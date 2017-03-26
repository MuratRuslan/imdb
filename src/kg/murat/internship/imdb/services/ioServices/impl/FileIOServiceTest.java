package kg.murat.internship.imdb.services.ioServices.impl;

import kg.murat.internship.imdb.services.ioServices.IOService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Fujitsu on 26.03.2017.
 */
public class FileIOServiceTest {
    IOService ioService;
    @Before
    public void setUp() throws Exception {
        ioService = new FileIOService("commands.txt");
    }

    @Test
    public void write() throws Exception {

    }

    @Test
    public void readLine() throws Exception {
        String next;
        while (null != (next = ioService.readLine())) {
            System.out.println(next);
        }
    }

}