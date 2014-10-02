package be.normegil.librarium.model.data.game;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.Constants;
import be.normegil.librarium.model.dao.DAO;
import be.normegil.librarium.model.dao.DatabaseDAO;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.model.data.Media;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.model.data.people.StaffRole;
import be.normegil.librarium.model.rest.RESTHelper;
import be.normegil.librarium.model.rest.digest.Digest;
import be.normegil.librarium.util.CollectionComparator;
import be.normegil.librarium.util.exception.NoSuchEntityException;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.net.URI;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

@javax.persistence.Entity
@Access(AccessType.FIELD)
public class Game extends Media implements Comparable<Game>, Serializable {

	private static final CollectionComparator COLLECTION_COMPARATOR = new CollectionComparator();

	@ManyToOne(cascade = {
			CascadeType.PERSIST,
			CascadeType.REFRESH,
			CascadeType.DETACH,
			CascadeType.MERGE
	})
	@JoinColumn(name = "id_gameserie")
	private GameSerie serie;

	public Game(@NotNull @Valid Game entity) {
		super(entity);
		addAllDevelopers(entity.getDevelopers());
		addAllEditors(entity.getEditors());
		addAllComposers(entity.getComposers());
		setSerie(entity.getSerie());
	}

	protected Game(@NotNull @Valid Init<?> init) {
		super(init);
		addAllDevelopers(init.developers);
		addAllEditors(init.editors);
		addAllComposers(init.composers);
		setSerie(init.serie);
	}

	//For JAXB
	private Game() {
		super();
	}

	public static Builder builder() {
		return new Builder();
	}

	public Set<Responsible> getDevelopers() {
		return getResponsibleFor(StaffRole.DEVELOPER);
	}

	public void addAllDevelopers(@NotNull final Collection<Responsible> developers) {
		for (Responsible developer : developers) {
			addDeveloper(developer);
		}
	}

	public void addDeveloper(@NotNull @Valid final Responsible developer) {
		addStaffMember(StaffRole.DEVELOPER, developer);
	}

	public void removeAllDevelopers(@NotNull final Collection<Responsible> developers) {
		for (Responsible developer : developers) {
			removeDeveloper(developer);
		}
	}

	public void removeDeveloper(@NotNull @Valid final Responsible developer) {
		removeStaffMember(StaffRole.DEVELOPER, developer);
	}

	public Set<Responsible> getEditors() {
		return getResponsibleFor(StaffRole.EDITOR);
	}

	public void addAllEditors(@NotNull final Collection<Responsible> editors) {
		for (Responsible editor : editors) {
			addEditor(editor);
		}
	}

	public void addEditor(@NotNull @Valid final Responsible editor) {
		addStaffMember(StaffRole.EDITOR, editor);
	}

	public void removeAllEditors(@NotNull final Collection<Responsible> editors) {
		for (Responsible editor : editors) {
			removeEditor(editor);
		}
	}

	public void removeEditor(@NotNull @Valid final Responsible editor) {
		removeStaffMember(StaffRole.EDITOR, editor);
	}

	public Set<Responsible> getComposers() {
		return getResponsibleFor(StaffRole.COMPOSER);
	}

	public void addAllComposers(@NotNull final Collection<Responsible> composers) {
		for (Responsible composer : composers) {
			addComposer(composer);
		}
	}

	public void addComposer(@NotNull @Valid final Responsible composer) {
		addStaffMember(StaffRole.COMPOSER, composer);
	}

	public void removeAllComposers(@NotNull final Collection<Responsible> composers) {
		for (Responsible composer : composers) {
			removeComposer(composer);
		}
	}

	public void removeComposer(@NotNull @Valid final Responsible composer) {
		removeStaffMember(StaffRole.COMPOSER, composer);
	}

	public GameSerie getSerie() {
		return serie;
	}

	public void setSerie(@NotNull @Valid final GameSerie serie) {
		this.serie = serie;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.appendSuper(super.toString())
				.append("developers", getDevelopers())
				.append("editors", getEditors())
				.append("composers", getComposers())
				.append("serie", serie)
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
		Game rhs = (Game) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(this.getDevelopers(), rhs.getDevelopers())
				.append(this.getEditors(), rhs.getEditors())
				.append(this.getComposers(), rhs.getComposers())
				.append(this.serie, rhs.serie)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(getDevelopers())
				.append(getEditors())
				.append(getComposers())
				.append(serie)
				.toHashCode();
	}

	@Override
	public int compareTo(final Game o) {
		if (o != null) {
			return new CompareToBuilder()
					.appendSuper(super.compareTo(o))
					.append(getSerie(), o.getSerie())
					.appendSuper(COLLECTION_COMPARATOR.compare(getDevelopers(), o.getDevelopers()))
					.appendSuper(COLLECTION_COMPARATOR.compare(getEditors(), o.getEditors()))
					.appendSuper(COLLECTION_COMPARATOR.compare(getComposers(), o.getComposers()))
					.toComparison();
		} else {
			return Constants.Comparator.PRIORITY_SECOND;
		}
	}

	public static class GameDigest extends Media.MediaDigest implements Digest<Game> {
		protected URI serie;

		@Override
		public Game toBase() {
			Builder builder = Game.builder();
			super.toBase(builder);

			DAO<GameSerie> serieDAO = new DatabaseDAO<>(GameSerie.class);
			UUID id = Entity.helper().getIdFromRESTURI(serie);
			GameSerie serie = serieDAO.get(id);
			if (serie != null) {
				builder.setSerie(serie);
			} else {
				throw new NoSuchEntityException("Cannot find ReleaseDate in database with ID : " + id);
			}

			Game game = builder.build();
			Entity.helper().setIdFromDigest(this, game);
			return game;
		}

		@Override
		public void fromBase(final URI baseURI, final Game entity) {
			super.fromBase(baseURI, entity);
			serie = new RESTHelper().getRESTUri(baseURI, GameSerie.class, entity.getSerie());
		}
	}

	public abstract static class Init<E extends Init<E>> extends Media.Init<E> {

		private Collection<Responsible> developers = new TreeSet<>();
		private Collection<Responsible> editors = new TreeSet<>();
		private Collection<Responsible> composers = new TreeSet<>();
		private GameSerie serie;

		protected abstract E self();

		public E from(@NotNull @Valid Game entity) {
			super.from(entity);
			addAllDevelopers(entity.getDevelopers());
			addAllEditors(entity.getEditors());
			addAllComposers(entity.getComposers());
			setSerie(entity.getSerie());
			return self();
		}

		public E addAllDevelopers(@NotNull final Collection<Responsible> developers) {
			for (Responsible developer : developers) {
				addDeveloper(developer);
			}
			return self();
		}

		public E addDeveloper(@NotNull @Valid final Responsible developer) {
			developers.add(developer);
			return self();
		}

		public E addAllEditors(@NotNull final Collection<Responsible> editors) {
			for (Responsible editor : editors) {
				addEditor(editor);
			}
			return self();
		}

		public E addEditor(@NotNull @Valid final Responsible editor) {
			editors.add(editor);
			return self();
		}

		public E addAllComposers(@NotNull final Collection<Responsible> composers) {
			for (Responsible composer : composers) {
				addComposer(composer);
			}
			return self();
		}

		public E addComposer(@NotNull @Valid final Responsible composer) {
			composers.add(composer);
			return self();
		}

		public E setSerie(@NotNull @Valid final GameSerie serie) {
			this.serie = serie;
			return self();
		}

		public
		@Valid
		Game build() {
			return new Game(this);
		}
	}

	public static class Builder extends Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
