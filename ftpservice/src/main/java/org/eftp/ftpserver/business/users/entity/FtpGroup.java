/*
 *
 */
package org.eftp.ftpserver.business.users.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author adam-bien.com
 */
@Entity
public class FtpGroup {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
