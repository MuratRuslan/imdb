package kg.murat.internship.imdb.tests;

import kg.murat.internship.imdb.dao.impl.FilePersonRepositoryImpl;
import kg.murat.internship.imdb.services.PersonService;
import kg.murat.internship.imdb.services.impl.PersonServiceImpl;
import kg.murat.internship.imdb.services.ioServices.impl.FileLogger;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Fujitsu on 26.03.2017.
 */
public class PersonServiceTest {
    PersonService personService;

    @Before
    public void setUp() throws Exception {
        personService = new PersonServiceImpl(new FilePersonRepositoryImpl("people.txt"),
                new FileLogger("output.txt"));

    }

    @Test
    public void listArtistFromCountry() throws Exception {
        personService.listArtistFromCountry("LIST\tARTISTS\tFROM\tUSA");
    }

}