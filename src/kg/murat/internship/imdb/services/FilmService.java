package kg.murat.internship.imdb.services;

import kg.murat.internship.imdb.entities.films.Film;
import kg.murat.internship.imdb.entities.films.TVSeries;

import java.util.List;

/**
 * Created by Fujitsu on 24.03.2017.
 */
public interface FilmService extends Service {

    void rateFilm(String command) throws Exception;

    void editRatedFilm(String command) throws Exception;

    void removeRate(String command) throws Exception;

    void addFeatureFilm(String command) throws Exception;

    Film viewFilm(String command) throws Exception;

    List<Film> listFilmsByCountry(String command) throws Exception;

    List<Film> listFilmsUserRated(String command) throws Exception;

    List<TVSeries> listAllTVSeries(String command) throws Exception;

    List<Film> listAllFilmsBeforeAfter(String command) throws Exception;

    void listAllSortedFilmsByRateDesc(String command) throws Exception;
}
