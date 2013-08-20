/*
 *
 */
package org.eftp.ftpserver.business.users.control;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author adam-bien.com
 */
public class DigesterTester {

    @Test
    public void digest() {
        String first = Digester.computeHash("aa");
        String second = Digester.computeHash("aa");
        String different = Digester.computeHash("ab");
        assertThat(first, is(second));
        assertThat(first, is(not(different)));
        System.out.println("First: " + first);
        System.out.println("Second: " + second);
        System.out.println("Different: " + different);
    }
}
