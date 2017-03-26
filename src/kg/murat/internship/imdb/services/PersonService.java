package kg.murat.internship.imdb.services;

import kg.murat.internship.imdb.entities.units.Person;

import java.util.Set;

/**
 * Created by Fujitsu on 26.03.2017.
 */
public interface PersonService extends Service{

    void listArtistFromCountry(String command) throws Exception;

    Set<Person> getAll();
}
