package kg.murat.internship.imdb.dao;

import kg.murat.internship.imdb.entities.films.Film;

/**
 * Created by Fujitsu on 24.03.2017.
 */
public interface FilmRepository extends Repository<Film> {
    boolean addFilm(Film film);
}
