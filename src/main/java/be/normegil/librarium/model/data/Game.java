package be.normegil.librarium.model.data;

import org.apache.commons.lang3.Validate;

public class Game {

    private Long id;

    private String name;

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

    public Game withName(String name) {
        Validate.notNull(name);
        Validate.isTrue(!name.isEmpty());

        Game game = new Game(this);
        game.name = name;
        return game;
    }
}
