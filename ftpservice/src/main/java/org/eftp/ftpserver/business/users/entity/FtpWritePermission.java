/*
 *
 */
package org.eftp.ftpserver.business.users.entity;

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
