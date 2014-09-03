package be.normegil.librarium.model.data.book;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.Constants;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public abstract class AbstractBD extends Book {

	@NotNull
	@Valid
	private Long issueNumber;

	protected AbstractBD(@NotNull @Valid AbstractBD entity) {
		super(entity);
		setIssueNumber(entity.getIssueNumber());
	}

	protected AbstractBD(@NotNull @Valid Init<?> init) {
		super(init);
		setIssueNumber(init.issueNumber);
	}

	//For JAXB
	protected AbstractBD() {
		super();
	}

	public Long getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(@NotNull @Valid final Long issueNumber) {
		this.issueNumber = issueNumber;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.appendSuper(super.toString())
				.append("issueNumber", issueNumber)
				.toString();
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
		AbstractBD rhs = (AbstractBD) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(this.issueNumber, rhs.issueNumber)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(issueNumber)
				.toHashCode();
	}

	public int compareTo(final AbstractBD o) {
		if (o != null) {
			return new CompareToBuilder()
					.appendSuper(super.compareTo(o))
					.append(getIssueNumber(), o.getIssueNumber())
					.toComparison();
		} else {
			return Constants.Comparator.PRIORITY_SECOND;
		}
	}

	public abstract static class Init<E extends Init<E>> extends Book.Init<E> {

		private Long issueNumber;

		protected abstract E self();

		public E from(@NotNull @Valid AbstractBD entity) {
			super.from(entity);
			setIssueNumber(entity.getIssueNumber());
			return self();
		}

		public E setIssueNumber(@NotNull @Valid final Long issueNumber) {
			this.issueNumber = issueNumber;
			return self();
		}
	}
}
