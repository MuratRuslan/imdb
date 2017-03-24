package kg.murat.internship.imdb.dao.impl;

import kg.murat.internship.imdb.dao.FilmRepository;
import kg.murat.internship.imdb.dao.PersonRepository;
import kg.murat.internship.imdb.entities.films.*;
import kg.murat.internship.imdb.entities.units.Person;
import kg.murat.internship.imdb.services.ioServices.FileIOService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Fujitsu on 24.03.2017.
 */
public class FileFilmRepositoryImpl extends AbstractRepository<Film> implements FilmRepository {
    private static final Film DEFAULT_FILM = new ShortFilm(0L, "", "", "", 0);
    private PersonRepository personRepository;

    public FileFilmRepositoryImpl(String fileToRead, String fileToWrite, PersonRepository personRepository) {
        super(fileToRead, fileToWrite);
        this.personRepository = personRepository;
    }

    @Override
    public Film getById(Long id) {
        ioService = new FileIOService(FILE_TO_READ, FILE_TO_WRITE);
        for (Film film : getAll()) {
            if (film.getId().equals(id)) {
                return film;
            }
        }
        return null;
    }

    @Override
    public List<Film> getAll() {
        ioService = new FileIOService(FILE_TO_READ, FILE_TO_WRITE);
        List<Film> films = new ArrayList<>();
        List<Person> persons = personRepository.getAll();
        String next;
        try {
            while (null != (next = ioService.readLine())) {
                films.add(new FilmFactory().getFilm(next, persons));
            }
        } catch (IOException e) {
        } catch (ParseException e) {
        }
        return films;
    }


    @Override
    public boolean addFilm(Film film) {
        String type = film.getClass().getSimpleName() + ":";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
        String result = type + "\t" + film.getId() + "\t" + film.getTitle() + "\t" +
                film.getLanguage() + "\t" + personListToString((Set) film.getDirectors()) + "\t" +
                film.getLength() + "\t" + film.getCountry() + "\t" +
                personListToString((Set) film.getCast());
        if (film instanceof Genreable) {
            result += "\t" + listToString(((Genreable) film).getGenre());
        }
        if (film instanceof Releasable) {
            result += "\t" + dateFormat.format(((Releasable) film).getReleaseDate());
        }
        if (film instanceof Writable) {
            result += "\t" + personListToString((Set) ((Writable) film).getWriters());
        }
        if (film instanceof Budgetable) {
            result += "\t" + ((Budgetable) film).getBudget();
        }
        if (film instanceof Series) {
            result += "\t" + dateFormat.format(((Series) film).getStartDate()) + "\t" +
                    dateFormat.format(((Series) film).getEndDate()) + "\t" +
                    ((Series) film).getNumberOfSeasons() + "\t" +
                    ((Series) film).getNumberOfEpisodes();
        }
        try {
            if (null == getById(film.getId())) {
                ioService.write(result);
                return true;
            }
        } catch (IOException e) {
        }
        return false;
    }

    private String personListToString(Collection<Person> persons) {
        List<Person> personList = new ArrayList<>(persons);
        String result = (persons.size() > 0) ? personList.get(0).getId().toString() : "";
        if (persons.size() > 1) {
            for (int i = 1; i < personList.size(); i++) {
                result += "," + personList.get(i).getId().toString();
            }
        }
        return result;
    }

    private String listToString(Collection<String> collection) {
        List<String> list = new ArrayList<>(collection);
        String result = (list.size() > 0) ? list.get(0) : "";
        for (int i = 1; i < list.size(); i++) {
            result += "," + list.get(i);
        }
        return result;
    }
}
