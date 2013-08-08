/*
 *
 */
package org.eftp.ftpserver.business.users.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author adam-bien.com
 */
@Entity
public class FtpUser {

    @Id
    @Column(name = "c_username")
    private String userName;
    private String password;

    @OneToMany
    private List<FtpGroup> groups;

    public FtpUser(String userName, String password) {
        this();
        this.userName = userName;
        this.password = password;
    }

    public FtpUser() {
        this.groups = new ArrayList<>();
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            return true;
        } else {
            return false;
        }
    }

}
