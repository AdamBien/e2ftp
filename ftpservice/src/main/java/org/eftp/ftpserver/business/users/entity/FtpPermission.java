/*
 *
 */
package org.eftp.ftpserver.business.users.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.apache.ftpserver.ftplet.Authority;

/**
 *
 * @author adam-bien.com
 */
@Entity
public abstract class FtpPermission {

    @Id
    @GeneratedValue
    private long id;

    public long getId() {
        return id;
    }

    public abstract Authority getAuthority();

}
