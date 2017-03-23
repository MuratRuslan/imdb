package kg.murat.internship.imdb.entities.films;

import kg.murat.internship.imdb.entities.units.artists.Writer;

import java.util.Date;
import java.util.Set;

/**
 * Created by Fujitsu on 20.03.2017.
 */
public class TVSeries extends Film implements Writable {
    private Date startDate;
    private Date endDate;
    private Integer numberOfSeasons;
    private Integer numberOfEpisodes;
    private Set<String> genre;
    private Set<Writer> writers;

    public TVSeries(Long id, String title, String language, String country, Integer length) {
        super(id, title, language, country, length);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(Integer numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public Integer getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(Integer numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public Set<String> getGenre() {
        return genre;
    }

    public void setGenre(Set<String> genre) {
        this.genre = genre;
    }

    public Set<Writer> getWriters() {
        return writers;
    }

    public void setWriters(Set<Writer> writers) {
        this.writers = writers;
    }
}
