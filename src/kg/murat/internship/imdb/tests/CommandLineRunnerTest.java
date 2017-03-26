package kg.murat.internship.imdb.tests;

import kg.murat.internship.imdb.commands.Command;
import kg.murat.internship.imdb.dao.FilmRepository;
import kg.murat.internship.imdb.dao.PersonRepository;
import kg.murat.internship.imdb.dao.impl.FileFilmRepositoryImpl;
import kg.murat.internship.imdb.dao.impl.FilePersonRepositoryImpl;
import kg.murat.internship.imdb.factories.CommandFactory;
import kg.murat.internship.imdb.runners.CommandLineRunner;
import kg.murat.internship.imdb.services.FilmService;
import kg.murat.internship.imdb.services.PersonService;
import kg.murat.internship.imdb.services.impl.FilmServiceImpl;
import kg.murat.internship.imdb.services.impl.PersonServiceImpl;
import kg.murat.internship.imdb.services.ioServices.Logger;
import kg.murat.internship.imdb.services.ioServices.impl.FileLogger;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Fujitsu on 26.03.2017.
 */
public class CommandLineRunnerTest {
    Logger logger = new FileLogger("output.txt");
    PersonRepository personRepository = new FilePersonRepositoryImpl("people.txt");
    PersonService personService = new PersonServiceImpl(personRepository, logger);
    FilmRepository filmRepository = new FileFilmRepositoryImpl("films.txt", personService.getAll());
    FilmService filmService = new FilmServiceImpl(filmRepository, personService.getAll(), logger);
    CommandFactory commandFactory = new CommandFactory(filmService, personService, logger);
    CommandLineRunner commandLineRunner;

    @Before
    public void setUp() throws Exception {
        commandLineRunner = new CommandLineRunner(new String[] {"people.txt", "films.txt", "commands.txt", "output.txt"});
    }

    @Test
    public void run() throws Exception {

    }

    @Test
    public void scanCommands() throws Exception {
        List<Command> commands = commandLineRunner.scanCommands("commands.txt");
        System.out.println(commands.size());
    }

}