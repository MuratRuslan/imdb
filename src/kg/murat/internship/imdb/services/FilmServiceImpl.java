package kg.murat.internship.imdb.services;

import kg.murat.internship.imdb.dao.FilmRepository;
import kg.murat.internship.imdb.dao.PersonRepository;
import kg.murat.internship.imdb.entities.films.*;
import kg.murat.internship.imdb.entities.films.interfaces.Releasable;
import kg.murat.internship.imdb.entities.units.Person;
import kg.murat.internship.imdb.entities.units.User;
import kg.murat.internship.imdb.services.ioServices.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Fujitsu on 24.03.2017.
 */
public class FilmServiceImpl implements FilmService {
    private static final String COMMAND_FAILED_MSG = "Command Failed";
    private FilmRepository filmRepository;
    private PersonRepository personRepository;
    private Logger logger;
    private Set<Person> personSet;
    private Set<Film> filmSet;

    public FilmServiceImpl(FilmRepository filmRepository, PersonRepository personRepository, Logger logger) {
        this.filmRepository = filmRepository;
        this.personRepository = personRepository;
        this.logger = logger;
        personSet = new HashSet<>(personRepository.getAll());
        filmSet = new HashSet<>(filmRepository.getAll());
    }

    @Override
    public void rateFilm(String command) {
        String[] split = command.split("\\t");
        Long userId = Long.parseLong(split[1]);
        Long filmId = Long.parseLong(split[2]);
        Integer rate = Integer.parseInt(split[3]);
        Person person = getPersonById(userId);
        Film film = getFilmById(filmId);
        if (!(person instanceof User) || null == film) {
            logger.log(command, COMMAND_FAILED_MSG + "\n" +
                    "User ID: " + userId + "\n" +
                    "Film ID: " + filmId);
            return;
        }

        if (film.getRating().containsKey(person)) {
            logger.log(command, "This film was earlier rated");
            return;
        }

        film.getRating().put((User) person, rate);
        logger.log(command, "Film rated successfully\n" +
                "Film type: " + film.getClass().getSimpleName() + "\n" +
                "Film title: " + film.getTitle());
    }

    @Override
    public void editRatedFilm(String command) {
        String[] split = command.split("\\t");
        Long userId = Long.parseLong(split[2]);
        Long filmId = Long.parseLong(split[3]);
        Integer rate = Integer.parseInt(split[4]);
        Person person = getPersonById(userId);
        Film film = getFilmById(filmId);

        if (!(person instanceof User) || null == film || !film.getRating().containsKey(person)) {
            logger.log(command, COMMAND_FAILED_MSG + "\n" +
                    "User ID: " + userId + "\n" +
                    "Film ID: " + filmId);
            return;
        }

        film.getRating().put((User) person, rate);
        logger.log(command, "New ratings done successfully\n" +
                "Film title: " + film.getTitle() + "\n" +
                "Your rating: " + rate);
    }

    @Override
    public void removeRate(String command) {
        String[] split = command.split("\\t");
        Long userId = Long.parseLong(split[2]);
        Long filmId = Long.parseLong(split[3]);
        Person person = getPersonById(userId);
        Film film = getFilmById(filmId);

        if (null == film || null == person || !film.getRating().containsKey(person)) {
            logger.log(command, COMMAND_FAILED_MSG + "\n" +
                    "User ID: " + userId + "\n" +
                    "Film ID: " + filmId);
            return;
        }
        film.getRating().remove(person);
        logger.log(command, "Your film rating was removed successfully\n" +
                "Film title: " + film.getTitle());
    }

    @Override
    public void addFeatureFilm(String command) {
        String featureFilmString = "FeatureFilm:" + command.substring(15, command.length());
        FeatureFilm film;
        try {
            film = (FeatureFilm) new FilmFactory().getFilm(featureFilmString, personSet);
        } catch (ParseException e) {
            film = null;
        }

        if (filmSet.contains(film) || null == film.getWriters() || null == film.getDirectors() ||
                null == film.getCast()) {
            logger.log(command, COMMAND_FAILED_MSG + "\n" +
                    "Film ID: " + film.getId() + "\n" +
                    "Film title " + film.getTitle());
            return;
        }

        filmRepository.addFilm(film);
        filmSet.add(film);

        logger.log(command, "FeatureFilm added successfully\n" +
                "Film ID: " + film.getId() + "\n" +
                "Film title: " + film.getTitle());
    }

    @Override
    public Film viewFilm(String command) {
        String[] split = command.split("\\t");
        Long filmId = Long.parseLong(split[1]);
        Film film = getFilmById(filmId);

        if (null == film) {
            logger.log(command, COMMAND_FAILED_MSG + "\n" +
                    "Film ID: " + filmId);
            return null;
        }

        logger.log(command, ToStringService.filmToStringLog(film));
        return film;
    }

    @Override
    public List<Film> listFilmsByCountry(String command) {
        String[] split = command.split("\\t");
        String country = split[4];
        List<Film> films = new ArrayList<>();
        String result = "";

        for (Film film : filmSet) {
            if (film.getCountry().equalsIgnoreCase(country)) {
                films.add(film);
                result += "Film title: " + film.getTitle() + "\n" +
                        film.getLength() + " min\n" +
                        "Language: " + film.getLanguage() + "\n\n";
            }
        }

        if (films.isEmpty()) {
            logger.log(command, "No result");
            return Collections.emptyList();
        }

        logger.log(command, result.trim());
        return films;
    }

