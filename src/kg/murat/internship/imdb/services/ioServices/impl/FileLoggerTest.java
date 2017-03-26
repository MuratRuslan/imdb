package kg.murat.internship.imdb.services.ioServices.impl;

import kg.murat.internship.imdb.services.ioServices.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Fujitsu on 26.03.2017.
 */
public class FileLoggerTest {
    Logger logger;
    @Before
    public void setUp() throws Exception {
        logger = new FileLogger("output.txt");

    }

    @Test
    public void log() throws Exception {
        logger.log("sldfj", "slkfjs");
    }

}