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

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import static org.eftp.ftpserver.business.users.control.Digester.computeHash;

/**
 *
 * @author adam-bien.com
 */
@Entity
@NamedQueries({
    @NamedQuery(name = FtpUser.isAdmin, query = "SELECT f FROM FtpUser f where f.admin = TRUE"),
    @NamedQuery(name = FtpUser.authenticate, query = "SELECT f FROM FtpUser f where f.userName = :userName and f.password = :password"),})
public class FtpUser {

    private static final String PREFIX = "org.eftp.ftpserver.business.users.entity.FtpUser.";
    public static final String isAdmin = PREFIX + "isAdmin";
    public static final String authenticate = PREFIX + "authenticate";

    @Id
    @Column(name = "c_username")
    private String userName;
    private String password;

    @Column(name = "c_admin")
    private boolean admin;
    private int maxIdleTimeSec;
    private String homeDir;
    private boolean enabled;

    @JoinColumn(name = "c_username")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FtpPermission> permissions;

    @OneToMany
    @JoinColumn(name = "c_username")
    private List<FtpGroup> groups;

    public FtpUser(String userName, String password) {
        this();
        this.userName = userName;
        this.password = computeHash(password);
    }

    public FtpUser() {
        this.groups = new ArrayList<>();
        this.permissions = new ArrayList<>();
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        String oldHashed = computeHash(oldPassword);
        String newHashed = computeHash(newPassword);
        if (this.password.equals(oldHashed)) {
            this.password = newHashed;
            return true;
        } else {
            return false;
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getMaxIdleTimeSec() {
        return maxIdleTimeSec;
    }

    public void setMaxIdleTimeSec(int maxIdleTimeSec) {
        this.maxIdleTimeSec = maxIdleTimeSec;
    }

    public String getHomeDir() {
        return homeDir;
    }

    public void setHomeDir(String homeDir) {
        this.homeDir = homeDir;
    }

    public boolean isIsEnabled() {
        return enabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.enabled = isEnabled;
    }

    public void addPermission(FtpPermission permission) {
        this.permissions.add(permission);
    }

    public void addGroup(FtpGroup group) {
        this.groups.add(group);
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public BaseUser getBaseUser() {
        BaseUser baseUser = new BaseUser();
        baseUser.setHomeDirectory(homeDir);
        baseUser.setName(userName);
        baseUser.setPassword(password);
        baseUser.setMaxIdleTime(maxIdleTimeSec);
        baseUser.setEnabled(this.enabled);
        baseUser.setAuthorities(getAuthorities());
        return baseUser;
    }

    public List<Authority> getAuthorities() {
        List<Authority> retVal = new ArrayList<>();
        for (FtpPermission permission : this.permissions) {
            retVal.add(permission.getAuthority());
        }
        return retVal;
    }
}
