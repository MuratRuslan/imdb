package kg.murat.internship.imdb.dao.impl;

import kg.murat.internship.imdb.dao.FilmRepository;
import kg.murat.internship.imdb.entities.films.Film;
import kg.murat.internship.imdb.factories.FilmFactory;
import kg.murat.internship.imdb.entities.films.interfaces.*;
import kg.murat.internship.imdb.entities.units.Person;
import kg.murat.internship.imdb.processors.ToStringProcessor;
import kg.murat.internship.imdb.services.ioServices.impl.FileIOService;

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
    private Collection<Person> personCollection;

    public FileFilmRepositoryImpl(String fileToRead, Collection<Person> personCollection) {
        super(fileToRead);
        this.personCollection = personCollection;
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
        List<Person> persons = new ArrayList<>(personCollection);
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
                film.getLanguage() + "\t" + ToStringProcessor.personsToString((Set) film.getDirectors()) + "\t" +
                film.getLength() + "\t" + film.getCountry() + "\t" +
                ToStringProcessor.personsToString((Set) film.getCast());
        if (film instanceof Genreable) {
            result += "\t" + ToStringProcessor.collectionStringsToString(((Genreable) film).getGenre());
        }
        if (film instanceof Releasable) {
            result += "\t" + dateFormat.format(((Releasable) film).getReleaseDate());
        }
        if (film instanceof Writable) {
            result += "\t" + ToStringProcessor.personsToString((Set) ((Writable) film).getWriters());
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
