package kg.murat.internship.imdb;

import kg.murat.internship.imdb.runners.CommandLineRunner;
import kg.murat.internship.imdb.runners.Runner;

public class Main {

    public static void main(String[] args) {
        Runner runner = new CommandLineRunner(args);
        runner.run();
    }
}
