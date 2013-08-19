/*
 *
 */
package org.eftp.ftpserver.business.files.boundary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.ws.rs.core.Response;
import org.eftp.ftpserver.business.RESTSupport;
import org.eftp.ftpserver.st.FTPServerWrapperIT;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author adam-bien.com
 */
public class FilesIT extends RESTSupport {

    private static final String URI = "http://localhost:8080/ftpserver/api/files/";

    private FTPServerWrapperIT support;

    @Before
    @Override
    public void init() {
        super.init(URI);
        this.support = new FTPServerWrapperIT();
        this.support.init();
    }

    @Test
    public void downloadWithUnknownUser() {
        Response response = super.mainTarget.path("-should-not-exist-").request().get();
        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void downloadWithNotExistingFile() {
        Response response = super.mainTarget.path("duke").path("-should-not-exist-").request().get();
        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void downloadFile() throws IOException {
        String fileName = "download-test" + System.currentTimeMillis() + ".txt";
        String content = "random content: " + System.currentTimeMillis();
        this.support.connect();
        this.support.sendFile(fileName, content);
        Response response = super.mainTarget.path("duke").path(fileName).request().get();
        assertThat(response.getStatus(), is(200));
        assertTrue(response.bufferEntity());
        File readFile = response.readEntity(File.class);
        FileReader reader = new FileReader(readFile);
        BufferedReader br = new BufferedReader(reader);
        String actual = br.readLine();
        System.out.println("Read content: " + actual);
        assertThat(actual, is(content));
    }
}
