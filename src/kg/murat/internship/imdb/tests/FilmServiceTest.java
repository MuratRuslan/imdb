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
import org.junit.Test;

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
        personRepository = new FilePersonRepositoryImpl("people.txt");
        filmRepository = new FileFilmRepositoryImpl("films.txt", personRepository);
        logger = new FileLogger("output.txt");
        filmService = new FilmServiceImpl(filmRepository, personRepository, logger);
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void rateFilm() throws Exception {
        filmService.rateFilm("rate film 480 110 9");
        filmService.rateFilm("rate film 480 110 9");
        filmService.rateFilm("rate film 480 110 8");
    }

    @org.junit.Test
    public void editRatedFilm() throws Exception {
        filmService.rateFilm("rate film 480 110 9");
        filmService.editRatedFilm("edit rate film 480 110 8");
        filmService.editRatedFilm("edit rate film 480 110 9");
    }

    @org.junit.Test
    public void removeRate() throws Exception {
        filmService.rateFilm("rate film 480 110 9");
        filmService.removeRate("remove rate film 480 110");
        filmService.removeRate("remove rate film 480 110");
    }

    @org.junit.Test
    public void addFeatureFilm() throws Exception {
        filmService.addFeatureFilm("ADD\tFEATUREFILM\t115\tFight_Club\tEnglish\t463\t139\tUSA\t466,467,468\tDrama\t10.12.1999\t464,465\t63000000");
        filmService.addFeatureFilm("ADD\tFEATUREFILM\t115\tFight_Club\tEnglish\t463\t139\tUSA\t466,467,468\tDrama\t10.12.1999\t464,465\t63000000");
    }

    @org.junit.Test
    public void viewFilm() throws Exception {
        filmService.rateFilm("rate film");
        filmService.viewFilm("View film");
        filmService.viewFilm("view film");
        filmService.viewFilm("view film");
        filmService.viewFilm("view film");
    }

    @org.junit.Test
    public void listFilmsByCountry() throws Exception {
        filmService.listFilmsByCountry("list film by country");
    }

    @org.junit.Test
    public void listFilmsUserRated() throws Exception {
        filmService.rateFilm("rate film");
        filmService.rateFilm("rate film");
        filmService.rateFilm("rate film");
        filmService.rateFilm("rate film");
        filmService.listFilmsUserRated("list films user rated");
    }

    @org.junit.Test
    public void listAllTVSeries() throws Exception {
        filmService.listAllTVSeries("list all tv series");
    }

    @org.junit.Test
    public void listAllFilmsBeforeAfter() throws Exception {
        filmService.listAllFilmsBeforeAfter("LIST\tFEATUREFILMS\tbefore\t1995");
        filmService.listAllFilmsBeforeAfter("LIST\tFEATUREFILMS\tafter\t1995");
    }

    @org.junit.Test
    public void listAllSortedFilmsByRateDesc() throws Exception {
        filmService.rateFilm("rate film");
        filmService.rateFilm("rate film");
        filmService.rateFilm("rate film");
        filmService.rateFilm("rate film");
        filmService.rateFilm("rate film");
        filmService.rateFilm("rate film");
        filmService.rateFilm("rate film");
        filmService.listAllSortedFilmsByRateDesc("list films by rate desc");
    }

    @Test
    public void globalTest() throws Exception {
        filmService.rateFilm("RATE\t470\t113\t9");
        filmService.rateFilm("RATE\t470\t113\t9");
        filmService.rateFilm("RATE\t454\t113\t8");
        filmService.addFeatureFilm("ADD\tFEATUREFILM\t115\tFight_Club\tEnglish\t463\t139\tUSA\t466,467,468\tDrama\t10.12.1999\t464,465\t63000000");
        filmService.addFeatureFilm("ADD\tFEATUREFILM\t115\tFight_Club\tEnglish\t463\t139\tUSA\t466,467,468\tDrama\t10.12.1999\t464,465\t63000000");
        filmService.addFeatureFilm("ADD\tFEATUREFILM\t116\tRight_Club\tEnglish\t463\t139\tUSA\t466,467,999\tDrama\t10.12.1999\t464,465\t63000000");
        filmService.rateFilm("RATE\t475\t115\t10");
        filmService.rateFilm("RATE\t477\t115\t9");
        filmService.rateFilm("RATE\t480\t111\t9");
        filmService.rateFilm("RATE\t477\t111\t7");
        filmService.rateFilm("RATE\t476\t114\t10");
        filmService.rateFilm("RATE\t474\t114\t10");
        filmService.rateFilm("RATE\t473\t114\t9");
        filmService.rateFilm("RATE\t472\t112\t8");
        filmService.rateFilm("RATE\t472\t103\t8");
        filmService.rateFilm("RATE\t472\t101\t6");
        filmService.rateFilm("RATE\t471\t102\t10");
        filmService.rateFilm("RATE\t479\t102\t9");
        filmService.rateFilm("RATE\t478\t100\t9");
        filmService.rateFilm("RATE\t478\t109\t9");
        filmService.rateFilm("RATE\t476\t104\t9");
        filmService.rateFilm("RATE\t473\t106\t9");
        filmService.rateFilm("RATE\t472\t106\t8");
        filmService.rateFilm("RATE\t471\t106\t7");
        filmService.rateFilm("RATE\t478\t106\t10");
        filmService.viewFilm("VIEWFILM\t115");
        filmService.viewFilm("VIEWFILM\t101");
        filmService.rateFilm("RATE\t470\t115\t9");
        filmService.rateFilm("RATE\t479\t115\t8");
        filmService.rateFilm("RATE\t478\t115\t8");
        filmService.rateFilm("RATE\t470\t108\t8");
        filmService.viewFilm("VIEWFILM\t101");
        filmService.listFilmsUserRated("LIST\tUSER\t470\tRATES");
        filmService.listFilmsUserRated("LIST\tUSER\t476\tRATES");
        filmService.listFilmsUserRated("LIST\tUSER\t4744\tRATES");
        filmService.editRatedFilm("EDIT\tRATE\t470\t115\t10");
        filmService.editRatedFilm("EDIT\tRATE\t470\t109\t10");
        filmService.viewFilm("VIEWFILM\t103");
        filmService.editRatedFilm("EDIT\tRATE\t470\t222\t10");
        filmService.listAllFilmsBeforeAfter("LIST\tFEATUREFILMS\tAFTER\t1995");
        filmService.editRatedFilm("EDIT\tRATE\t475\t109\t10");
        filmService.viewFilm("VIEWFILM\t108");
        filmService.rateFilm("RATE\t470\t100\t10");
        filmService.rateFilm("RATE\t473\t113\t8");
        filmService.removeRate("REMOVE\tRATE\t470\t108");
        filmService.viewFilm("VIEWFILM\t108");
        filmService.removeRate("REMOVE\tRATE\t470\t109");
        filmService.listFilmsUserRated("LIST\tUSER\t470\tRATES");
        filmService.listAllTVSeries("LIST\tFILM\tSERIES");
        filmService.viewFilm("VIEWFILM\t106");
        filmService.listFilmsByCountry("LIST\tFILMS\tBY\tCOUNTRY\tJapan");
        filmService.listFilmsByCountry("LIST\tFILMS\tBY\tCOUNTRY\tUSA");
        filmService.listAllFilmsBeforeAfter("LIST\tFEATUREFILMS\tBEFORE\t1995");
        filmService.listAllFilmsBeforeAfter("LIST\tFEATUREFILMS\tBEFORE\t1920");
        filmService.listAllSortedFilmsByRateDesc("LIST\tFILMS\tBY\tRATE\tDEGREE");
    }
}