/*
 *
 */
package org.eftp.ftpserver.business.users.entity;

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
