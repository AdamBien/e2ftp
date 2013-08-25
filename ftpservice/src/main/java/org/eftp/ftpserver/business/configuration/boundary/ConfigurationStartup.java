/*
 *
 */
package org.eftp.ftpserver.business.configuration.boundary;

/*
 * #%L
 * ftpservice
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

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.eftp.ftpserver.business.configuration.control.ConfigurationStore;
import org.eftp.ftpserver.business.logger.boundary.Log;

/**
 *
 * @author adam-bien.com
 */
@Startup
@Singleton
public class ConfigurationStartup {

    @Inject
    ConfigurationStore store;

    @Inject
    Log LOG;

    @PostConstruct
    public void establishDefaults() {
        LOG.info("Establishing defaults");
        this.store.saveOrUpdate("serverPort", "8888");
        this.store.saveOrUpdate("maxLogins", "10");
        this.store.saveOrUpdate("anonymousLoginEnabled", "false");
        this.store.saveOrUpdate("corePoolSize", String.valueOf(2));
        this.store.saveOrUpdate("maximumPoolSize", String.valueOf(8));
        this.store.saveOrUpdate("keepAliveTimeInHours", String.valueOf(1));
        this.store.saveOrUpdate("incomingRequestQueueDepth", String.valueOf(10));

        LOG.info("Default values are written");
    }

}
