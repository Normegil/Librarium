package be.normegil.librarium.model.data;

import be.normegil.librarium.annotation.XSD;
import org.apache.commons.lang3.Validate;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

/* @NamedQueries(
        @NamedQuery(name = "Game.getAll",
                query = "select Game from Game")
) */

@Entity
@Table(name = "GAME")
@XSD(path = "game.xsd")
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    @XmlAttribute
    private Long id;
    @XmlElement
    @Column(name = "NAME")
    private String name;

    /**
     * For JAXB
     */
    public Game() {
        this.id = null;
        this.name = null;
    }

    public Game(final String name) {
        validateName(name);

        this.name = name;
    }

    public Game(final Long id, final String name) {
        Validate.notNull(id);
        validateName(name);

        this.id = id;
        this.name = name;
    }

    public Game(final Game game) {
        Validate.notNull(game);

        this.id = game.getId();
        this.name = game.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        validateName(name);

        return name;
    }

    public void setName(String name) {
        validateName(name);

        this.name = name;
    }

    private void validateName(final String name) {
        Validate.notNull(name);
        Validate.isTrue(!name.isEmpty());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (!name.equals(game.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
