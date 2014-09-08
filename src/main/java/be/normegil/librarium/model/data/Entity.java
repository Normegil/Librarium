package be.normegil.librarium.model.data;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.Constants;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
@Access(AccessType.FIELD)
public class Entity {

	@Id
	@Column(name = "ID")
	private UUID id;

	protected Entity() {
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
}
