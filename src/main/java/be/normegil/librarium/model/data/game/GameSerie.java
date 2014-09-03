package be.normegil.librarium.model.data.game;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.Constants;
import be.normegil.librarium.model.data.BaseMedia;
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
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

@javax.persistence.Entity
@Access(AccessType.FIELD)
public class GameSerie extends BaseMedia implements Comparable<GameSerie>, Serializable {

	private static final CollectionComparator COLLECTION_COMPARATOR = new CollectionComparator();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "serie")
	private Set<Game> games = new TreeSet<>();

	public GameSerie(@NotNull @Valid GameSerie entity) {
		super(entity);
		addAllGames(entity.getGames());
	}

	protected GameSerie(@NotNull @Valid Init<?> init) {
		super(init);
		addAllGames(init.games);
	}

	//For JAXB
	private GameSerie() {
		super();
	}

	public static Builder builder() {
		return new Builder();
	}

	public Set<Game> getGames() {
		return new TreeSet<>(games);
	}

	public void addAllGames(@NotNull final Collection<Game> games) {
		for (Game game : games) {
			addGame(game);
		}
	}

	public void addGame(@NotNull @Valid final Game game) {
		games.add(game);
	}

	public void removeAllGames(@NotNull final Collection<Game> games) {
		for (Game game : games) {
			removeGame(game);
		}
	}

	public void removeGame(@NotNull @Valid final Game game) {
		games.remove(game);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.appendSuper(super.toString())
				.append("games", games)
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
		GameSerie rhs = (GameSerie) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(this.games, rhs.games)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(games)
				.toHashCode();
	}

	@Override
	public int compareTo(final GameSerie o) {
		if (o != null) {
			return new CompareToBuilder()
					.appendSuper(super.compareTo(o))
					.appendSuper(COLLECTION_COMPARATOR.compare(getGames(), o.getGames()))
					.toComparison();
		} else {
			return Constants.Comparator.PRIORITY_SECOND;
		}
	}

	public abstract static class Init<E extends Init<E>> extends BaseMedia.Init<E> {

		private Collection<Game> games = new TreeSet<>();

		protected abstract E self();

		public E from(@NotNull @Valid GameSerie entity) {
			super.from(entity);
			addAllGames(entity.getGames());
			return self();
		}

		public E addAllGames(@NotNull final Collection<Game> games) {
			for (Game game : games) {
				addGame(game);
			}
			return self();
		}

		public E addGame(@NotNull @Valid final Game game) {
			games.add(game);
			return self();
		}

		public
		@Valid
		GameSerie build() {
			return new GameSerie(this);
		}
	}

	protected static class Builder extends Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