    @Override
    public List<Film> listFilmsUserRated(String command) {
        String[] split = command.split("\\t");
        Long userID = Long.parseLong(split[2]);
        List<Film> films = new ArrayList<>();
        Person person = getPersonById(userID);
        String result = "";

        for (Film film : filmSet) {
            if (film.getRating().containsKey(person)) {
                films.add(film);
                result += "\n" + film.getTitle() + ": " + film.getRating().get(person);
            }
        }

        if (!(person instanceof User) || films.isEmpty()) {
            logger.log(command, COMMAND_FAILED_MSG + "\n" +
                    "User ID: " + userID);
            return Collections.emptyList();
        }

        logger.log(command, result.trim());
        return films;
    }

    @Override
    public List<TVSeries> listAllTVSeries(String command) {
        List<TVSeries> tvSeries = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String result = "";
        for (Film film : filmSet) {
            if (film instanceof TVSeries) {
                tvSeries.add((TVSeries) film);
                result += film.getTitle() + " (" +
                        dateFormat.format(((TVSeries) film).getStartDate()) + "-" +
                        dateFormat.format(((TVSeries) film).getEndDate()) + ")\n" +
                        ((TVSeries) film).getNumberOfSeasons() + " seasons and " +
                        ((TVSeries) film).getNumberOfEpisodes() + " episodes\n\n";
            }
        }

        if (tvSeries.isEmpty()) {
            logger.log(command, "No result");
            return Collections.emptyList();
        }

        logger.log(command, result.trim());
        return tvSeries;
    }

    @Override
    public List<Film> listAllFilmsBeforeAfter(String command) {
        String[] split = command.split("\\t");
        Integer year = Integer.parseInt(split[3]);
        List<Film> filmsBefore = new ArrayList<>();
        List<Film> filmsAfter = new ArrayList<>();
        String beforeAfter = command.split("\\t")[2];
        String resultBefore = "";
        String resultAfter = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        Calendar calendar = new GregorianCalendar();
        calendar.set(year, 1, 1);
        Date date = calendar.getTime();

        for (Film film : filmRepository.getAll()) {
            if (film instanceof Releasable && ((Releasable) film).getReleaseDate().before(date)) {
                filmsBefore.add(film);
                resultBefore += ToStringService.filmListShortInfoToString(film) + "\n\n";
            }
            if (film instanceof Releasable && ((Releasable) film).getReleaseDate().after(date)) {
                filmsAfter.add(film);
                resultAfter += ToStringService.filmListShortInfoToString(film) + "\n\n";
            }
        }

        if (beforeAfter.equalsIgnoreCase("before") && filmsBefore.isEmpty() ||
                beforeAfter.equalsIgnoreCase("after") && filmsAfter.isEmpty()) {
            logger.log(command, "No result");
            return Collections.emptyList();
        }

        if (beforeAfter.equalsIgnoreCase("before")) {
            logger.log(command, resultBefore.trim());
            return filmsBefore;
        }
        logger.log(command, resultAfter.trim());
        return filmsAfter;
    }

    @Override
    public void listAllSortedFilmsByRateDesc(String command) {
        Set<Film> featureFilms = new TreeSet<>(new FilmComparator().reversed());
        Set<Film> shortFilms = new TreeSet<>(new FilmComparator().reversed());
        Set<Film> documentaryFilms = new TreeSet<>(new FilmComparator().reversed());
        Set<Film> tvSeries = new TreeSet<>(new FilmComparator().reversed());

        for (Film film : filmSet) {
            if (film instanceof FeatureFilm) {
                featureFilms.add(film);
            }
            if (film instanceof ShortFilm) {
                shortFilms.add(film);
            }
            if (film instanceof Documentary) {
                documentaryFilms.add(film);
            }
            if (film instanceof TVSeries) {
                tvSeries.add(film);
            }
        }

        String result = "FeatureFilm:" + ToStringService.filmTitleYearRatingToString(featureFilms) +
                "\n\nShortFilm:" + ToStringService.filmTitleYearRatingToString(shortFilms) +
                "\n\nDocumentary:" + ToStringService.filmTitleYearRatingToString(documentaryFilms) +
                "\n\nTVSeries:" + ToStringService.filmTitleYearRatingToString(tvSeries);

        logger.log(command, result.trim());
    }

    private Film getFilmById(Long id) {
        for (Film film : filmSet) {
            if (film.getId().equals(id)) {
                return film;
            }
        }
        return null;
    }

    private Person getPersonById(Long id) {
        for (Person person : personSet) {
            if (person.getId().equals(id)) {
                return person;
            }
        }
        return null;
    }


    private static class FilmComparator implements Comparator<Film> {

        @Override
        public int compare(Film film1, Film film2) {
            float rating1 = ToStringService.getAvgRating(film1.getRating().values());
            float rating2 = ToStringService.getAvgRating(film2.getRating().values());
            if (rating1 > rating2) {
                return 1;
            }
            return -1;
        }
    }
}
