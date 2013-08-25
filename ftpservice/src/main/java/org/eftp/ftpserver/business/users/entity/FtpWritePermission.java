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
import org.apache.ftpserver.usermanager.impl.WritePermission;

/**
 *
 * @author adam-bien.com
 */
@Entity
public class FtpWritePermission extends FtpPermission {

    private String permissionRoot;

    public FtpWritePermission() {
        this("/");
    }

    public FtpWritePermission(String permissionRoot) {
        this.permissionRoot = permissionRoot;
    }

    public String getPermissionRoot() {
        return permissionRoot;
    }

    public void setPermissionRoot(String permissionRoot) {
        this.permissionRoot = permissionRoot;
    }

    @Override
    public Authority getAuthority() {
        return new WritePermission(permissionRoot);
    }
}
