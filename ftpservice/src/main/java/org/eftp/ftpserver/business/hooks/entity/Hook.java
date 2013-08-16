/*
 *
 */
package org.eftp.ftpserver.business.hooks.entity;

import java.net.MalformedURLException;
import java.net.URL;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import org.eftp.events.FtpEventName;

/**
 *
 * @author adam-bien.com
 */
@Entity
@NamedQuery(name = Hook.findByCommand, query = "SELECT h from Hook h where h.command = :command")
public class Hook {

    private static final String PREFIX = "org.eftp.ftpserver.business.hooks.entity.";
    public static final String findByCommand = PREFIX + "findByCommand";

    @Id
    @GeneratedValue
    private long id;
    private String uri;

    @Enumerated(EnumType.STRING)
    private FtpEventName command;

    public Hook(String uri, FtpEventName command) {
        this.uri = uri;
        this.command = command;
    }

    public Hook() {
    }

    public long getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public FtpEventName getCommand() {
        return command;
    }

    public void setCommand(FtpEventName command) {
        this.command = command;
    }

    boolean isValid() {
        try {
            new URL(this.uri);
        } catch (MalformedURLException ex) {
            return false;
        }
        return this.command != null;
    }

}
