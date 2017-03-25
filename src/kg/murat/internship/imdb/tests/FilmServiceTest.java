package kg.murat.internship.imdb.tests;

import kg.murat.internship.imdb.dao.FilmRepository;
import kg.murat.internship.imdb.dao.PersonRepository;
import kg.murat.internship.imdb.dao.impl.FileFilmRepositoryImpl;
import kg.murat.internship.imdb.dao.impl.FilePersonRepositoryImpl;
import kg.murat.internship.imdb.entities.films.FeatureFilm;
import kg.murat.internship.imdb.services.FilmService;
import kg.murat.internship.imdb.services.FilmServiceImpl;
import kg.murat.internship.imdb.services.ioServices.Logger;
import kg.murat.internship.imdb.services.ioServices.impl.FileLogger;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Created by Fujitsu on 25.03.2017.
 */
public class FilmServiceTest {
    PersonRepository personRepository;
    FilmRepository filmRepository;
    Logger logger;
    FilmService filmService;

    @org.junit.Before
    public void setUp() throws Exception {
        personRepository = new FilePersonRepositoryImpl("people.txt", "people.txt");
        filmRepository = new FileFilmRepositoryImpl("films.txt", "films.txt", personRepository);
        logger = new FileLogger("output.txt");
        filmService = new FilmServiceImpl(filmRepository, personRepository, logger);
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void rateFilm() throws Exception {
        filmService.rateFilm("rate film 480 110 9", 480L, 110L, 9);
        filmService.rateFilm("rate film 480 110 9", 480L, 110L, 9);
        filmService.rateFilm("rate film 480 110 8", 480L, 110L, 8);
    }

    @org.junit.Test
    public void editRatedFilm() throws Exception {
        filmService.rateFilm("rate film 480 110 9", 480L, 110L, 9);
        filmService.editRatedFilm("edit rate film 480 110 8", 480L, 110L, 8);
        filmService.editRatedFilm("edit rate film 480 110 9", 480L, 200L, 9);
    }

    @org.junit.Test
    public void removeRate() throws Exception {
        filmService.rateFilm("rate film 480 110 9", 480L, 110L, 9);
        filmService.removeRate("remove rate film 480 110", 480L, 110L);
        filmService.removeRate("remove rate film 480 110", 480L, 110L);
    }

    @org.junit.Test
    public void addFeatureFilm() throws Exception {
        FeatureFilm featureFilm = (FeatureFilm) filmRepository.getById(109L);
        FeatureFilm newFilm = new FeatureFilm(116L, "Kok_salkyn", "KYR", "KG", 160);
        newFilm.setGenre(featureFilm.getGenre());
        newFilm.setBudget(1102929L);
        newFilm.setWriters(featureFilm.getWriters());
        newFilm.setCast(featureFilm.getCast());
        newFilm.setDirectors(featureFilm.getDirectors());
        Calendar calendar = new GregorianCalendar(2002, 01, 01);
        newFilm.setReleaseDate(calendar.getTime());
        filmService.addFeatureFilm("Add feature film", newFilm);
        filmService.addFeatureFilm("Add feature film", newFilm);
    }

    @org.junit.Test
    public void viewFilm() throws Exception {
        filmService.rateFilm("rate film", 480L, 110L, 9);
        filmService.viewFilm("View film", 110L);
    }

    @org.junit.Test
    public void listFilmsByCountry() throws Exception {
        filmService.listFilmsByCountry("list film by country", "USA");
    }

    @org.junit.Test
    public void listFilmsUserRated() throws Exception {
        filmService.rateFilm("rate film", 480L, 110L, 9);
        filmService.rateFilm("rate film", 480L, 111L, 9);
        filmService.rateFilm("rate film", 480L, 109L, 9);
        filmService.rateFilm("rate film", 480L, 112L, 9);
        filmService.listFilmsUserRated("list films user rated", 480L);
    }

    @org.junit.Test
    public void listAllTVSeries() throws Exception {
        filmService.listAllTVSeries("list all tv series");
    }

    @org.junit.Test
    public void listAllFilmsBeforeAfter() throws Exception {
        filmService.listAllFilmsBeforeAfter("LIST\tFEATUREFILMS\tbefore\t1995", 1995);
        filmService.listAllFilmsBeforeAfter("LIST\tFEATUREFILMS\tafter\t1995", 1995);
    }

    @org.junit.Test
    public void listAllSortedFilmsByRateDesc() throws Exception {
        filmService.rateFilm("rate film", 480L, 112L, 9);
        filmService.rateFilm("rate film", 480L, 111L, 9);
        filmService.rateFilm("rate film", 470L, 112L, 9);
        filmService.rateFilm("rate film", 480L, 113L, 9);
        filmService.rateFilm("rate film", 479L, 112L, 9);
        filmService.rateFilm("rate film", 478L, 110L, 9);
        filmService.rateFilm("rate film", 480L, 113L, 9);
        filmService.listAllSortedFilmsByRateDesc("list films by rate desc");
    }

}