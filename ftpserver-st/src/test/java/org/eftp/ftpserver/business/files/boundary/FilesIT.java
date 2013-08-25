/*
 *
 */
package org.eftp.ftpserver.business.files.boundary;

/*
 * #%L
 * ftpserver-st
 * %%
 * Copyright (C) 2013 e2ftp
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
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
        super.init(URI, "duke", "duke");
        this.support = new FTPServerWrapperIT();
        this.support.init();
    }

    @Test
    public void downloadWithUnknownUser() {
        Client unauthenticated = ClientBuilder.newClient();
        WebTarget unauthenticatedTarget = unauthenticated.target(URI);
        Response response = unauthenticatedTarget.path("-should-not-exist-").request().get();
        assertThat(response.getStatus(), is(401));
    }

    @Test
    public void downloadWithNotExistingFile() {
        Response response = super.mainTarget.path("-should-not-exist-").request().get();
        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void downloadFile() throws IOException {
        String fileName = "download-test" + System.currentTimeMillis() + ".txt";
        String content = "random content: " + System.currentTimeMillis();
        this.support.connect();
        this.support.sendFile(fileName, content);
        Response response = super.mainTarget.path(fileName).request().get();
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
