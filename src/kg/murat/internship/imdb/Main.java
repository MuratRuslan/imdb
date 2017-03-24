package kg.murat.internship.imdb;

import kg.murat.internship.imdb.dao.FilmRepository;
import kg.murat.internship.imdb.dao.PersonRepository;
import kg.murat.internship.imdb.dao.impl.FileFilmRepositoryImpl;
import kg.murat.internship.imdb.dao.impl.FilePersonRepositoryImpl;
import kg.murat.internship.imdb.entities.films.FeatureFilm;
import kg.murat.internship.imdb.entities.films.Film;
import kg.murat.internship.imdb.entities.units.Person;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        PersonRepository personRepository = new FilePersonRepositoryImpl("people.txt", "people.txt");
        FilmRepository filmRepository = new FileFilmRepositoryImpl("films.txt", "films.txt", personRepository);
        FeatureFilm film = (FeatureFilm) filmRepository.getById(109L);
        FeatureFilm featureFilm = new FeatureFilm(115L, "Fight_night", "Eng", "USA", 181);
        featureFilm.setBudget(23424L);
        featureFilm.setReleaseDate(new Date());
        Set<String> genre = new HashSet<>();
        genre.add("Roman");
        genre.add("Drama");
        featureFilm.setGenre(genre);
        featureFilm.setWriters(film.getWriters());
        featureFilm.setDirectors(film.getDirectors());
        featureFilm.setCast(film.getCast());
        filmRepository.addFilm(featureFilm);
        List<Film> all = filmRepository.getAll();
        for (Film fil : all) {
            System.out.println(fil.getId());
        }
    }
}
