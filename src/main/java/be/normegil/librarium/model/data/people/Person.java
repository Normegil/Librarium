package be.normegil.librarium.model.data.people;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.Constants;
import be.normegil.librarium.util.CollectionComparator;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

@javax.persistence.Entity
@Access(AccessType.FIELD)
public class Person extends Responsible implements Comparable<Person>, Serializable {

	private static final CollectionComparator COLLECTION_COMPARATOR = new CollectionComparator();
	@OneToMany(cascade = CascadeType.ALL)
	private Collection<Role> roles = new ArrayList<>();

	public Person(@NotNull @Valid Person entity) {
		super(entity);

		addAllRoles(entity.getRoles());
	}

	protected Person(@NotNull @Valid Init<?> init) {
		super(init);

		addAllRoles(init.roles);
	}

	//For JAXB
	private Person() {
		super();
	}

	public static Builder builder() {
		return new Builder();
	}

	public Collection<Role> getRoles() {
		return new TreeSet<>(roles);
	}

	public void addAllRoles(@NotNull final Collection<Role> roles) {
		for (Role role : roles) {
			addRole(role);
		}
	}

	public void addRole(@NotNull @Valid final Role role) {
		roles.add(role);
	}

	public void removeAllRoles(@NotNull final Collection<Role> roles) {
		for (Role role : roles) {
			removeRole(role);
		}
	}

	public void removeRole(@NotNull @Valid final Role role) {
		roles.remove(role);
	}

	@Override
	public int compareTo(final Person o) {
		if (o != null) {
			return new CompareToBuilder()
					.appendSuper(super.compareTo(o))
					.appendSuper(COLLECTION_COMPARATOR.compare(getRoles(), o.getRoles()))
					.toComparison();
		} else {
			return Constants.Comparator.PRIORITY_SECOND;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.appendSuper(super.toString())
				.append("roles", roles)
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
		Person rhs = (Person) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(this.roles, rhs.roles)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(roles)
				.toHashCode();
	}

	public abstract static class Init<E extends Init<E>> extends Responsible.Init<E> {

		private Collection<Role> roles = new TreeSet<>();

		protected abstract E self();

		public E from(@NotNull @Valid Person entity) {
			super.from(entity);
			addAllRoles(entity.getRoles());
			return self();
		}

		public E addAllRoles(@NotNull final Collection<Role> roles) {
			for (Role role : roles) {
				addRole(role);
			}
			return self();
		}

		public E addRole(@NotNull @Valid final Role role) {
			roles.add(role);
			return self();
		}

		public
		@Valid
		Person build() {
			return new Person(this);
		}
	}

	protected static class Builder extends Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
