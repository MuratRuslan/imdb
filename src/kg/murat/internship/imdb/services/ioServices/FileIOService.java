package kg.murat.internship.imdb.services.ioServices;

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
            e.printStackTrace();
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
    public void log(String command, String message) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter("output.txt", true));
        printWriter.println(command);
        printWriter.println();
        printWriter.println(message);
        printWriter.println();
        printWriter.println("--------------------------------------------------");
        printWriter.close();
    }

    @Override
    public String readLine() throws IOException {
        String next;
        while (null != (next = reader.readLine())) {
            return next;
        }
        return null;
    }

}
