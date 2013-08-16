/*
 *
 */
package org.eftp.ftpserver.business.hooks.boundary;

import java.util.List;
import org.eftp.events.FtpEventName;
import org.eftp.ftpserver.business.PersistenceSupport;
import org.eftp.ftpserver.business.hooks.entity.Hook;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author adam-bien.com
 */
public class HooksRegistryIT extends PersistenceSupport {

    HooksRegistry cut;

    @Before
    public void init() {
        super.initEM();
        this.cut = new HooksRegistry();
        this.cut.em = super.em;

    }

    @Test
    public void findByCommand() {
        List<Hook> foundHooks = this.cut.findByCommand(FtpEventName.EVERYTHING);
        assertTrue(foundHooks.isEmpty());

        Hook hook = new Hook("http://localhost", FtpEventName.EVERYTHING);
        this.tx.begin();
        long id = this.cut.saveOrUpdate(hook);
        this.tx.commit();

        hook = this.cut.find(id);

        foundHooks = this.cut.findByCommand(FtpEventName.EVERYTHING);
        assertTrue(foundHooks.contains(hook));
    }

}
