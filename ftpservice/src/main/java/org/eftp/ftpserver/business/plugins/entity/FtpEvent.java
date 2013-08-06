/*
 *
 */
package org.eftp.ftpserver.business.plugins.entity;

import org.apache.ftpserver.ftplet.FtpRequest;
import org.apache.ftpserver.ftplet.FtpSession;

/**
 *
 * @author adam-bien.com
 */
public class FtpEvent {

    private final FtpSession session;
    private final FtpRequest request;

    public FtpEvent(FtpSession session, FtpRequest request) {
        this.session = session;
        this.request = request;
    }

    public FtpEvent(FtpSession session) {
        this(session, null);
    }

    public FtpSession getSession() {
        return session;
    }

    public FtpRequest getRequest() {
        return request;
    }

    @Override
    public String toString() {
        return "FtpEvent{" + "session=" + session + ", request=" + request + '}';
    }

}
