package kg.murat.internship.imdb.services;

import kg.murat.internship.imdb.entities.films.FeatureFilm;
import kg.murat.internship.imdb.entities.films.Film;
import kg.murat.internship.imdb.entities.units.User;

/**
 * Created by Fujitsu on 24.03.2017.
 */
public interface FilmService {
    boolean rateFilm(Long userId, Long filmId, Integer rate);
    boolean removeRate(Long userId, Long filmId);
    boolean addFeatureFilm(String command);
    void viewFilm(Long id);
    void listFilmsByCountry(String country);
}
