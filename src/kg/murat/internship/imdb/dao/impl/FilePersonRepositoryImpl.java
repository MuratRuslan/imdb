package kg.murat.internship.imdb.dao.impl;

import kg.murat.internship.imdb.dao.PersonRepository;
import kg.murat.internship.imdb.entities.units.Person;
import kg.murat.internship.imdb.factories.PersonFactory;
import kg.murat.internship.imdb.services.ioServices.impl.FileIOService;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fujitsu on 24.03.2017.
 */
public class FilePersonRepositoryImpl extends AbstractRepository<Person> implements PersonRepository {

    public FilePersonRepositoryImpl(String fileToRead) {
        super(fileToRead);
    }

    @Override
    public Person getById(Long id) {
        ioService = new FileIOService(FILE_TO_READ);
        for (Person person : getAll()) {
            if (person.getId().equals(id)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public List<Person> getAll() {
        ioService = new FileIOService(FILE_TO_READ);
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
