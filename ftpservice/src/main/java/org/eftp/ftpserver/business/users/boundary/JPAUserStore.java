/*
 *
 */
package org.eftp.ftpserver.business.users.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.eftp.ftpserver.business.logger.boundary.Log;
import org.eftp.ftpserver.business.users.entity.FtpConcurrentLoginPermission;
import org.eftp.ftpserver.business.users.entity.FtpUser;
import org.eftp.ftpserver.business.users.entity.FtpWritePermission;

/**
 *
 * @author adam-bien.com
 */
@Stateless
public class JPAUserStore {

    @PersistenceContext
    EntityManager em;

    @Inject
    Log LOG;

    private static final String DEFAULT_USER_DIRECTORY = System.getProperty("java.io.tmpdir");
    private static final int DEFAULT_NUMBER_OF_CONCURRENT_LOGINS = 8;
    private static final int DEFAULT_NUMBER_OF_CONCURRENT_LOGINS_FROM_SAME_IP = 8;

    public FtpUser find(String userName) {
        return this.em.find(FtpUser.class, userName);
    }

    public FtpUser findAdmin() {
        return this.em.createNamedQuery(FtpUser.isAdmin, FtpUser.class).getSingleResult();
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
            FtpUser defaultUser = getDefaultUser("duke", "duke");
            em.persist(defaultUser);
        } else {
            LOG.info("At least a user already exists, nothing todo");
        }
    }

    public FtpUser getDefaultUser(String user, String password) {
        FtpUser defaultUser = new FtpUser(user, password);
        defaultUser.setIsEnabled(true);
        defaultUser.setHomeDir(DEFAULT_USER_DIRECTORY);
        defaultUser.addPermission(new FtpWritePermission(DEFAULT_USER_DIRECTORY));
        defaultUser.addPermission(new FtpConcurrentLoginPermission(DEFAULT_NUMBER_OF_CONCURRENT_LOGINS, DEFAULT_NUMBER_OF_CONCURRENT_LOGINS_FROM_SAME_IP));
        defaultUser.setMaxIdleTimeSec(3600);
        return defaultUser;
    }

    public List<FtpUser> allUsers() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FtpUser> cq = cb.createQuery(FtpUser.class);
        Root<FtpUser> rootEntry = cq.from(FtpUser.class);
        CriteriaQuery<FtpUser> all = cq.select(rootEntry);
        TypedQuery<FtpUser> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public boolean isAdmin(String userName) {
        FtpUser user = find(userName);
        return (user != null && user.isAdmin());

    }

    public FtpUser find(String username, String password) {
        return this.em.createNamedQuery(FtpUser.authenticate, FtpUser.class).setParameter("userName", username).setParameter("password", password).getSingleResult();
    }

}
