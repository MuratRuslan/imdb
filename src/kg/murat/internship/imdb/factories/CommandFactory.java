package kg.murat.internship.imdb.factories;

import kg.murat.internship.imdb.commands.Command;
import kg.murat.internship.imdb.commands.filmCommands.*;
import kg.murat.internship.imdb.commands.personCommands.ListAllCategorizedArtistsByCountry;
import kg.murat.internship.imdb.dao.FilmRepository;
import kg.murat.internship.imdb.dao.PersonRepository;
import kg.murat.internship.imdb.services.FilmService;
import kg.murat.internship.imdb.services.PersonService;
import kg.murat.internship.imdb.services.ioServices.Logger;

/**
 * Created by Fujitsu on 26.03.2017.
 */
public class CommandFactory {
    private FilmService filmService;
    private PersonService personService;
    private Logger logger;

    public CommandFactory(FilmService filmService, PersonService personService, Logger logger) {
        this.filmService = filmService;
        this.personService = personService;
        this.logger = logger;
    }

    public Command get(String command) {
        String[] split = command.split("\\t");
        if(split[0].equalsIgnoreCase("RATE")) {
            return new RateFilmCommand(filmService, command, logger);
        }
        if(split[0].equalsIgnoreCase("add") && split[1].equalsIgnoreCase("FEATUREFILM")) {
            return new AddFeatureFilmCommand(filmService, command, logger);
        }
        if(split[0].equalsIgnoreCase("viewfilm")) {
            return new ViewFilmCommand(filmService, command, logger);
        }
        if(split[1].equalsIgnoreCase("user") && split[3].equalsIgnoreCase("rates")) {
            return new ListUserRatedFilmsCommand(filmService, command, logger);
        }
        if(split[0].equalsIgnoreCase("edit") && split[1].equalsIgnoreCase("rate")) {
            return new EditRatedFilmCommand(filmService, command, logger);
        }
        if(split[0].equalsIgnoreCase("remove") && split[1].equalsIgnoreCase("rate")) {
            return new RemoveRateCommand(filmService, command, logger);
        }
        if(split[0].equalsIgnoreCase("list") && split[2].equalsIgnoreCase("series")) {
            return new ListAllTVSeriesCommand(filmService, command, logger);
        }
        if(split[0].equalsIgnoreCase("list") && split[1].equalsIgnoreCase("films") && split[3].equalsIgnoreCase("country")) {
            return new ListAllFilmsByCountry(filmService, command, logger);
        }
        if(split[0].equalsIgnoreCase("list") && split[1].equalsIgnoreCase("featurefilm") && (split[2].equalsIgnoreCase("after")
                || split[2].equalsIgnoreCase("before"))) {
            return new ListAllFilmsBeforeAfterCommand(filmService, command, logger);
        }
        if(split[0].equalsIgnoreCase("list") && split[1].equalsIgnoreCase("films") && split[3].equalsIgnoreCase("rate")
                && split[4].equalsIgnoreCase("degree")) {
            return new ListAllFilmsCategorizedRatingDescCommand(filmService, command, logger);
        }
        if(split[0].equalsIgnoreCase("list") && split[1].equalsIgnoreCase("artists") && split[2].equalsIgnoreCase("from")) {
            return new ListAllCategorizedArtistsByCountry(personService, command, logger);
        }
        return null;
    }
}
