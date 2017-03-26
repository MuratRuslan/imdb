package kg.murat.internship.imdb.tests;

import kg.murat.internship.imdb.commands.Command;
import kg.murat.internship.imdb.dao.FilmRepository;
import kg.murat.internship.imdb.dao.PersonRepository;
import kg.murat.internship.imdb.dao.impl.FileFilmRepositoryImpl;
import kg.murat.internship.imdb.dao.impl.FilePersonRepositoryImpl;
import kg.murat.internship.imdb.factories.CommandFactory;
import kg.murat.internship.imdb.services.FilmService;
import kg.murat.internship.imdb.services.PersonService;
import kg.murat.internship.imdb.services.impl.FilmServiceImpl;
import kg.murat.internship.imdb.services.impl.PersonServiceImpl;
import kg.murat.internship.imdb.services.ioServices.Logger;
import kg.murat.internship.imdb.services.ioServices.impl.FileLogger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Fujitsu on 26.03.2017.
 */
public class CommandFactoryTest {
    private CommandFactory commandFactory;
    
    @Before
    public void setUp() throws Exception {
        Logger logger = new FileLogger("output.txt");
        PersonRepository personRepository = new FilePersonRepositoryImpl("people.txt");
        PersonService personService = new PersonServiceImpl(personRepository, logger);
        FilmRepository filmRepository = new FileFilmRepositoryImpl("films.txt", personService.getAll());
        FilmService filmService = new FilmServiceImpl(filmRepository, personService.getAll(), logger);
        commandFactory = new CommandFactory(filmService, personService, logger);
    }

    @Test
    public void get() throws Exception {
        Command rateCommand = commandFactory.get("RATE\t470\t113\t9");
        Command addFeatureFilmCommand = commandFactory.get("ADD\tFEATUREFILM\t115\tFight_Club\tEnglish\t463\t139\tUSA\t466,467,468\tDrama\t10.12.1999\t464,465\t63000000");
        Command viewCommand = commandFactory.get("VIEWFILM\t115");
        Command listUserRates =commandFactory.get("LIST\tUSER\t470\tRATES");
        Command listArtistsFrom =commandFactory.get("LIST\tARTISTS\tFROM\tTurkey");
        Command editRate =commandFactory.get("EDIT	RATE	475	109	10");
        Command removeRate =commandFactory.get("REMOVE\tRATE\t470\t109");
        Command listFilmSeries =commandFactory.get("LIST\tFILM\tSERIES");
        Command listFilmsByCountry =commandFactory.get("LIST\tFILMS\tBY\tCOUNTRY\tUSA");
        Command listFilmsBeforeAfter =commandFactory.get("LIST\tFEATUREFILMS\tBEFORE\t1920");
        Command listFilmsByRateDegree =commandFactory.get("LIST\tFILMS\tBY\tRATE\tDEGREE");
        Command listFilmsByRateDegree2 =commandFactory.get("LIST\tFILMS\tBY\tRATE\tDEGREE");
    }

}