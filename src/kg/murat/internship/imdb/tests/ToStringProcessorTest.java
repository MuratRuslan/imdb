package kg.murat.internship.imdb.tests;

import kg.murat.internship.imdb.dao.FilmRepository;
import kg.murat.internship.imdb.dao.PersonRepository;
import kg.murat.internship.imdb.dao.impl.FileFilmRepositoryImpl;
import kg.murat.internship.imdb.dao.impl.FilePersonRepositoryImpl;
import kg.murat.internship.imdb.entities.films.Film;
import kg.murat.internship.imdb.entities.units.Person;
import kg.murat.internship.imdb.processors.ToStringProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Fujitsu on 28.03.2017.
 */
public class ToStringProcessorTest {
    private PersonRepository personRepository;
    private FilmRepository filmRepository;

    @Before
    public void setUp() throws Exception {
        personRepository = new FilePersonRepositoryImpl("people.txt");
        filmRepository = new FileFilmRepositoryImpl("films.txt", (Collection) personRepository.getAll());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void personsToString() throws Exception {
        Film featureFilm = filmRepository.getById(100L);
        Film shortFilm = filmRepository.getById(101L);
        Film tvSeries = filmRepository.getById(102L);
        Film documentary = filmRepository.getById(103L);
        System.out.println(ToStringProcessor.filmToStringLog(featureFilm));
        System.out.println();
        System.out.println(ToStringProcessor.filmToStringLog(shortFilm));
        System.out.println();
        System.out.println(ToStringProcessor.filmToStringLog(tvSeries));
        System.out.println();
        System.out.println(ToStringProcessor.filmToStringLog(documentary));
    }

    @Test
    public void collectionStringsToString() throws Exception {

    }

    @Test
    public void filmToStringLog() throws Exception {

    }

    @Test
    public void personsNameAndSurnameToString() throws Exception {

    }

    @Test
    public void filmShortInfoToString() throws Exception {

    }

    @Test
    public void filmsTitleYearRatingToString() throws Exception {
        String result = ToStringProcessor.filmsTitleYearRatingToString((Collection) filmRepository.getAll());
        System.out.println(result);
    }

    @Test
    public void getAvgRating() throws Exception {
        float avg = ToStringProcessor.getAvgRating((Collection) personRepository.getAll());
        System.out.println(avg);
    }

    @Test
    public void personToShortString() throws Exception {

    }

}