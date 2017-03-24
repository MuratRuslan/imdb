package kg.murat.internship.imdb.entities.films;

import kg.murat.internship.imdb.entities.films.interfaces.Genreable;
import kg.murat.internship.imdb.entities.films.interfaces.Releasable;
import kg.murat.internship.imdb.entities.films.interfaces.Writable;
import kg.murat.internship.imdb.entities.units.artists.Writer;

import java.util.Date;
import java.util.Set;

/**
 * Created by Fujitsu on 20.03.2017.
 */
public class ShortFilm extends Film implements Releasable, Writable, Genreable {
    private Date releaseDate;
    private Set<Writer> writers;
    private Set<String> genre;

    public ShortFilm(Long id, String title, String language, String country, Integer length) {
        super(id, title, language, country, length);
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<Writer> getWriters() {
        return writers;
    }

    public void setWriters(Set<Writer> writers) {
        this.writers = writers;
    }

    public Set<String> getGenre() {
        return genre;
    }

    public void setGenre(Set<String> genre) {
        this.genre = genre;
    }
}
