package kg.murat.internship.imdb.services;

import kg.murat.internship.imdb.entities.films.FeatureFilm;
import kg.murat.internship.imdb.entities.films.Film;
import kg.murat.internship.imdb.entities.films.TVSeries;

import java.util.List;

/**
 * Created by Fujitsu on 24.03.2017.
 */
public interface FilmService {
    void rateFilm(String command, Long userId, Long filmId, Integer rate);
    void editRatedFilm(String command, Long userId, Long filmId, Integer rate);
    void removeRate(String command, Long userId, Long filmId);
    void addFeatureFilm(String command, FeatureFilm featureFilm);
    Film viewFilm(String command, Long id);
    List<Film> listFilmsByCountry(String command, String country);
    List<Film> listFilmsUserRated(String command, Long userID);
    List<TVSeries> listAllTVSeries(String command);
    List<Film> listAllFilmsBeforeAfter(String command, Integer year);
    void listAllSortedFilmsByRateDesc(String command);
}
