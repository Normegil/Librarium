package be.normegil.librarium.model.data.people;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.Constants;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.validation.constraint.NotEmpty;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.TreeSet;

@javax.persistence.Entity
@Access(AccessType.FIELD)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Responsible extends Entity {

	@NotEmpty
	private String name;
	@NotNull
	private URL wikipediaPage;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "responsible")
	private Collection<StaffMember> staffMembers = new TreeSet<>();

	protected Responsible(@NotNull @Valid Responsible entity) {
		super();

		setName(entity.getName());
		setWikipediaPage(entity.getWikipediaPage());
	}

	protected Responsible(@NotNull @Valid Init<?> init) {
		super();

		setName(init.name);
		setWikipediaPage(init.wikipediaPage);
	}

	//For JAXB
	protected Responsible() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(@NotEmpty final String name) {
		this.name = name;
	}

	public URL getWikipediaPage() {
		return wikipediaPage;
	}

	public void setWikipediaPage(@NotNull @Valid final URL wikipediaPage) {
		this.wikipediaPage = wikipediaPage;
	}

	public Collection<StaffMember> getStaffMembers() {
		return new TreeSet<>(staffMembers);
	}

	public void addAllStaffMembers(@NotNull final Collection<StaffMember> staffMembers) {
		for (StaffMember staffMember : staffMembers) {
			addStaffMember(staffMember);
		}
	}

	public void addStaffMember(@NotNull final StaffMember staffMember) {
		staffMembers.add(staffMember);
	}

	public void removeAllStaffMembers(@NotNull final Collection<StaffMember> staffMembers) {
		for (StaffMember staffMember : staffMembers) {
			removeStaffMember(staffMember);
		}
	}

	public void removeStaffMember(@NotNull final StaffMember staffMember) {
		staffMembers.remove(staffMember);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.appendSuper(super.toString())
				.append("name", name)
				.append("wikipediaPage", wikipediaPage)
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
		Responsible rhs = (Responsible) obj;
		return new EqualsBuilder()
				.append(this.name, rhs.name)
				.append(this.wikipediaPage, rhs.wikipediaPage)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(name)
				.append(wikipediaPage)
				.toHashCode();
	}

	public int compareTo(final Responsible o) {
		if (o != null) {
			return new CompareToBuilder()
					.append(getClass(), o.getClass())
					.append(getName(), o.getName())
					.append(getWikipediaPage(), o.getWikipediaPage())
					.toComparison();
		} else {
			return Constants.Comparator.PRIORITY_SECOND;
		}
	}

	public abstract static class Init<E extends Init<E>> {

		private String name;
		private URL wikipediaPage;

		protected abstract E self();

		public E from(@NotNull @Valid Responsible entity) {
			setName(entity.getName());
			setWikipediaPage(entity.getWikipediaPage());
			return self();
		}

		public E setName(@NotEmpty final String name) {
			this.name = name;
			return self();
		}

		public E setWikipediaPage(@NotNull @Valid final URL wikipediaPage) {
			this.wikipediaPage = wikipediaPage;
			return self();
		}
	}
}
