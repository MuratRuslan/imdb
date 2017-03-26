package kg.murat.internship.imdb.dao.impl;

import kg.murat.internship.imdb.dao.Repository;
import kg.murat.internship.imdb.services.ioServices.IOService;
import kg.murat.internship.imdb.services.ioServices.impl.FileIOService;

import java.util.List;

/**
 * Created by Fujitsu on 24.03.2017.
 */
public abstract class AbstractRepository<T> implements Repository<T> {
    protected final String FILE_TO_READ;
    protected IOService ioService;

    public AbstractRepository(String fileToRead) {
        this.FILE_TO_READ = fileToRead;
        ioService = new FileIOService(FILE_TO_READ);
    }


    @Override
    public abstract T getById(Long id);

    @Override
    public abstract List<T> getAll();

}
