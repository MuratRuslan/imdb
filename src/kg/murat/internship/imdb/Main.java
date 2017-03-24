package kg.murat.internship.imdb;

import kg.murat.internship.imdb.dao.PersonRepository;
import kg.murat.internship.imdb.dao.impl.FilePersonRepositoryImpl;
import kg.murat.internship.imdb.entities.units.Person;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar();
        try {
            calendar.setTime(new SimpleDateFormat("dd.mm.yyyy").parse("22.12.2002"));
            System.out.println(calendar.get(Calendar.YEAR));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
