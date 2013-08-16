/*
 *
 */
package org.eftp.ftpserver.business.hooks.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.eftp.events.FtpEventName;
import org.eftp.ftpserver.business.hooks.entity.Checks;
import org.eftp.ftpserver.business.hooks.entity.Hook;

/**
 *
 * @author adam-bien.com
 */
@Stateless
public class HooksRegistry {

    @PersistenceContext
    EntityManager em;

    public long saveOrUpdate(@Checks Hook hook) {
        return this.em.merge(hook).getId();
    }

    public Hook find(long id) {
        return this.em.find(Hook.class, id);
    }

    public void remove(long id) {
        Hook reference = this.em.getReference(Hook.class, id);
        this.em.remove(reference);
    }

    public List<Hook> all() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Hook> cq = cb.createQuery(Hook.class);
        Root<Hook> rootEntry = cq.from(Hook.class);
        CriteriaQuery<Hook> all = cq.select(rootEntry);
        TypedQuery<Hook> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public List<Hook> findByCommand(FtpEventName name) {
        return this.em.createNamedQuery(Hook.findByCommand, Hook.class).
                setParameter("command", name).
                getResultList();
    }
}
