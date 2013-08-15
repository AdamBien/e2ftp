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
import org.eftp.events.Command;

/**
 *
 * @author adam-bien.com
 */
@Entity
public class Hook {

    @Id
    @GeneratedValue
    private long id;
    private String uri;

    @Enumerated(EnumType.STRING)
    private Command.Name command;

    public Hook(String uri, Command.Name command) {
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

    public Command.Name getCommand() {
        return command;
    }

    public void setCommand(Command.Name command) {
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
