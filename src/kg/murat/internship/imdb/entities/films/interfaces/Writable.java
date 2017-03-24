package kg.murat.internship.imdb.entities.films.interfaces;

import kg.murat.internship.imdb.entities.units.artists.Writer;

import java.util.Set;

/**
 * Created by Fujitsu on 24.03.2017.
 */
public interface Writable {

    Set<Writer> getWriters();

    void setWriters(Set<Writer> writers);
}
