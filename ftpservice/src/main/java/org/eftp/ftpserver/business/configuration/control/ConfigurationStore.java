/*
 *
 */
package org.eftp.ftpserver.business.configuration.control;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        ConfigurationEntry entry = findEntry(name);
        if (entry != null) {
            return entry.getValue();
        }
        return null;

    }

    public ConfigurationEntry findEntry(String name) {
        return this.em.find(ConfigurationEntry.class, name);
    }

    public void setIfNotExist(String name, String value) {
        String actualValue = find(name);
        if (actualValue == null) {
            saveOrUpdate(name, value);
            LOG.info("Saving default value: " + name + " value " + value);
        }
    }

    public ConfigurationEntry saveOrUpdate(ConfigurationEntry entry) {
        return this.em.merge(entry);
    }

    public void remove(String name) {
        ConfigurationEntry ref = this.em.getReference(ConfigurationEntry.class, name);
        this.em.remove(ref);
    }

    public void saveOrUpdate(String name, String value) {
        saveOrUpdate(new ConfigurationEntry(name, value));
    }

    public List<ConfigurationEntry> allEntries() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ConfigurationEntry> cq = cb.createQuery(ConfigurationEntry.class);
        Root<ConfigurationEntry> rootEntry = cq.from(ConfigurationEntry.class);
        CriteriaQuery<ConfigurationEntry> all = cq.select(rootEntry);
        TypedQuery<ConfigurationEntry> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
}
