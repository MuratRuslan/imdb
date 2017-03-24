package kg.murat.internship.imdb.services.ioServices;

/**
 * Created by Fujitsu on 24.03.2017.
 */
public interface Logger {

    void log(String msg);


    void log(String command, String message);
}
