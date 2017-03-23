package kg.murat.internship.imdb.services.ioServices;

import java.io.IOException;

/**
 * Created by Fujitsu on 21.03.2017.
 */
public interface IOService {
    void write(String message) throws IOException;

    void log(String command, String message) throws IOException;

    String readLine() throws IOException;
}
