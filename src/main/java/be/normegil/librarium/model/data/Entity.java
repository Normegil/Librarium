package be.normegil.librarium.model.data;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.Constants;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.rest.RESTHelper;
import be.normegil.librarium.util.parser.adapter.jaxb.UUIDToRESTURLJAXBAdapter;
import be.normegil.librarium.validation.constraint.ExistingID;
import be.normegil.librarium.validation.constraint.URIWithID;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class Entity {

	@Id
	@Column(name = "ID")
	@XmlAttribute
	private UUID id;

	protected Entity() {
	}

	public static Helper helper() {
		return new Helper();
	}

	public UUID getId() {
		return id;
	}

	@PrePersist
	private void generateUUID() {
		if (id == null) {
			id = UUID.randomUUID();
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.append("id", id)
				.toString();
	}

	public int compareTo(final Entity o) {
		if (o != null) {
			return new CompareToBuilder()
					.append(getId(), o.getId())
					.toComparison();
		} else {
			return Constants.Comparator.PRIORITY_SECOND;
		}
	}

	public static class EntityDigest {

		@NotNull
		@URIWithID
		protected URI href;

		public void fromBase(@NotNull final URI baseUri, @NotNull @ExistingID final Entity entity) {
			href = new RESTHelper().getRESTUri(baseUri, entity.getClass(), entity);
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (obj.getClass() != getClass()) {
				return false;
			}
			EntityDigest rhs = (EntityDigest) obj;
			return new EqualsBuilder()
					.append(this.href, rhs.href)
					.isEquals();
		}

		@Override
		public int hashCode() {
			return new HashCodeBuilder()
					.append(href)
					.toHashCode();
		}
	}

	public static class Helper {

		public UUID getIdFromRESTURI(@NotNull @URIWithID final URI restURI) {
			String idAsString = StringUtils.substringAfterLast(restURI.toString(), "/");
			try {
				return UUID.fromString(idAsString);
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("URI : " + restURI, e);
			}
		}

		public void setIdFromDigest(@NotNull @Valid final EntityDigest digest, @NotNull final Entity entity) {
			entity.id = digest.href != null ? getIdFromRESTURI(digest.href) : null;
		}

		public List<URL> convertToURLs(@NotNull final List<? extends Entity> entities, @NotNull final URL baseURL) {
			List<URL> urlsToEntities = new ArrayList<>();
			UUIDToRESTURLJAXBAdapter adapter = new UUIDToRESTURLJAXBAdapter(baseURL);
			for (Entity entity : entities) {
				UUID id = entity.getId();
				URL urlToEntity = adapter.marshal(id);
				urlsToEntities.add(urlToEntity);
			}
			return urlsToEntities;
		}
	}
}
