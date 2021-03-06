package kg.murat.internship.imdb.factories;

import kg.murat.internship.imdb.entities.units.Person;
import kg.murat.internship.imdb.entities.units.User;
import kg.murat.internship.imdb.entities.units.artists.Director;
import kg.murat.internship.imdb.entities.units.artists.Writer;
import kg.murat.internship.imdb.entities.units.artists.performers.Actor;
import kg.murat.internship.imdb.entities.units.artists.performers.Actress;

import java.text.ParseException;

/**
 * Created by Fujitsu on 20.03.2017.
 */
public class PersonFactory {


    public Person getPerson(String personInfo) throws ParseException {
        String[] info = personInfo.split("\\t");
        String personType = info[0];
        Long id = Long.parseLong(info[1]);
        String name = info[2];
        String surname = info[3];
        String country = info[4];
        if (personType.equals("Actor:")) {
            Integer height = Integer.parseInt(info[5]);
            Actor person = new Actor(id, name, surname, country, height);
            return person;
        }
        if (personType.equals("Actress:")) {
            Actress person = new Actress(id, name, surname, country);
            return person;
        }
        if (personType.equals("Director:")) {
            String agent = info[5];
            Director person = new Director(id, name, surname, country, agent);
            return person;
        }
        if (personType.equals("Writer:")) {
            String style = info[5];
            Writer person = new Writer(id, name, surname, country, style);
            return person;
        }
        if (personType.equals("User:")) {
            User person = new User(id, name, surname, country);
            return person;
        }
        throw new ParseException("No such person type", -1);
    }
}
