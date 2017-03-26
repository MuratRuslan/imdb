package kg.murat.internship.imdb.services.ioServices.impl;

import kg.murat.internship.imdb.entities.films.Film;
import kg.murat.internship.imdb.services.ioServices.IOService;

import java.io.*;

/**
 * Created by Fujitsu on 21.03.2017.
 */
public class FileIOService implements IOService {
    private final String FILE_PATH;
    private BufferedReader reader;
    private File file;
    public FileIOService(String filePath) {
        FILE_PATH = filePath;
        try {
            file = new File(FILE_PATH);
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
        }
    }

    @Override
    public void write(String msg) throws IOException {
        FileWriter fileWriter = new FileWriter(file, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print("\n" + msg);
        printWriter.close();
    }



    @Override
    public String readLine() throws IOException {
        String next;
        while (null != (next = reader.readLine())) {
            return next;
        }
        reader.close();
        return null;
    }

}
