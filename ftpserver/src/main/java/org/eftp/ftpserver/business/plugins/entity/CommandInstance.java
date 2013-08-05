/*
 *
 */
package org.eftp.ftpserver.business.plugins.entity;

import javax.enterprise.util.AnnotationLiteral;

/**
 *
 * @author adam-bien.com
 */
public class CommandInstance extends AnnotationLiteral<Command> implements Command {

    private Name name;

    public CommandInstance(Name name) {
        this.name = name;
    }

    @Override
    public Name value() {
        return this.name;
    }
}
