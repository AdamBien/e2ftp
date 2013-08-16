/*
 *
 */
package org.eftp.ftpserver.business.plugins.entity;

import javax.enterprise.util.AnnotationLiteral;
import org.eftp.events.Command;
import org.eftp.events.FtpEventName;

/**
 *
 * @author adam-bien.com
 */
public class CommandInstance extends AnnotationLiteral<Command> implements Command {

    private FtpEventName name;

    public CommandInstance(FtpEventName name) {
        this.name = name;
    }

    @Override
    public FtpEventName value() {
        return this.name;
    }
}
