package be.normegil.librarium.model.data;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.Constants;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.Comparator;

@MappedSuperclass
@Access(AccessType.FIELD)
public class Entity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@XmlAttribute
	private Long id;

	protected Entity() {
	}

	public Long getId() {
		return id;
	}

	// Needed for JAXB Unmarshalling
	private void setId(@NotNull Long id) {
		this.id = id;
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
		}else {
			return Constants.Comparator.PRIORITY_SECOND;
		}
	}
}
