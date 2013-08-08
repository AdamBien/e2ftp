/*
 *
 */
package org.eftp.ftpserver.business.users.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.usermanager.impl.WritePermission;

/**
 *
 * @author adam-bien.com
 */
@Entity
public class FtpWritePermission extends FtpPermission {

    @Id
    private Long id;
    private String permissionRoot;

    public FtpWritePermission() {
    }

    public FtpWritePermission(String permissionRoot) {
        this.permissionRoot = permissionRoot;
    }

    public Long getId() {
        return id;
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
