package kg.murat.internship.imdb.entities.units.artists;

import kg.murat.internship.imdb.entities.units.Artist;

/**
 * Created by Fujitsu on 20.03.2017.
 */
public class Director extends Artist {
    private String agent;

    public Director(Long id, String name, String surname, String country, String agent) {
        super(id, name, surname, country);
        this.agent = agent;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

}
