package org.eftp.ftpserver.st;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
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
    public void connectAndDisconnect() throws IOException {
        this.client.connect(HOST, PORT);
        this.client.disconnect();
    }

    @Test
    public void login() throws IOException {
        connect();
    }

    @Test
    public void list() throws IOException {
        connect();
        FTPFile[] listDirectories = this.client.listDirectories();
        assertNotNull(listDirectories);
        assertTrue(listDirectories.length > 0);
    }

    @Test
    public void sendAndReceive() throws IOException {
        connect();
        String remoteFile = "e2ftp-client" + System.currentTimeMillis() + ".txt";
        final String expected = "just a test";
        ByteArrayInputStream bais = new ByteArrayInputStream(expected.getBytes());
        this.client.storeFile(remoteFile, bais);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        this.client.retrieveFile(remoteFile, baos);
        byte[] actual = baos.toByteArray();
        assertNotNull(actual);
        assertThat(new String(actual), is(expected));
        this.client.deleteFile(remoteFile);

        baos = new ByteArrayOutputStream();
        boolean fileExists = this.client.retrieveFile(remoteFile, baos);
        assertFalse(fileExists);
    }

    @After
    public void cleanup() throws IOException {
        this.client.disconnect();
    }

    private void connect() throws IOException {
        this.client.connect(HOST, PORT);
        this.client.login(USER, PASSWORD);
    }

}
