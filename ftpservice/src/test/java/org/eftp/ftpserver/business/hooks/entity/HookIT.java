/*
 *
 */
package org.eftp.ftpserver.business.hooks.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.eftp.events.FtpEventName;
import org.junit.Assert;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author adam-bien.com
 */
public class HookIT {

    private EntityManager em;
    private EntityTransaction tx;

    @Before
    public void initEM() {
        this.em = Persistence.createEntityManagerFactory("it").createEntityManager();
        this.tx = this.em.getTransaction();
    }

    @Test
    public void crud() {
        Hook cut = new Hook("asdf", FtpEventName.EVERYTHING);
        this.tx.begin();
        cut = this.em.merge(cut);
        this.tx.commit();
        long id = cut.getId();

        Hook found = this.em.find(Hook.class, id);
        Assert.assertSame(cut, found);

        this.tx.begin();
        this.em.remove(found);
        this.tx.commit();

        found = this.em.find(Hook.class, id);
        assertNull(found);
    }

}
