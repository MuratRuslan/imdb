package kg.murat.internship.imdb.dao.impl;

import kg.murat.internship.imdb.dao.PersonRepository;
import kg.murat.internship.imdb.entities.units.Person;
import kg.murat.internship.imdb.entities.units.PersonFactory;
import kg.murat.internship.imdb.entities.units.User;
import kg.murat.internship.imdb.services.ioServices.FileIOService;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fujitsu on 24.03.2017.
 */
public class FilePersonRepositoryImpl extends AbstractRepository<Person> implements PersonRepository {
    private static Person DEFAULT_PERSON = new User(0L, "", "", "");

    public FilePersonRepositoryImpl(String fileToRead, String fileToWrite) {
        super(fileToRead, fileToWrite);
    }

    @Override
    public Person getById(Long id) {
        ioService = new FileIOService(FILE_TO_READ, FILE_TO_WRITE);
        for (Person person : getAll()) {
            if (person.getId().equals(id)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public List<Person> getAll() {
        ioService = new FileIOService(FILE_TO_READ, FILE_TO_WRITE);
        List<Person> persons = new ArrayList<>();
        String next;
        try {
            while (null != (next = ioService.readLine())) {
                persons.add(new PersonFactory().getPerson(next));
            }
        } catch (IOException e) {
        } catch (ParseException e) {
        }
        return persons;
    }

}
