package be.normegil.librarium.model.data;

import be.normegil.librarium.Constants;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.model.data.people.StaffMember;
import be.normegil.librarium.model.data.people.StaffRole;
import be.normegil.librarium.util.CollectionComparator;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

@javax.persistence.Entity
@Access(AccessType.FIELD)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Media extends BaseMedia {

	private static final Logger LOG = LoggerFactory.getLogger(Media.class);
	private static final CollectionComparator COLLECTION_COMPARATOR = new CollectionComparator();

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH,})
	private Collection<Universe> universes = new TreeSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "media")
	private Collection<StaffMember> staffMembers = new TreeSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "media")
	private Collection<ReleaseDate> releaseDates = new TreeSet<>();

	protected Media(@NotNull @Valid Media entity) {
		super(entity);
		addAllUniverses(entity.getUniverses());
		addAllReleaseDates(entity.getReleaseDates());
	}

	protected Media(@NotNull @Valid Init<?> init) {
		super(init);
		addAllUniverses(init.universes);
		addAllReleaseDates(init.releaseDates);
	}

	//For JAXB
	protected Media() {
		super();
	}

	public Set<Universe> getUniverses() {
		return new TreeSet<>(universes);
	}

	public void addAllUniverses(@NotNull final Collection<Universe> universes) {
		for (Universe universe : universes) {
			addUniverse(universe);
		}
	}

	public void addUniverse(@NotNull final Universe universe) {
		universes.add(universe);
	}

	public void removeAllUniverses(@NotNull final Collection<Universe> universes) {
		for (Universe universe : universes) {
			removeUniverse(universe);
		}
	}

	public void removeUniverse(@NotNull final Universe universe) {
		universes.remove(universe);
	}

	public Set<Support> getSupports() {
		Set<Support> supports = new TreeSet<>();
		for (ReleaseDate releaseDate : releaseDates) {
			supports.add(releaseDate.getSupport());
		}
		return supports;
	}

	public void addAllSupports(@NotNull final Collection<Support> supports) {
		for (Support support : supports) {
			addSupport(support);
		}
	}

	public void addSupport(@NotNull final Support support) {
		addReleaseDate(support, null);
	}

	public void removeAllSupports(@NotNull final Collection<Support> supports) {
		for (Support support : supports) {
			removeSupport(support);
		}
	}

	public void removeSupport(@NotNull final Support support) {
		removeReleaseDate(support);
	}

	public Map<Support, LocalDate> getReleaseDates() {
		TreeMap<Support, LocalDate> releaseDates = new TreeMap<>();
		for (ReleaseDate releaseDate : this.releaseDates) {
			releaseDates.put(releaseDate.getSupport(), releaseDate.getDate());
		}
		return releaseDates;
	}

	public LocalDate getReleaseDate(@NotNull @Valid final Support support) {
		for (ReleaseDate releaseDate : releaseDates) {
			if (releaseDate.getSupport().equals(support)) {
				return releaseDate.getDate();
			}
		}
		throw new NoSuchElementException("Media not supported by the media [Support=" + support + "]");
	}

	public void addAllReleaseDates(@NotNull final Map<Support, LocalDate> releaseDates) {
		for (Map.Entry<Support, LocalDate> releaseDate : releaseDates.entrySet()) {
			addReleaseDate(releaseDate.getKey(), releaseDate.getValue());
		}
	}

	public void addReleaseDate(@NotNull @Valid final Support support, final LocalDate releaseDate) {
		try {
			LocalDate date = getReleaseDate(support);
			LOG.warn("ReleaseDate will not be added : Date for Support already exist [FoundDate=" + date + "][GivenSupport=" + support + "; GivenDate=" + releaseDate + "]");
		} catch (NoSuchElementException e) {
			releaseDates.add(new ReleaseDate(this, support, releaseDate));
		}
	}

	public void removeAllReleaseDates(@NotNull final Collection<Support> supports) {
		for (Support support : supports) {
			removeReleaseDate(support);
		}
	}

	public void removeReleaseDate(@NotNull @Valid final Support support) {
		NavigableSet<ReleaseDate> dates = new TreeSet<>(releaseDates);
		for (ReleaseDate releaseDate : dates) {
			if (releaseDate.getSupport().equals(support)) {
				releaseDates.remove(releaseDate);
			}
		}
	}

	protected Set<StaffMember> getStaffMembers() {
		return new TreeSet<>(staffMembers);
	}

	protected Set<Responsible> getResponsibleFor(@NotNull StaffRole staffRole) {
		Set<Responsible> responsibles = new TreeSet<>();
		for (StaffMember staffMember : getStaffMembers()) {
			if (staffRole.equals(staffMember.getRole())) {
				responsibles.add(staffMember.getResponsible());
			}
		}
		return responsibles;
	}

	protected void addStaffMember(@NotNull StaffRole staffRole, @NotNull @Valid Responsible responsible) {
		StaffMember staffMember = StaffMember.builder()
				.setMedia(this)
				.setRole(staffRole)
				.setResponsible(responsible)
				.build();
		staffMembers.add(staffMember);
	}

	protected void removeStaffMember(StaffRole staffRole, Responsible responsible) {
		for (StaffMember staffMember : getStaffMembers()) {
			if (hasRole(staffRole, staffMember) && isResponsible(responsible, staffMember)) {
				staffMembers.remove(staffMember);
			}
		}
	}


	private boolean hasRole(final StaffRole staffRole, final StaffMember staffMember) {
		return staffRole == null || staffRole.equals(staffMember.getRole());
	}

	private boolean isResponsible(final Responsible responsible, final StaffMember staffMember) {
		return responsible == null || responsible.equals(staffMember.getResponsible());
	}

	public int compareTo(final Media o) {
		if (o != null) {
			return new CompareToBuilder()
					.appendSuper(super.compareTo(o))
					.appendSuper(COLLECTION_COMPARATOR.compare(getUniverses(), o.getUniverses()))
					.toComparison();
		} else {
			return Constants.Comparator.PRIORITY_SECOND;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.appendSuper(super.toString())
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
		Media rhs = (Media) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(this.universes, rhs.universes)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(universes)
				.toHashCode();
	}

	public abstract static class Init<E extends Init<E>> extends BaseMedia.Init<E> {

		private Collection<Universe> universes = new TreeSet<>();
		private Map<Support, LocalDate> releaseDates = new TreeMap<>();

		protected abstract E self();

		public E from(@NotNull @Valid Media entity) {
			super.from(entity);
			addAllUniverses(entity.getUniverses());
			addAllReleaseDates(entity.getReleaseDates());
			return self();
		}

		public E addAllUniverses(@NotNull final Collection<Universe> universes) {
			for (Universe universe : universes) {
				addUniverse(universe);
			}
			return self();
		}

		public E addUniverse(@NotNull final Universe universe) {
			universes.add(universe);
			return self();
		}

		public E addAllSupports(@NotNull final Collection<Support> supports) {
			for (Support support : supports) {
				addSupport(support);
			}
			return self();
		}

		public E addSupport(@NotNull final Support support) {
			addReleaseDate(support, null);
			return self();
		}

		public E addAllReleaseDates(@NotNull final Map<Support, LocalDate> releaseDates) {
			for (Map.Entry<Support, LocalDate> releaseDate : releaseDates.entrySet()) {
				addReleaseDate(releaseDate.getKey(), releaseDate.getValue());
			}
			return self();
		}

		public E addReleaseDate(@NotNull @Valid final Support support, final LocalDate releaseDate) {
			releaseDates.put(support, releaseDate);
			return self();
		}
	}
}
