package org.eftp.ftpserver.st;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Before;
import org.junit.Test;

public class FTPServerWrapperIT {

    private FTPClient client;
    private static final String HOST = "localhost";
    private static final int PORT = 8888;
    private static final String USER = "duke";
    private static final String PASSWORD = "duke";

    @Before
    public void init() {
        this.client = new FTPClient();
    }

    @Test
    public void connect() throws IOException {
        this.client.connect(HOST, PORT);
        this.client.disconnect();
    }

    @Test
    public void login() throws IOException {
        this.client.connect(HOST, PORT);
        this.client.login(USER, PASSWORD);
        this.client.disconnect();
    }

}
