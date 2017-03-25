package kg.murat.internship.imdb.dao.impl;

import kg.murat.internship.imdb.dao.FilmRepository;
import kg.murat.internship.imdb.dao.PersonRepository;
import kg.murat.internship.imdb.entities.films.*;
import kg.murat.internship.imdb.entities.films.interfaces.*;
import kg.murat.internship.imdb.entities.units.Person;
import kg.murat.internship.imdb.services.ToStringService;
import kg.murat.internship.imdb.services.ioServices.impl.FileIOService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Fujitsu on 24.03.2017.
 */
public class FileFilmRepositoryImpl extends AbstractRepository<Film> implements FilmRepository {
    private PersonRepository personRepository;

    public FileFilmRepositoryImpl(String fileToRead, PersonRepository personRepository) {
        super(fileToRead);
        this.personRepository = personRepository;
    }



    @Override
    public Film getById(Long id) {
        ioService = new FileIOService(FILE_TO_READ);
        for (Film film : getAll()) {
            if (film.getId().equals(id)) {
                return film;
            }
        }
        return null;
    }

    @Override
    public List<Film> getAll() {
        ioService = new FileIOService(FILE_TO_READ);
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
                film.getLanguage() + "\t" + ToStringService.personListToString((Set) film.getDirectors()) + "\t" +
                film.getLength() + "\t" + film.getCountry() + "\t" +
                ToStringService.personListToString((Set) film.getCast());
        if (film instanceof Genreable) {
            result += "\t" + ToStringService.listToString(((Genreable) film).getGenre());
        }
        if (film instanceof Releasable) {
            result += "\t" + dateFormat.format(((Releasable) film).getReleaseDate());
        }
        if (film instanceof Writable) {
            result += "\t" + ToStringService.personListToString((Set) ((Writable) film).getWriters());
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

}
