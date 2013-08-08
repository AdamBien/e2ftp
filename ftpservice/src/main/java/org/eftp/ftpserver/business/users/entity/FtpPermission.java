/*
 *
 */
package org.eftp.ftpserver.business.users.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.apache.ftpserver.ftplet.Authority;

/**
 *
 * @author adam-bien.com
 */
@MappedSuperclass
public abstract class FtpPermission {

    @Id
    @GeneratedValue
    private long id;

    public abstract Authority getAuthority();

}
