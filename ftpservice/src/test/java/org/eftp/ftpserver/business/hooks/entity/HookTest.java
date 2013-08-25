/*
 *
 */
package org.eftp.ftpserver.business.hooks.entity;

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
