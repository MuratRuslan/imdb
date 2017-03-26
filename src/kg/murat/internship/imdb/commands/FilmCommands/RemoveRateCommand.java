package kg.murat.internship.imdb.commands.filmCommands;

import kg.murat.internship.imdb.commands.AbstractFilmCommand;
import kg.murat.internship.imdb.services.FilmService;
import kg.murat.internship.imdb.services.ioServices.Logger;

/**
 * Created by Fujitsu on 26.03.2017.
 */
public class RemoveRateCommand extends AbstractFilmCommand {
    public RemoveRateCommand(FilmService filmService, String command, Logger logger) {
        super(filmService, command, logger);
    }

    @Override
    public void execute() {
        try {
            filmService.removeRate(command);
        } catch (Exception e) {
            logger.log(command, COMMAND_FAILED_MSG);
        }
    }
}
