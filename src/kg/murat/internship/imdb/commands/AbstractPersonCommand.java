package kg.murat.internship.imdb.commands;

import kg.murat.internship.imdb.services.PersonService;
import kg.murat.internship.imdb.services.ioServices.Logger;

/**
 * Created by Fujitsu on 26.03.2017.
 */
public abstract class AbstractPersonCommand implements Command {
    protected static final String COMMAND_FAILED_MSG = "Command failed";
    protected PersonService personService;
    protected String command;
    protected Logger logger;

    public AbstractPersonCommand(PersonService personService, String command, Logger logger) {
        this.personService = personService;
        this.command = command;
        this.logger = logger;
    }

}
