package be.normegil.librarium.model.data.fake;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.model.data.Entity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class FakeEntity extends Entity implements Comparable<Entity> {

	@Override
	public boolean equals(Object obj) {
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.appendSuper(super.toString())
				.toString();
	}
}
