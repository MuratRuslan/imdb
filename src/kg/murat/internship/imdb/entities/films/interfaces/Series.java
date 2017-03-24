package kg.murat.internship.imdb.entities.films.interfaces;

import java.util.Date;

/**
 * Created by Fujitsu on 24.03.2017.
 */
public interface Series {
    Date getStartDate();
    Date getEndDate();
    Integer getNumberOfSeasons();
    Integer getNumberOfEpisodes();
}
