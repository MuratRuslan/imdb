package kg.murat.internship.imdb.dao.impl;

import kg.murat.internship.imdb.dao.Repository;
import kg.murat.internship.imdb.services.ioServices.FileIOService;
import kg.murat.internship.imdb.services.ioServices.IOService;

import java.util.List;

/**
 * Created by Fujitsu on 24.03.2017.
 */
public abstract class AbstractRepository<T> implements Repository<T> {
    protected final String FILE_TO_READ;
    protected final String FILE_TO_WRITE;
    protected IOService ioService;

    public AbstractRepository(String fileToRead, String fileToWrite) {
        this.FILE_TO_READ = fileToRead;
        this.FILE_TO_WRITE = fileToWrite;
        ioService = new FileIOService(FILE_TO_READ, FILE_TO_WRITE);
    }


    @Override
    public abstract T getById(Long id);

    @Override
    public abstract List<T> getAll();

}
