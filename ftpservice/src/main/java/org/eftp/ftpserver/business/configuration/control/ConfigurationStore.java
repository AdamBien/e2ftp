/*
 *
 */
package org.eftp.ftpserver.business.configuration.control;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.eftp.ftpserver.business.configuration.entity.ConfigurationEntry;
import org.eftp.ftpserver.business.logger.boundary.Log;

/**
 *
 * @author adam-bien.com
 */
public class ConfigurationStore {

    @PersistenceContext
    EntityManager em;

    @Inject
    Log LOG;

    public String find(String name) {
        ConfigurationEntry entry = this.em.find(ConfigurationEntry.class, name);
        if (entry != null) {
            return entry.getValue();
        }
        return null;
    }

    public void setIfNotExist(String name, String value) {
        String actualValue = find(name);
        if (actualValue == null) {
            saveOrUpdate(name, value);
            LOG.info("Saving default value: " + name + " value " + value);
        }
    }

    public void saveOrUpdate(ConfigurationEntry entry) {
        this.em.merge(entry);
    }

    public void remove(String name) {
        ConfigurationEntry ref = this.em.getReference(ConfigurationEntry.class, name);
        this.em.remove(ref);
    }

    public void saveOrUpdate(String name, String value) {
        saveOrUpdate(new ConfigurationEntry(name, value));
    }
}
