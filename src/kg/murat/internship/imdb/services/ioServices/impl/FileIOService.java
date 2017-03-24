package kg.murat.internship.imdb.services.ioServices.impl;

import kg.murat.internship.imdb.services.ioServices.IOService;

import java.io.*;

/**
 * Created by Fujitsu on 21.03.2017.
 */
public class FileIOService implements IOService {
    private final String OUTPUT_FILE_PATH;
    private final String INPUT_FILE_PATH;
    private BufferedReader reader;

    public FileIOService(String inputFilePath, String outputFilePath) {
        INPUT_FILE_PATH = inputFilePath;
        OUTPUT_FILE_PATH = outputFilePath;
        try {
            reader = new BufferedReader(new FileReader(new File(INPUT_FILE_PATH)));
        } catch (FileNotFoundException e) {
        }
    }

    @Override
    public void write(String msg) throws IOException {
        FileWriter fileWriter = new FileWriter(new File(OUTPUT_FILE_PATH), true);
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
