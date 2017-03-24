package kg.murat.internship.imdb.services;

import kg.murat.internship.imdb.dao.FilmRepository;
import kg.murat.internship.imdb.dao.PersonRepository;
import kg.murat.internship.imdb.entities.films.FeatureFilm;
import kg.murat.internship.imdb.entities.films.Film;
import kg.murat.internship.imdb.entities.films.ShortFilm;
import kg.murat.internship.imdb.entities.films.TVSeries;
import kg.murat.internship.imdb.entities.units.Person;
import kg.murat.internship.imdb.entities.units.User;
import kg.murat.internship.imdb.services.ioServices.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Fujitsu on 24.03.2017.
 */
public class FilmServiceImpl implements FilmService {
    private static final String COMMAND_FAILED = "Command Failed";
    private FilmRepository filmRepository;
    private PersonRepository personRepository;
    private Logger logger;

    public FilmServiceImpl(FilmRepository filmRepository, PersonRepository personRepository, Logger logger) {
        this.filmRepository = filmRepository;
        this.personRepository = personRepository;
        this.logger = logger;
    }

    @Override
    public void rateFilm(String command, Long userId, Long filmId, Integer rate) {
        Person person = personRepository.getById(userId);
        Film film = filmRepository.getById(filmId);

        if (!(person instanceof User) || null == film) {
            logger.log(command, COMMAND_FAILED + "\n" +
                    "User ID: " + userId + "\n" +
                    "Film ID: " + filmId);
            return;
        }

        if (containsPerson(person, (Set)film.getRating().keySet())) {
            logger.log(command, "This film was earlier rated");
            return;
        }

        film.getRating().put((User) person, rate);
        logger.log(command, "Film rated successfully\n" +
                "Film type: " + film.getClass().getSimpleName() + "\n" +
                "Film title: " + film.getTitle());
    }

    @Override
    public void removeRate(String command, Long userId, Long filmId) {
        Person person = personRepository.getById(userId);
        Film film = filmRepository.getById(filmId);

        if(null == film || null == person || !containsPerson(person ,(Set)film.getRating().keySet())) {
            logger.log(command, COMMAND_FAILED + "\n" +
                    "User ID: " + userId + "\n" +
                    "Film ID: " + filmId);
            return;
        }

        logger.log(command, "Your film rating was removed successfully\n" +
                "Film title: " + film.getTitle());
    }

    @Override
    public void addFeatureFilm(String command, FeatureFilm featureFilm) {
        Film film = filmRepository.getById(featureFilm.getId());

        if (null != film || null == featureFilm.getWriters() || null == featureFilm.getDirectors() ||
                null == featureFilm.getCast()) {
            logger.log(command, COMMAND_FAILED + "\n" +
                    "Film ID: " + featureFilm.getId() + "\n" +
                    "Film title " + featureFilm.getTitle());
            return;
        }

        filmRepository.addFilm(film);

        logger.log(command, "FeatureFilm added successfully\n" +
                "Film ID: " + featureFilm.getId() + "\n" +
                "Film title: " + featureFilm.getTitle());
    }

    @Override
    public Film viewFilm(String command, Long filmId) {
        Film film = filmRepository.getById(filmId);

        if(null == film) {
            logger.log(command, COMMAND_FAILED + "\n" +
                    "Film ID: " + filmId);
            return null;
        }

        logger.log(command, ToStringService.filmToStringLog(film));
        return film;
    }

    @Override
    public List<Film> listFilmsByCountry(String command, String country) {
        List<Film> films = new ArrayList<>();
        String
        for(Film film : filmRepository.getAll()) {
            if(film.getCountry().equalsIgnoreCase(country)) {
                films.add(film);
            }
        }
        return films;
    }

    @Override
    public List<Film> listFilmsUserRated(String command, Long userID) {
        return null;
    }

    @Override
    public List<TVSeries> listAllTVSeries(String command) {
        return null;
    }

    @Override
    public List<Film> listAllFilmsBefore(String command, Integer year) {
        return null;
    }

    @Override
    public List<Film> listAllFilmsAfter(String command, Integer year) {
        return null;
    }

    @Override
    public Set<Film> listAllSortedFilmsByRateDesc(String command) {
        return null;
    }


    private Boolean containsPerson(Person person, Collection<Person> collection) {
        for (Person currentPerson : collection) {
            if (person.getId().equals(currentPerson.getId())) {
                return true;
            }
        }
        return false;
    }


}
