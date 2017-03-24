package kg.murat.internship.imdb.entities.films;

import kg.murat.internship.imdb.entities.films.interfaces.Releasable;

import java.util.Date;

/**
 * Created by Fujitsu on 20.03.2017.
 */
public class Documentary extends Film implements Releasable {
    private Date releaseDate;

    public Documentary(Long id, String title, String language, String country, Integer length) {
        super(id, title, language, country, length);
    }


    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

}
