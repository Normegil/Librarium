package be.normegil.librarium.model.data.video;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.Constants;
import be.normegil.librarium.model.data.Media;
import be.normegil.librarium.model.data.people.Person;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.model.data.people.Role;
import be.normegil.librarium.model.data.people.StaffRole;
import be.normegil.librarium.validation.constraint.Positive;
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
import java.time.Duration;
import java.util.*;

@javax.persistence.Entity
@Access(AccessType.FIELD)
public abstract class Video extends Media {

	@NotNull
	private Duration duration;
	@NotNull
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "video")
	private Collection<Role> actors = new TreeSet<>();

	public Video(@NotNull @Valid Video entity) {
		super(entity);

		setDuration(entity.getDuration());
		addAllProducers(entity.getProducers());
		addAllDirectors(entity.getDirectors());
		addAllComposers(entity.getComposers());
		addAllScenarists(entity.getScenarists());
		addAllOtherStaffMembers(entity.getOtherStaffMembers());
		addAllActors(entity.getActors());
	}

	protected Video(@NotNull @Valid final Init<?> init) {
		super(init);

		setDuration(init.duration);
		addAllProducers(init.producers);
		addAllDirectors(init.directors);
		addAllComposers(init.composers);
		addAllScenarists(init.scenarists);
		addAllOtherStaffMembers(init.others);
		addAllActors(init.actors);
	}

	//For JAXB
	protected Video() {
		super();
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(@NotNull @Positive final Duration duration) {
		this.duration = duration;
	}

	public Collection<Responsible> getProducers() {
		return getResponsibleFor(StaffRole.PRODUCER);
	}

	public void addAllProducers(@NotNull final Collection<Responsible> producers) {
		for (Responsible producer : producers) {
			addProducer(producer);
		}
	}

	public void addProducer(@NotNull final Responsible producer) {
		addStaffMember(StaffRole.PRODUCER, producer);
	}

	public void removeAllProducers(@NotNull final Collection<Responsible> producers) {
		for (Responsible producer : producers) {
			removeProducer(producer);
		}
	}

	public void removeProducer(@NotNull final Responsible producer) {
		removeStaffMember(StaffRole.PRODUCER, producer);
	}

	public Collection<Responsible> getDirectors() {
		return getResponsibleFor(StaffRole.DIRECTOR);
	}

	public void addAllDirectors(@NotNull final Collection<Responsible> directors) {
		for (Responsible director : directors) {
			addDirector(director);
		}
	}

	public void addDirector(@NotNull final Responsible director) {
		addStaffMember(StaffRole.DIRECTOR, director);
	}

	public void removeAllDirectors(@NotNull final Collection<Responsible> directors) {
		for (Responsible director : directors) {
			removeDirector(director);
		}
	}

	public void removeDirector(@NotNull final Responsible director) {
		removeStaffMember(StaffRole.DIRECTOR, director);
	}

	public Collection<Responsible> getComposers() {
		return getResponsibleFor(StaffRole.COMPOSER);
	}

	public void addAllComposers(@NotNull final Collection<Responsible> composers) {
		for (Responsible composer : composers) {
			addComposer(composer);
		}
	}

	public void addComposer(@NotNull final Responsible composer) {
		addStaffMember(StaffRole.COMPOSER, composer);
	}

	public void removeAllComposers(@NotNull final Collection<Responsible> composers) {
		for (Responsible composer : composers) {
			removeComposer(composer);
		}
	}

	public void removeComposer(@NotNull final Responsible composer) {
		removeStaffMember(StaffRole.COMPOSER, composer);
	}

	public Collection<Responsible> getScenarists() {
		return getResponsibleFor(StaffRole.AUTHOR);
	}

	public void addAllScenarists(@NotNull final Collection<Responsible> scenarists) {
		for (Responsible scenarist : scenarists) {
			addScenarist(scenarist);
		}
	}

	public void addScenarist(@NotNull final Responsible scenarist) {
		addStaffMember(StaffRole.AUTHOR, scenarist);
	}

	public void removeAllScenarists(@NotNull final Collection<Responsible> scenarists) {
		for (Responsible scenarist : scenarists) {
			removeScenarist(scenarist);
		}
	}

	public void removeScenarist(@NotNull final Responsible scenarist) {
		removeStaffMember(StaffRole.AUTHOR, scenarist);
	}

	public Collection<Responsible> getOtherStaffMembers() {
		return getResponsibleFor(StaffRole.OTHER);
	}

	public void addAllOtherStaffMembers(@NotNull final Collection<Responsible> others) {
		for (Responsible other : others) {
			addOtherStaffMember(other);
		}
	}

	public void addOtherStaffMember(@NotNull final Responsible other) {
		addStaffMember(StaffRole.OTHER, other);
	}

	public void removeAllOtherStaffMembers(@NotNull final Collection<Responsible> others) {
		for (Responsible other : others) {
			removeOtherStaffMember(other);
		}
	}

	public void removeOtherStaffMember(@NotNull final Responsible other) {
		removeStaffMember(StaffRole.OTHER, other);
	}
	//Other

	public SortedSet<Role> getActors() {
		return new TreeSet<>(actors);
	}

	public Set<Person> getActorsForRole(@NotNull @Valid Person searchedRole) {
		HashSet<Person> actorWithGivenRole = new HashSet<>();
		for (Role role : actors) {
			if (role.getRole().equals(searchedRole)) {
				actorWithGivenRole.add(role.getActor());
			}
		}
		return actorWithGivenRole;
	}

	public Set<Person> getRoleForActor(@NotNull @Valid Person searchedActor) {
		HashSet<Person> rolePlayed = new HashSet<>();
		for (Role role : actors) {
			if (role.getActor().equals(searchedActor)) {
				rolePlayed.add(role.getRole());
			}
		}
		return rolePlayed;
	}

	public void addAllActors(@NotNull final Collection<Role> actors) {
		for (Role actor : actors) {
			addActor(actor);
		}
	}

	public void addActor(@NotNull @Valid final Role actor) {
		actors.add(actor);
	}

	public void removeAllActors(@NotNull final Collection<Role> actors) {
		for (Role actor : actors) {
			removeRole(actor);
		}
	}

	public void removeActor(@NotNull @Valid final Person actor) {
		for (Role role : getActors()) {
			if (role.getActor().equals(actor)) {
				removeRole(role);
			}
		}
	}

	public void removeRole(@NotNull @Valid final Person roleToRemove) {
		for (Role role : getActors()) {
			if (role.getRole().equals(roleToRemove)) {
				removeRole(role);
			}
		}
	}

	public void removeRole(@NotNull @Valid final Role actor) {
		actors.remove(actor);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.appendSuper(super.toString())
				.append("duration", duration)
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
		Video rhs = (Video) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(this.duration, rhs.duration)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(duration)
				.toHashCode();
	}

	public int compareTo(final Video o) {
		if (o != null) {
			return new CompareToBuilder()
					.appendSuper(super.compareTo(o))
					.append(getDuration(), o.getDuration())
					.toComparison();
		} else {
			return Constants.Comparator.PRIORITY_SECOND;
		}
	}

	public abstract static class Init<E extends Init<E>> extends Media.Init<E> {

		private Duration duration;
		private Collection<Role> actors = new TreeSet<>();
		private Collection<Responsible> producers = new TreeSet<>();
		private Collection<Responsible> directors = new TreeSet<>();
		private Collection<Responsible> composers = new TreeSet<>();
		private Collection<Responsible> scenarists = new TreeSet<>();
		private Collection<Responsible> others = new TreeSet<>();

		protected abstract E self();

		public E from(@NotNull @Valid final Video entity) {
			super.from(entity);
			setDuration(entity.getDuration());

			addAllActors(entity.getActors());
			return self();
		}

		public E setDuration(@NotNull @Positive final Duration duration) {
			this.duration = duration;
			return self();
		}

		public E addAllProducers(@NotNull final Collection<Responsible> producers) {
			for (Responsible producer : producers) {
				addProducer(producer);
			}
			return self();
		}

		public E addProducer(@NotNull final Responsible producer) {
			producers.add(producer);
			return self();
		}

		public E addAllDirectors(@NotNull final Collection<Responsible> directors) {
			for (Responsible director : directors) {
				addDirector(director);
			}
			return self();
		}

		public E addDirector(@NotNull final Responsible director) {
			directors.add(director);
			return self();
		}

		public E addAllComposers(@NotNull final Collection<Responsible> composers) {
			for (Responsible composer : composers) {
				addComposer(composer);
			}
			return self();
		}

		public E addComposer(@NotNull final Responsible composer) {
			composers.add(composer);
			return self();
		}

		public E addAllScenarists(@NotNull final Collection<Responsible> scenarists) {
			for (Responsible scenarist : scenarists) {
				addScenarist(scenarist);
			}
			return self();
		}

		public E addScenarist(@NotNull final Responsible scenarist) {
			scenarists.add(scenarist);
			return self();
		}

		public E addAllOtherStaffMembers(@NotNull final Collection<Responsible> others) {
			for (Responsible other : others) {
				addOtherStaffMember(other);
			}
			return self();
		}

		public E addOtherStaffMember(@NotNull final Responsible other) {
			others.add(other);
			return self();
		}

		public E addAllActors(@NotNull final Collection<Role> actors) {
			for (Role actor : actors) {
				addActor(actor);
			}
			return self();
		}

		public E addActor(@NotNull @Valid final Role actor) {
			actors.add(actor);
			return self();
		}
	}
}
