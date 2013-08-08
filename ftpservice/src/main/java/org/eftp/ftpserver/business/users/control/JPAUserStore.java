/*
 *
 */
package org.eftp.ftpserver.business.users.control;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.eftp.ftpserver.business.logger.boundary.Log;
import org.eftp.ftpserver.business.users.entity.FtpUser;

/**
 *
 * @author adam-bien.com
 */
public class JPAUserStore {

    @PersistenceContext
    EntityManager em;

    @Inject
    Log LOG;

    public FtpUser find(String userName) {
        return this.em.find(FtpUser.class, userName);
    }

    public void create(FtpUser user) {
        this.em.persist(user);
    }

    public void remove(String userName) {
        FtpUser user = this.em.getReference(FtpUser.class, userName);
        this.em.remove(user);
    }

    public boolean changePassword(String userName, String oldPassword, String newPassword) {
        FtpUser user = find(userName);
        return user.changePassword(oldPassword, newPassword);
    }

    public void createDefaultUser() {
        if (allUsers().isEmpty()) {
            LOG.info("Userstore is empty, creating the default user");
            FtpUser defaultUser = new FtpUser("duke", "duke");
            em.persist(defaultUser);
        } else {
            LOG.info("At least a user already exists, nothing todo");
        }
    }

    public List<FtpUser> allUsers() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FtpUser> cq = cb.createQuery(FtpUser.class);
        Root<FtpUser> rootEntry = cq.from(FtpUser.class);
        CriteriaQuery<FtpUser> all = cq.select(rootEntry);
        TypedQuery<FtpUser> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

}
