package kg.murat.internship.imdb.entities.films;

import kg.murat.internship.imdb.entities.units.Person;
import kg.murat.internship.imdb.entities.units.artists.Director;
import kg.murat.internship.imdb.entities.units.artists.Performer;
import kg.murat.internship.imdb.entities.units.artists.Writer;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Fujitsu on 20.03.2017.
 */
public class FilmFactory {

    public Film getFilm(String filmInfo, Collection<Person> people) throws ParseException {
        String info[] = filmInfo.split("\\t");

        if (info.length < Film.class.getFields().length) {
            throw new ParseException("Attributes are not full", -1);
        }

        String type = info[0];
        Long id = Long.parseLong(info[1]);
        String title = info[2];
        String language = info[3];
        Set<Director> directors = (Set) getPersonsByIds(info[4], people);
        Integer length = Integer.parseInt(info[5]);
        String country = info[6];
        Set<Performer> performers = (Set) getPersonsByIds(info[7], people);

        if (type.equals("FeatureFilm:")) {
            FeatureFilm film = new FeatureFilm(id, title, language, country, length);
            Set<String> genre = new HashSet<>(Arrays.asList(info[8].split(",")));
            Set<Writer> writers = (Set) getPersonsByIds(info[10], people);
            Long budget = Long.parseLong(info[11]);
            film.setWriters(writers);
            film.setGenre(genre);
            film.setBudget(budget);
            film.setDirectors(directors);
            film.setCast(performers);
            Date date = DateFormat.getDateInstance().parse(info[9]);
            film.setReleaseDate(date);
            return film;
        }

        if (type.equals("ShortFilm:")) {
            ShortFilm film = new ShortFilm(id, title, language, country, length);
            Set<String> genre = new HashSet<>(Arrays.asList(info[8].split(",")));
            Set<Writer> writers = (Set) getPersonsByIds(info[10], people);
            Date date = DateFormat.getDateInstance().parse(info[9]);
            film.setWriters(writers);
            film.setReleaseDate(date);
            film.setGenre(genre);
            film.setDirectors(directors);
            film.setCast(performers);
            return film;
        }

        if (type.equals("Documentary:")) {
            Documentary film = new Documentary(id, title, language, country, length);
            Date date = DateFormat.getDateInstance().parse(info[8]);
            film.setReleaseDate(date);
            film.setDirectors(directors);
            film.setCast(performers);
            return film;
        }

        if (type.equals("TVSeries:")) {
            TVSeries film = new TVSeries(id, title, language, country, length);
            Set<String> genre = new HashSet<>(Arrays.asList(info[8].split(",")));
            Set<Writer> writers = (Set) getPersonsByIds(info[9], people);
            Integer seasons = Integer.parseInt(info[12]);
            Integer episodes = Integer.parseInt(info[13]);
            Date startDate;
            Date endDate;
            startDate = DateFormat.getDateInstance().parse(info[10]);
            endDate = DateFormat.getDateInstance().parse(info[11]);
            film.setGenre(genre);
            film.setWriters(writers);
            film.setStartDate(startDate);
            film.setEndDate(endDate);
            film.setNumberOfSeasons(seasons);
            film.setNumberOfEpisodes(episodes);
            film.setDirectors(directors);
            film.setCast(performers);
            return film;
        }

        throw new ParseException("No such type in films", -1);
    }

    private Person findPersonById(Long id, Collection<Person> people) {
        for (Person person : people) {
            if (person.getId().equals(id)) {
                return person;
            }
        }
        throw null;
    }

    private Set<Person> getPersonsByIds(String idStrings, Collection<Person> people) {
        Set<Person> persons = new HashSet<>();
        for (String textId : idStrings.split(",")) {
            Long id = Long.parseLong(textId);
            persons.add(findPersonById(id, people));
        }
        return persons;
    }


}
