package kg.murat.internship.imdb.services;

import kg.murat.internship.imdb.entities.films.Film;
import kg.murat.internship.imdb.entities.films.TVSeries;

import java.util.List;

/**
 * Created by Fujitsu on 24.03.2017.
 */
public interface FilmService {

    void rateFilm(String command);

    void editRatedFilm(String command);

    void removeRate(String command);

    void addFeatureFilm(String command);

    Film viewFilm(String command);

    List<Film> listFilmsByCountry(String command);

    List<Film> listFilmsUserRated(String command);

    List<TVSeries> listAllTVSeries(String command);

    List<Film> listAllFilmsBeforeAfter(String command);

    void listAllSortedFilmsByRateDesc(String command);
}
