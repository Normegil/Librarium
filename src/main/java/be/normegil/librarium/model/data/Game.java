package be.normegil.librarium.model.data;

import org.apache.commons.lang3.Validate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.*;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @XmlAttribute
    private Long id;
    @XmlElement
    private String name;

    /**
     * For JAXB
     */
    protected Game() {
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
        Validate.notNull(id);

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
}
