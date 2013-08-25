/*
 *
 */
package org.eftp.ftpserver.business.users.entity;

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

import javax.persistence.Entity;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.usermanager.impl.ConcurrentLoginPermission;

/**
 *
 * @author adam-bien.com
 */
@Entity
public class FtpConcurrentLoginPermission extends FtpPermission {

    private int maxConcurrentLogins;
    private int maxConcurrentLoginsPerIP;

    public FtpConcurrentLoginPermission(int maxConcurrentLogins, int maxConcurrentLoginsPerIP) {
        this.maxConcurrentLogins = maxConcurrentLogins;
        this.maxConcurrentLoginsPerIP = maxConcurrentLoginsPerIP;
    }

    public FtpConcurrentLoginPermission() {
    }

    public int getMaxConcurrentLogins() {
        return maxConcurrentLogins;
    }

    public void setMaxConcurrentLogins(int maxConcurrentLogins) {
        this.maxConcurrentLogins = maxConcurrentLogins;
    }

    public int getMaxConcurrentLoginsPerIP() {
        return maxConcurrentLoginsPerIP;
    }

    public void setMaxConcurrentLoginsPerIP(int maxConcurrentLoginsPerIP) {
        this.maxConcurrentLoginsPerIP = maxConcurrentLoginsPerIP;
    }

    @Override
    public Authority getAuthority() {
        return new ConcurrentLoginPermission(maxConcurrentLogins, maxConcurrentLoginsPerIP);
    }
}
