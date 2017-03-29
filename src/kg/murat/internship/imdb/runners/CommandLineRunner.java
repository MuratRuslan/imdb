package kg.murat.internship.imdb.runners;

import kg.murat.internship.imdb.commands.Command;
import kg.murat.internship.imdb.dao.FilmRepository;
import kg.murat.internship.imdb.dao.impl.FileFilmRepositoryImpl;
import kg.murat.internship.imdb.dao.impl.FilePersonRepositoryImpl;
import kg.murat.internship.imdb.factories.CommandFactory;
import kg.murat.internship.imdb.services.FilmService;
import kg.murat.internship.imdb.services.PersonService;
import kg.murat.internship.imdb.services.impl.FilmServiceImpl;
import kg.murat.internship.imdb.services.impl.PersonServiceImpl;
import kg.murat.internship.imdb.services.ioServices.IOService;
import kg.murat.internship.imdb.services.ioServices.Logger;
import kg.murat.internship.imdb.services.ioServices.impl.FileIOService;
import kg.murat.internship.imdb.services.ioServices.impl.FileLogger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fujitsu on 26.03.2017.
 */
public class CommandLineRunner implements Runner {
    private String peopleFile;
    private String filmsFile;
    private String commandsFile;
    private String outputFile;
    private FilmService filmService;
    private PersonService personService;
    private Logger logger;

    public CommandLineRunner(String[] args) {
        if (args.length > 0) {
            peopleFile = args[0];
        }
        if (args.length > 1) {
            filmsFile = args[1];
        }
        if (args.length > 2) {
            commandsFile = args[2];
        }
        if (args.length > 3) {
            outputFile = args[3];
        }
        logger = new FileLogger(outputFile);
        personService = new PersonServiceImpl(new FilePersonRepositoryImpl(peopleFile), logger);
        FilmRepository filmRepository = new FileFilmRepositoryImpl(filmsFile, personService.getAll());
        filmService = new FilmServiceImpl(filmRepository, personService.getAll(), logger);
    }


    @Override
    public void run() {
        if (null == peopleFile || null == filmsFile || null == outputFile || null == commandsFile) {
            return;
        }
        List<Command> commands  = scanCommands(commandsFile);
        commands.stream().filter(command -> null != command).forEach(Command::execute);
    }

    public List<Command> scanCommands(String commandsFile) {
        List<Command> commands = new ArrayList<>();
        IOService ioService = new FileIOService(commandsFile);
        CommandFactory commandFactory = new CommandFactory(filmService, personService, logger);
        String next;
        try {
            while (null != (next = ioService.readLine())) {
                commands.add(commandFactory.get(next));
            }
        } catch (IOException e) {
        }
        return commands;
    }
}
