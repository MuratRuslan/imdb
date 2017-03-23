package kg.murat.internship.imdb.dao.impl;

import kg.murat.internship.imdb.dao.PersonRepository;
import kg.murat.internship.imdb.entities.units.Person;
import kg.murat.internship.imdb.entities.units.PersonFactory;
import kg.murat.internship.imdb.entities.units.User;

import java.io.IOException;
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
        Person person;
        try {
            person = new PersonFactory().getPerson(ioService.readLine());
        } catch (IOException e) {
            person = DEFAULT_PERSON;
        }
        return person;
    }

    @Override
    public List<Person> getAll() {
        List<Person> persons = new ArrayList<>();
        String next;
        try {
            while (null != (next = ioService.readLine())) {
                persons.add(new PersonFactory().getPerson(next));
            }
        } catch (IOException e) {
            return persons;
        }
        return persons;
    }
}
