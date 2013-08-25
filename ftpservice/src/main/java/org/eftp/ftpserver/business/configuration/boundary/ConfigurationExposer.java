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

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import org.eftp.ftpserver.business.configuration.control.ConfigurationStore;
import org.eftp.ftpserver.business.logger.boundary.Log;

/**
 *
 * @author adam-bien.com
 */
public class ConfigurationExposer {

    @Inject
    ConfigurationStore store;

    @Inject
    Log LOG;

    @Produces
    public Integer getInt(InjectionPoint ip) {
        final String stringValue = getString(ip);
        if (stringValue == null) {
            return 0;
        }
        return Integer.parseInt(stringValue);
    }

    @Produces
    public Long getLong(InjectionPoint ip) {
        final String stringValue = getString(ip);
        if (stringValue == null) {
            return 0l;
        }
        return Long.parseLong(stringValue);
    }

    @Produces
    public Boolean getBoolean(InjectionPoint ip) {
        final String stringValue = getString(ip);
        if (stringValue == null) {
            return false;
        }
        return Boolean.parseBoolean(stringValue);
    }

    @Produces
    public String getString(InjectionPoint ip) {
        String fieldName = ip.getMember().getName();
        String configurationValue = this.store.find(fieldName);
        LOG.info("Found value " + configurationValue + " for : " + fieldName);
        return configurationValue;
    }
}
