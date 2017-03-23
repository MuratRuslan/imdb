package kg.murat.internship.imdb.entities.units;

/**
 * Created by Fujitsu on 20.03.2017.
 */
public abstract class Artist extends Person {

    public Artist(Long id, String name, String surname, String country) {
        super(id, name, surname, country);
    }
}
