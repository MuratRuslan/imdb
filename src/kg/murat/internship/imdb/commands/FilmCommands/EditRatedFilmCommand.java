package kg.murat.internship.imdb.commands.filmCommands;

import kg.murat.internship.imdb.commands.AbstractFilmCommand;
import kg.murat.internship.imdb.services.FilmService;
import kg.murat.internship.imdb.services.ioServices.Logger;

/**
 * Created by Fujitsu on 26.03.2017.
 */
public class EditRatedFilmCommand extends AbstractFilmCommand {

    public EditRatedFilmCommand(FilmService filmService, String command, Logger logger) {
        super(filmService, command, logger);
    }

    @Override
    public void execute() {
        try {
            filmService.editRatedFilm(command);
        } catch (Exception e) {
            logger.log(command, COMMAND_FAILED_MSG);
        }
    }
}
