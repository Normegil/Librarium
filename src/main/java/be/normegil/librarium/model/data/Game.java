package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import org.apache.commons.lang3.Validate;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Game {

    @XmlAttribute
    private final Long id;
    @XmlElement
    private final String name;

    /**
     * For JAXB
     */
    @SuppressWarnings(WarningTypes.UNUSED)
    private Game() {
        this.id = null;
        this.name = null;
    }

    public Game(final Long id, final String name) {
        Validate.notNull(id);
        Validate.notNull(name);
        Validate.isTrue(!name.isEmpty());

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
        return name;
    }

    public Game withName(final String name) {
        Validate.notNull(name);
        Validate.isTrue(!name.isEmpty());

        return new Game(getId(), name);
    }
}
