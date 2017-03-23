package kg.murat.internship.imdb.dao;

import java.util.List;

/**
 * Created by Fujitsu on 24.03.2017.
 */
public interface Repository<T> {
    T getById(Long id);

    List<T> getAll();

}
