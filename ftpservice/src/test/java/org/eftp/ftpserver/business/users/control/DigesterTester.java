/*
 *
 */
package org.eftp.ftpserver.business.users.control;

/*
 * #%L
 * ftpservice
 * %%
 * Copyright (C) 2013 e2ftp
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
