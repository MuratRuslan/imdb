package kg.murat.internship.imdb;

import kg.murat.internship.imdb.dao.PersonRepository;
import kg.murat.internship.imdb.dao.impl.FilePersonRepositoryImpl;
import kg.murat.internship.imdb.entities.units.Person;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int i = Calendar.getInstance().get(Calendar.YEAR);
        System.out.println(i);
    }
}
