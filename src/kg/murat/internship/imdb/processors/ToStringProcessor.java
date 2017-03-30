package kg.murat.internship.imdb.processors;

import kg.murat.internship.imdb.entities.films.*;
import kg.murat.internship.imdb.entities.films.interfaces.Genreable;
import kg.murat.internship.imdb.entities.films.interfaces.Releasable;
import kg.murat.internship.imdb.entities.films.interfaces.Series;
import kg.murat.internship.imdb.entities.films.interfaces.Writable;
import kg.murat.internship.imdb.entities.units.Person;
import kg.murat.internship.imdb.entities.units.artists.Director;
import kg.murat.internship.imdb.entities.units.artists.Writer;
import kg.murat.internship.imdb.entities.units.artists.performers.Actor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Fujitsu on 24.03.2017.
 */
public class ToStringProcessor {
    public static String personsToString(Collection<Person> persons) {
        List<Person> personList = new ArrayList<>(persons);
        String result = (persons.size() > 0) ? personList.get(0).getId().toString() : "";
        if (persons.size() > 1) {
            for (int i = 1; i < personList.size(); i++) {
                result += "," + personList.get(i).getId().toString();
            }
        }
        return result;
    }

    public static String collectionStringsToString(Collection<String> collection) {
        List<String> list = new ArrayList<>(collection);
        String result = (list.size() > 0) ? list.get(0) : "";
        for (int i = 1; i < list.size(); i++) {
            result += "," + list.get(i);
        }
        return result;
    }

    public static String filmToStringLog(Film film) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String releaseDate = (film instanceof Releasable) ? dateFormat.format(((Releasable) film).getReleaseDate()) : "";
        String startDate = (film instanceof Series) ? dateFormat.format(((Series) film).getStartDate()) : "";
        String endDate = (film instanceof Series) ? dateFormat.format(((Series) film).getEndDate()) : "";
        String genre = (film instanceof Genreable) ? collectionStringsToString(((Genreable) film).getGenre()) : "";
        String writers = (film instanceof Writable) ? personsNameAndSurnameToString((Set) ((Writable) film).getWriters()) : "";
        String directors = personsNameAndSurnameToString((Collection) film.getDirectors());
        String stars = personsNameAndSurnameToString((Collection) film.getCast());
        float avgRating = getAvgRating(film.getRating().values());
        String rating = (!film.getRating().isEmpty()) ? String.format("%.1f", avgRating) + "/10 from " +
                film.getRating().size() + " users" : "Awaiting for votes";
        String format;
        if (film instanceof FeatureFilm || film instanceof ShortFilm) {
            format = "%s (%s)\n" +
                    "%s\n" +
                    "Writers: %s\n" +
                    "Directors: %s\n" +
                    "Stars: %s\n" +
                    "Ratings: %s";
            return String.format(format, film.getTitle(), releaseDate, genre, writers, directors, stars, rating);
        }

        if (film instanceof Documentary) {
            format = "%s (%s)\n" +
                    "Directors: %s\n" +
                    "Stars: %s\n" +
                    "Ratings: %s";
            return String.format(format, film.getTitle(), releaseDate, directors, stars, rating);
        }

        if (film instanceof TVSeries) {
            format = "%s (%s-%s)\n" +
                    "%s seasons, %s episodes\n" +
                    "%s\n" +
                    "Writers: %s\n" +
                    "Directors: %s\n" +
                    "Stars: %s\n" +
                    "Ratings: %s";
            return String.format(format, film.getTitle(), startDate, endDate,
                    ((TVSeries) film).getNumberOfSeasons(), ((TVSeries) film).getNumberOfEpisodes(),
                    genre, writers, directors, stars, rating);
        }
        return "";
    }

    public static String personsNameAndSurnameToString(Collection<Person> collection) {
        List<Person> persons = new ArrayList<>(collection);
        String result = "";
        if (persons.size() > 0)
            result = persons.get(0).getName() + " " + persons.get(0).getSurname();
        for (int i = 1; i < persons.size(); i++) {
            result += ", " + persons.get(i).getName() + " " + persons.get(i).getSurname();
        }
        return result;
    }

    public static String filmShortInfoToString(Film film) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String result = "Film title: " + film.getTitle();
        if (film instanceof Releasable) {
            result += " (" + dateFormat.format(((Releasable) film).getReleaseDate()) + ")";
        }
        result += "\n" + film.getLength() + " min\n" +
                "Language: " + film.getLanguage();
        return result;
    }

    public static String filmsTitleYearRatingToString(Collection<Film> films) {
        String seriesformat = "\n%s (%s-%s) Ratings: %.1f/10 from %d users";
        String filmFormat = "\n%s (%s) Ratings: %.1f/10 from %d users";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String result = "";
        for (Film film : films) {
            float avgRating = getAvgRating(film.getRating().values());
            if (film instanceof TVSeries) {
                String startDate = dateFormat.format(((TVSeries) film).getStartDate());
                String endDate = dateFormat.format(((TVSeries) film).getEndDate());
                result += String.format(seriesformat, film.getTitle(), startDate, endDate, avgRating, film.getRating().size());
                continue;
            }
            String releaseDate = dateFormat.format(((Releasable) film).getReleaseDate());
            result += String.format(filmFormat, film.getTitle(), releaseDate, avgRating, film.getRating().size());
        }
        return result;
    }

    public static float getAvgRating(Collection<Integer> collection) {
        int sum = 0;
        for (Integer rate : collection) {
            sum += rate;
        }
        if (collection.isEmpty()) return 0f;
        return (sum + 0f) / collection.size();
    }

    public static String personToShortString(Collection<Person> collection) {
        String result = "";
        for (Person person : collection) {
            result += "\n" + person.getName() + " " + person.getSurname();
            if (person instanceof Director) {
                result += " " + ((Director) person).getAgent();
            }
            if (person instanceof Writer) {
                result += " " + ((Writer) person).getStyle();
            }
            if (person instanceof Actor) {
                result += " " + ((Actor) person).getHeight() + " cm";
            }
        }
        return ("".equals(result)) ? "\nNo result" : result;
    }
}
