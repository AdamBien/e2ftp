/*
 *
 */
package org.e2ftp.testclient.business.hooks.boundary;

/*
 * #%L
 * hook-test-client
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

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author adam-bien.com
 */
@Path("callbacks")
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class CallbacksResource {

    private List<JsonObject> callbacks;

    @PostConstruct
    public void init() {
        this.callbacks = new ArrayList<>();
    }

    @POST
    public void onCallaback(JsonObject object) {
        this.callbacks.add(object);
    }

    public List<JsonObject> getCallbackObject() {
        return callbacks;
    }

    public void clear() {
        this.callbacks.clear();
    }

}
