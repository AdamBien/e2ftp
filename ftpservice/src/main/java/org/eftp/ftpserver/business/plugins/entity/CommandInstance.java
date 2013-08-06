/*
 *
 */
package org.eftp.ftpserver.business.plugins.entity;

import javax.enterprise.util.AnnotationLiteral;
import org.eftp.events.Command;

/**
 *
 * @author adam-bien.com
 */
public class CommandInstance extends AnnotationLiteral<Command> implements Command {

    private org.eftp.events.Command.Name name;

    public CommandInstance(Name name) {
        this.name = name;
    }

    @Override
    public Name value() {
        return this.name;
    }
}
