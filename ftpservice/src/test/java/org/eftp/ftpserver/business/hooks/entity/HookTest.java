/*
 *
 */
package org.eftp.ftpserver.business.hooks.entity;

import org.eftp.events.Command;
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
        Hook cut = new Hook("http://localhost", Command.Name.EVERYTHING);
        assertTrue(cut.isValid());
    }

    @Test
    public void invalidUri() {
        Hook cut = new Hook("asdf", Command.Name.EVERYTHING);
        assertFalse(cut.isValid());
    }

}
