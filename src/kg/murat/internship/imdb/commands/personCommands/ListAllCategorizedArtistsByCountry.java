package kg.murat.internship.imdb.commands.personCommands;

import kg.murat.internship.imdb.commands.AbstractPersonCommand;
import kg.murat.internship.imdb.services.PersonService;
import kg.murat.internship.imdb.services.ioServices.Logger;

/**
 * Created by Fujitsu on 26.03.2017.
 */
public class ListAllCategorizedArtistsByCountry extends AbstractPersonCommand {
    public ListAllCategorizedArtistsByCountry(PersonService personService, String command, Logger logger) {
        super(personService, command, logger);
    }

    @Override
    public void execute() {
        try {
            personService.listArtistFromCountry(command);
        } catch (Exception e) {
            logger.log(command, COMMAND_FAILED_MSG);
        }
    }
}
