/*
 *
 */
package org.eftp.ftpserver.business.hooks.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.eftp.events.Command;

/**
 *
 * @author adam-bien.com
 */
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

}
