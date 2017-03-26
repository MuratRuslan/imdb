package kg.murat.internship.imdb.commands;

import kg.murat.internship.imdb.services.FilmService;
import kg.murat.internship.imdb.services.ioServices.Logger;

/**
 * Created by Fujitsu on 26.03.2017.
 */
public abstract class AbstractFilmCommand implements Command {
    protected static final String COMMAND_FAILED_MSG = "Command failed";
    protected FilmService filmService;
    protected String command;
    protected Logger logger;

    public AbstractFilmCommand(FilmService filmService, String command, Logger logger) {
        this.filmService = filmService;
        this.command = command;
        this.logger = logger;
    }
}
