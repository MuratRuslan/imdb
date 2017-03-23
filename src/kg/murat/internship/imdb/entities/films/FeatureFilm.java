package kg.murat.internship.imdb.entities.films;

import kg.murat.internship.imdb.entities.units.artists.Writer;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Fujitsu on 20.03.2017.
 */
public class FeatureFilm extends Film implements Releasable, Writable {
    private Date releaseDate;
    private Long budget;
    private Set<Writer> writers;
    private Set<String> genre;

    public FeatureFilm(Long id, String title, String language, String country, Integer length) {
        super(id, title, language, country, length);
        writers = new HashSet<>();
        genre = new HashSet<>();
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
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
