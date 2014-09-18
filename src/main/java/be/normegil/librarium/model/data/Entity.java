package be.normegil.librarium.model.data;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.Constants;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.util.parser.adapter.jaxb.UUIDToRESTURLJAXBAdapter;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
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

	public static class Helper {
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
