package kg.murat.internship.imdb.entities.units.artists.performers;

import kg.murat.internship.imdb.entities.units.artists.Performer;

/**
 * Created by Fujitsu on 20.03.2017.
 */
public class Actor extends Performer {
    private Integer height;

    public Actor(Long id, String name, String surname, String country, Integer height) {
        super(id, name, surname, country);
        this.height = height;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

}
