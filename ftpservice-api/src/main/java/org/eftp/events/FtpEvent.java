/*
 *
 */
package org.eftp.events;

import org.apache.ftpserver.ftplet.FtpRequest;
import org.apache.ftpserver.ftplet.FtpSession;

/**
 *
 * @author adam-bien.com
 */
public class FtpEvent {

    private final FtpSession session;
    private final FtpRequest request;
    private final Command.Name command;

    public FtpEvent(Command.Name command, FtpSession session, FtpRequest request) {
        this.command = command;
        this.session = session;
        this.request = request;
    }

    public FtpEvent(Command.Name command, FtpSession session) {
        this(command, session, null);
    }

    public FtpEvent(FtpSession session) {
        this(Command.Name.EVERYTHING, session, null);
    }

    public FtpSession getSession() {
        return session;
    }

    public FtpRequest getRequest() {
        return request;
    }

    public Command.Name getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return "FtpEvent{" + "session=" + session + ", request=" + request + '}';
    }

}
