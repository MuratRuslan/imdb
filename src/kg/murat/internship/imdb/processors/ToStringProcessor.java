package kg.murat.internship.imdb.processors;

import kg.murat.internship.imdb.entities.films.*;
import kg.murat.internship.imdb.entities.films.interfaces.Releasable;
import kg.murat.internship.imdb.entities.films.interfaces.Writable;
import kg.murat.internship.imdb.entities.units.Person;
import kg.murat.internship.imdb.entities.units.artists.Director;
import kg.murat.internship.imdb.entities.units.artists.Writer;
import kg.murat.internship.imdb.entities.units.artists.performers.Actor;

import java.text.SimpleDateFormat;
import java.util.*;

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

        if (film instanceof FeatureFilm || film instanceof ShortFilm) {
            List<Person> writers = new ArrayList(((Writable) film).getWriters());
            List<Person> directors = new ArrayList<>(film.getDirectors());
            List<Person> stars = new ArrayList<>(film.getCast());
            List<Person> ratedUsers = new ArrayList<>();
            ratedUsers.addAll(film.getRating().keySet());
            String releaseDate = dateFormat.format(((Releasable) film).getReleaseDate());
            return film.getTitle() + " (" + releaseDate + ")\n" +
                    "Writers: " + personsNameAndSurnameToString(writers) + "\n" +
                    "Directors: " + personsNameAndSurnameToString(directors) + "\n" +
                    "Stars: " + personsNameAndSurnameToString(stars) + "\n" +
                    ((ratedUsers.size() > 0) ? getAvgRating(film.getRating().values()) + "/10 from " + film.getRating().size() +
                            " " + personsNameAndSurnameToString(ratedUsers) : "Awaiting for votes");
        }

        if (film instanceof Documentary) {
            Documentary documentaryFilm = (Documentary) film;
            String releaseDate = dateFormat.format(documentaryFilm.getReleaseDate());
            List<Person> ratedUsers = new ArrayList<>(film.getRating().keySet());
            List<Person> directors = new ArrayList<>(film.getDirectors());
            List<Person> stars = new ArrayList<>(film.getCast());
            return film.getTitle() + " (" + releaseDate + ")\n" +
                    "Directors: " + personsNameAndSurnameToString(directors) + "\n" +
                    "Stars: " + personsNameAndSurnameToString(stars) + "\n" +
                    ((ratedUsers.size() > 0) ? getAvgRating(film.getRating().values()) + "/10 from " + film.getRating().size() +
                            " " + personsNameAndSurnameToString(ratedUsers) : "Awaiting for votes");
        }

        if (film instanceof TVSeries) {
            TVSeries series = (TVSeries) film;
            List<Person> writers = new ArrayList(((Writable) film).getWriters());
            List<Person> directors = new ArrayList<>(film.getDirectors());
            List<Person> stars = new ArrayList<>(film.getCast());
            List<Person> ratedUsers = new ArrayList<>(film.getRating().keySet());
            String startDate = dateFormat.format(series.getStartDate());
            String endDate = dateFormat.format(series.getEndDate());
            return film.getTitle() + " (" + startDate + "-" + endDate + ")\n" +
                    series.getNumberOfSeasons() + " seasons, " + series.getNumberOfEpisodes() + " episodes\n" +
                    collectionStringsToString(series.getGenre()) + "\n" +
                    "Writers: " + personsNameAndSurnameToString(writers) + "\n" +
                    "Directors: " + personsNameAndSurnameToString(directors) + "\n" +
                    "Stars: " + personsNameAndSurnameToString(stars) + "\n" +
                    ((ratedUsers.size() > 0) ? getAvgRating(film.getRating().values()) + "/10 from " + film.getRating().size() +
                            " " + personsNameAndSurnameToString(ratedUsers) : "Awaiting for votes");
        }
        return "";
    }

    public static String personsNameAndSurnameToString(List<Person> persons) {
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
        Calendar calendar1 = new GregorianCalendar();
        Calendar calendar2 = new GregorianCalendar();
        String result = "";
        for (Film film : films) {
            if (film instanceof TVSeries) {
                calendar1.setTime(((TVSeries) film).getStartDate());
                calendar2.setTime(((TVSeries) film).getEndDate());
                result += "\n" + film.getTitle() + " (" + calendar1.get(Calendar.YEAR) + "-" +
                        calendar2.get(Calendar.YEAR) + ") Ratings: " +
                        getAvgRating(film.getRating().values()) +
                        "/10 " + film.getRating().size() + " from " +
                        film.getRating().size() + " users";
                continue;
            }
            calendar1.setTime(((Releasable) film).getReleaseDate());
            result += "\n" + film.getTitle() + " (" + calendar1.get(Calendar.YEAR) +
                    ") Ratings: " + getAvgRating(film.getRating().values()) +
                    "/10 " + film.getRating().size() + " from " +
                    film.getRating().size() + " users";
        }
        return result;
    }

    public static float getAvgRating(Collection<Integer> collection) {
        int sum = 0;
        for (Integer rate : collection) {
            sum += rate;
        }
        if (collection.isEmpty()) return 0f;
        return sum / collection.size();
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
        return result;
    }
}
