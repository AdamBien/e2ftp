/*
 *
 */
package org.eftp.ftpserver.business.hooks.boundary;

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
