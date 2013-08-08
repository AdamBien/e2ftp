/*
 *
 */
package org.eftp.ftpserver.business.users.control;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.eftp.ftpserver.business.users.entity.FtpUser;

/**
 *
 * @author adam-bien.com
 */
public class JPAUserStore {

    @PersistenceContext
    EntityManager em;

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
}
