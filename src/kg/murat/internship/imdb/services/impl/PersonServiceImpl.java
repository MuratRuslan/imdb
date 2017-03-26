package kg.murat.internship.imdb.services.impl;

import kg.murat.internship.imdb.dao.PersonRepository;
import kg.murat.internship.imdb.entities.units.Person;
import kg.murat.internship.imdb.entities.units.artists.Director;
import kg.murat.internship.imdb.entities.units.artists.Writer;
import kg.murat.internship.imdb.entities.units.artists.performers.Actor;
import kg.murat.internship.imdb.entities.units.artists.performers.Actress;
import kg.murat.internship.imdb.processors.ToStringProcessor;
import kg.murat.internship.imdb.services.PersonService;
import kg.murat.internship.imdb.services.ioServices.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Fujitsu on 26.03.2017.
 */
public class PersonServiceImpl implements PersonService {
    private PersonRepository personRepository;
    private Logger logger;
    private Set<Person> personSet;

    public PersonServiceImpl(PersonRepository personRepository, Logger logger) {
        this.personRepository = personRepository;
        this.logger = logger;
        personSet = new HashSet<>(personRepository.getAll());
    }

    @Override
    public void listArtistFromCountry(String command) {
        String country = command.split("\\t")[3];
        Set<Writer> writers = new HashSet<>();
        Set<Director> directors = new HashSet<>();
        Set<Actor> actors = new HashSet<>();
        Set<Actress> actresses = new HashSet<>();

        personSet.stream().filter(person -> person.getCountry().equalsIgnoreCase(country)).forEach(person -> {
            if (person instanceof Writer) {
                writers.add((Writer) person);
            }
            if (person instanceof Director) {
                directors.add((Director) person);
            }
            if (person instanceof Actor) {
                actors.add((Actor) person);
            }
            if (person instanceof Actress) {
                actresses.add((Actress) person);
            }
        });

        if (directors.isEmpty() && writers.isEmpty() && actors.isEmpty() && actresses.isEmpty()) {
            logger.log(command, "No result");
            return;
        }

        String result = "Directors:" + ToStringProcessor.personToShortString((Set) directors) +
                "\n\nWriters:" + ToStringProcessor.personToShortString((Set) writers) +
                "\n\nActors:" + ToStringProcessor.personToShortString((Set) actors) +
                "\n\nActresses:" + ToStringProcessor.personToShortString((Set) actresses);
        logger.log(command, result);
    }

    public Set<Person> getAll() {
        return personSet;
    }
}
