/*
 *
 */
package org.eftp.ftpserver.business.hooks.entity;

import org.eftp.events.FtpEventName;
import org.eftp.ftpserver.business.PersistenceSupport;
import org.junit.Assert;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author adam-bien.com
 */
public class HookIT extends PersistenceSupport {

    @Before
    public void init() {
        super.initEM();
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
