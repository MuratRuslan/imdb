package kg.murat.internship.imdb.entities.films;

import kg.murat.internship.imdb.entities.units.User;
import kg.murat.internship.imdb.entities.units.artists.Director;
import kg.murat.internship.imdb.entities.units.artists.Performer;

import java.util.Map;
import java.util.Set;

/**
 * Created by Fujitsu on 20.03.2017.
 */
public abstract class Film {
    private Long id;
    private String title;
    private String language;
    private String country;
    private Integer length;
    private Map<User, Integer> rating;
    private Set<Director> directors;
    private Set<Performer> cast;

    public Film(Long id, String title, String language, String country, Integer length) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.country = country;
        this.length = length;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Map<User, Integer> getRating() {
        return rating;
    }

    public void setRating(Map<User, Integer> rating) {
        this.rating = rating;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Set<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(Set<Director> directors) {
        this.directors = directors;
    }

    public Set<Performer> getCast() {
        return cast;
    }

    public void setCast(Set<Performer> cast) {
        this.cast = cast;
    }
}
