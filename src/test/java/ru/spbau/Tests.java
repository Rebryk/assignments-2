package ru.spbau;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
//import java.util.List;
import static org.junit.Assert.assertArrayEquals;

/**
 * Created by rebryk on 10/03/16.
 */
public class Tests {
    public static final int PORT = 5000;
    public static final String HOSTNAME = "localhost";

    //public static final String DIR_PATH = "src/test/resources/test";
    public static final String FILE_PATH = "src/test/resources/test/1.png";

    @Test
    public void testFtp() throws IOException {
        FtpServer server = new FtpServer(PORT);
        FtpClient client = new FtpClient(HOSTNAME, PORT);

        server.start();
        client.start();

        //List<String> list = client.getList(DIR_PATH);
        //Assert.assertNotNull(list);

        InputStream file = Files.newInputStream(Paths.get(FILE_PATH));
        InputStream data = client.getFile(FILE_PATH);

        Assert.assertNotNull(data);
        assertArrayEquals(IOUtils.toByteArray(file), IOUtils.toByteArray(data));

        server.stop();
        client.stop();
    }
}