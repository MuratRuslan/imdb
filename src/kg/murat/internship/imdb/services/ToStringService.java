package kg.murat.internship.imdb.services;

import kg.murat.internship.imdb.entities.films.*;
import kg.murat.internship.imdb.entities.films.interfaces.Releasable;
import kg.murat.internship.imdb.entities.films.interfaces.Writable;
import kg.murat.internship.imdb.entities.units.Person;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Fujitsu on 24.03.2017.
 */
public class ToStringService {
    public static String personListToString(Collection<Person> persons) {
        List<Person> personList = new ArrayList<>(persons);
        String result = (persons.size() > 0) ? personList.get(0).getId().toString() : "";
        if (persons.size() > 1) {
            for (int i = 1; i < personList.size(); i++) {
                result += "," + personList.get(i).getId().toString();
            }
        }
        return result;
    }

    public static String listToString(Collection<String> collection) {
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
                    "Writers: " + personListNameAndSurnameToString(writers) + "\n" +
                    "Directors: " + personListNameAndSurnameToString(directors) + "\n" +
                    "Stars: " + personListNameAndSurnameToString(stars) + "\n" +
                    ((ratedUsers.size() > 0) ? film.getRating() + "/10 from " + film.getRating().size() +
                            " " + personListNameAndSurnameToString(ratedUsers) : "Awaiting for votes");
        }

        if (film instanceof Documentary) {
            Documentary documentaryFilm = (Documentary) film;
            String releaseDate = dateFormat.format(documentaryFilm.getReleaseDate());
            List<Person> ratedUsers = new ArrayList<>(film.getRating().keySet());
            return film.getTitle() + " (" + releaseDate + ")\n" +
                    "Directors: " + personListNameAndSurnameToString((List) film.getDirectors()) + "\n" +
                    "Stars: " + personListNameAndSurnameToString((List) film.getCast()) + "\n" +
                    ((ratedUsers.size() > 0) ? film.getRating() + "/10 from " + film.getRating().size() +
                            " " + personListNameAndSurnameToString(ratedUsers) : "Awaiting for votes");
        }

        if (film instanceof TVSeries) {
            TVSeries series = (TVSeries) film;
            List<Person> writers = (List) (series).getWriters();
            List<Person> directors = (List) film.getDirectors();
            List<Person> stars = (List) film.getCast();
            List<Person> ratedUsers = new ArrayList<>(film.getRating().keySet());
            String startDate = dateFormat.format(series.getStartDate());
            String endDate = dateFormat.format(series.getEndDate());
            return film.getTitle() + " (" + startDate + "-" + endDate + ")\n" +
                    series.getNumberOfSeasons() + " seasons, " + series.getNumberOfEpisodes() + " episodes\n" +
                    listToString(series.getGenre()) + "\n" +
                    "Writers: " + personListNameAndSurnameToString(writers) + "\n" +
                    "Directors: " + personListNameAndSurnameToString(directors) + "\n" +
                    "Stars: " + personListNameAndSurnameToString(stars) + "\n" +
                    ((ratedUsers.size() > 0) ? film.getRating() + "/10 from " + film.getRating().size() +
                            " " + personListNameAndSurnameToString(ratedUsers) : "Awaiting for votes");
        }
        return "";
    }

    public static String personListNameAndSurnameToString(List<Person> persons) {
        String result = "";
        if (persons.size() > 0)
            result = persons.get(0).getName() + " " + persons.get(0).getSurname();
        for (int i = 1; i < persons.size(); i++) {
            result += ", " + persons.get(i).getName() + " " + persons.get(i).getSurname();
        }
        return result;
    }

    public static String filmListShortInfoToString(Film film) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String result = "Film title: " + film.getTitle();
        if(film instanceof Releasable) {
            result += " (" + dateFormat.format(((Releasable) film).getReleaseDate()) + ")";
        }
        result += "\n" + film.getLength() + "min\n" +
                "Language: " + film.getLanguage();
        return result;
    }
}
