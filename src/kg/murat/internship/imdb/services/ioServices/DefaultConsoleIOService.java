package kg.murat.internship.imdb.services.ioServices;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Fujitsu on 21.03.2017.
 */
public class DefaultConsoleIOService implements IOService {

    private Scanner scanner;

    public DefaultConsoleIOService() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void write(String msg) {
        System.out.println(msg);
    }

    @Override
    public void log(String command, String message) throws IOException {
        //ToDo
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }


}
