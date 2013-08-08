/*
 *
 */
package org.eftp.ftpserver.business.configuration.boundary;

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
    public String getString(InjectionPoint ip) {
        String fieldName = ip.getMember().getName();
        String configurationValue = this.store.find(fieldName);
        LOG.info("Found value " + configurationValue + " for : " + fieldName);
        return configurationValue;
    }
}
