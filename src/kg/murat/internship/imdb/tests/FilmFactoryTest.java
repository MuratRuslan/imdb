package kg.murat.internship.imdb.tests;

import kg.murat.internship.imdb.dao.FilmRepository;
import kg.murat.internship.imdb.dao.PersonRepository;
import kg.murat.internship.imdb.dao.impl.FileFilmRepositoryImpl;
import kg.murat.internship.imdb.dao.impl.FilePersonRepositoryImpl;
import kg.murat.internship.imdb.entities.films.Film;
import kg.murat.internship.imdb.entities.films.interfaces.Writable;
import kg.murat.internship.imdb.entities.units.Person;
import kg.murat.internship.imdb.factories.FilmFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Fujitsu on 28.03.2017.
 */
public class FilmFactoryTest {
    private FilmFactory filmFactory;
    private PersonRepository personRepository;
    private FilmRepository filmRepository;
    private List<Person> personList;

    @Before
    public void init() {
        filmFactory = new FilmFactory();
        personRepository = new FilePersonRepositoryImpl("people.txt");
        personList = personRepository.getAll();
        filmRepository = new FileFilmRepositoryImpl("films.txt", personList);
    }

    @Test
    public void getFilm() throws Exception {
        String command = "ADD\tFEATUREFILM\t116\tRight_Club\tEnglish\t463\t139\tUSA\t466,467,999\tDrama\t10.12.1999\t464,465\t63000000";
        String substring = "FeatureFilm:";
        substring += command.substring(15, command.length());
        Film film = filmFactory.getFilm(substring, personList);
        boolean contains = filmRepository.getAll().contains(film);
        if (contains || null == ((Writable)film).getWriters() || null == film.getDirectors() ||
                null == film.getCast()) {
            return;
        }
        System.out.println("done");
    }

}