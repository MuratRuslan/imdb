package kg.murat.internship.imdb.entities.units.artists;

import kg.murat.internship.imdb.entities.units.Artist;

/**
 * Created by Fujitsu on 20.03.2017.
 */
public class Writer extends Artist {
    private String style;

    public Writer(Long id, String name, String surname, String country, String style) {
        super(id, name, surname, country);
        this.style = style;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

}
