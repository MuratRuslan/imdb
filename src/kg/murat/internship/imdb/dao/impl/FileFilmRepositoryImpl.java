package kg.murat.internship.imdb.dao.impl;

import kg.murat.internship.imdb.dao.FilmRepository;
import kg.murat.internship.imdb.dao.Repository;
import kg.murat.internship.imdb.entities.films.*;
import kg.murat.internship.imdb.entities.units.Person;

import java.io.IOException;
import java.util.*;

/**
 * Created by Fujitsu on 24.03.2017.
 */
public class FileFilmRepositoryImpl extends AbstractRepository<Film> implements FilmRepository{
    private static final Film DEFAULT_FILM = new Film(0L, "", "", "", 0);
    private Repository<Person> personRepository = new FilePersonRepositoryImpl(FILE_TO_READ, FILE_TO_WRITE);

    public FileFilmRepositoryImpl(String fileToRead, String fileToWrite) {
        super(fileToRead, fileToWrite);
    }

    @Override
    public Film getById(Long id) {
        Film film;
        try {
            film = new FilmFactory().getFilm(ioService.readLine(), personRepository.getAll());
        } catch (IOException e) {
            film = DEFAULT_FILM;
        }
        return film;
    }

    @Override
    public List<Film> getAll() {
        List<Film> films = new ArrayList<>();
        List<Person> persons = personRepository.getAll();
        String next;
        try {
            while (null != (next = ioService.readLine())) {
                films.add(new FilmFactory().getFilm(next, persons));
            }
        } catch (IOException e) {
            return films;
        }
        return films;
    }

    @Override
    public boolean addFilm(Film film) {
        String type = film.getClass().getSimpleName() + ":";
        String result = type;
        result += "\t" + film.getId() + "\t" + film.getTitle() + "\t" +
                film.getLanguage() + "\t" + personListToString((List)film.getDirectors()) + "\t" +
                film.getLength() + "\t" + film.getCountry() + "\t" +
                personListToString((List) film.getCast());
        if(film instanceof Releasable) {
            //result += new Calendar().get(Calendar.) ((Releasable) film).getReleaseDate().getY;
        }
        if(film instanceof Writable) {
            result += ((Writable)film).getWriters();
        }
        return false;
    }

    private String personListToString(Collection<Person> persons) {
        Person[] personsArr = null;
        persons.toArray(personsArr);
        String result = (persons.size() > 0) ? personsArr[0].getId().toString() : "";
        if (persons.size() > 1) {
            for (int i = 1; i < personsArr.length; i++) {
                result += "," + personsArr[i].getId().toString();
            }
        }
        return result;
    }
}
