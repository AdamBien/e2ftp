/*
 *
 */
package org.eftp.ftpserver.business.hooks.entity;

import org.eftp.events.FtpEventName;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author adam-bien.com
 */
public class HookTest {

    @Test
    public void validUri() {
        Hook cut = new Hook("http://localhost", FtpEventName.EVERYTHING);
        assertTrue(cut.isValid());
    }

    @Test
    public void invalidUri() {
        Hook cut = new Hook("asdf", FtpEventName.EVERYTHING);
        assertFalse(cut.isValid());
    }

}
