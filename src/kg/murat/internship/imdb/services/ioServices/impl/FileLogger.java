package kg.murat.internship.imdb.services.ioServices.impl;

import kg.murat.internship.imdb.services.ioServices.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Fujitsu on 24.03.2017.
 */
public class FileLogger implements Logger {
    private final String OUTPUT_FILE_PATH;

    public FileLogger(String outputFilePath) {
        OUTPUT_FILE_PATH = outputFilePath;
    }

    @Override
    public void log(String msg) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter(OUTPUT_FILE_PATH, true));
        } catch (IOException e) {
        }
        printWriter.println(msg);
        printWriter.println();
        printWriter.println("--------------------------------------------------");
        printWriter.close();
    }

    @Override
    public void log(String command, String message) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter(OUTPUT_FILE_PATH, true));
        } catch (IOException e) {
        }
        printWriter.println(command);
        printWriter.println();
        printWriter.println(message);
        printWriter.println();
        printWriter.println("--------------------------------------------------");
        printWriter.close();
    }
}
