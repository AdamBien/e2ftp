/*
 *
 */
package org.eftp.ftpserver.business;

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

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author adam-bien.com
 */
public abstract class RESTSupport {

    protected Client client;
    protected WebTarget mainTarget;
    private String user;
    private String password;

    public void init(String uri, String user, String password) {
        this.user = user;
        this.password = password;
        this.client = ClientBuilder.newClient().register(new Authenticator(user, password));
        this.mainTarget = this.client.target(uri);
    }

    public abstract void init();

}
