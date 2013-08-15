/*
 *
 */
package org.eftp.ftpserver.business.plugins.boundary;

import java.io.IOException;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import org.apache.ftpserver.ftplet.DefaultFtplet;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.FtpRequest;
import org.apache.ftpserver.ftplet.FtpSession;
import org.apache.ftpserver.ftplet.FtpletResult;
import org.eftp.events.Command;
import org.eftp.events.FtpEvent;
import org.eftp.ftpserver.business.plugins.entity.CommandInstance;

/**
 *
 * @author adam-bien.com
 */
@Plugin
public class FtpletHook extends DefaultFtplet {

    @Inject
    Event<FtpEvent> events;

    @Inject
    @Command
    Event<FtpEvent> all;

    @Override
    public FtpletResult onLogin(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(Command.Name.LOGIN, session, request);
        this.broadcast(ftpEvent);
        return super.onLogin(session, request);
    }

    @Override
    public FtpletResult onSite(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(Command.Name.SITE, session, request);
        this.broadcast(ftpEvent);
        return super.onSite(session, request);
    }

    @Override
    public FtpletResult onRenameEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(Command.Name.RENAME_END, session, request);
        this.broadcast(ftpEvent);
        return super.onRenameEnd(session, request);
    }

    @Override
    public FtpletResult onRenameStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(Command.Name.RENAME_END, session, request);
        this.broadcast(ftpEvent);
        return super.onRenameStart(session, request);
    }

    @Override
    public FtpletResult onUploadUniqueEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(Command.Name.UPLOAD_UNIQUE_END, session, request);
        this.broadcast(ftpEvent);
        return super.onUploadUniqueEnd(session, request);
    }

    @Override
    public FtpletResult onUploadUniqueStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(Command.Name.UPLOAD_UNIQUE_START, session, request);
        this.broadcast(ftpEvent);
        return super.onUploadUniqueStart(session, request);
    }

    @Override
    public FtpletResult onAppendEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(Command.Name.APPEND_END, session, request);
        this.broadcast(ftpEvent);
        return super.onAppendEnd(session, request);
    }

    @Override
    public FtpletResult onAppendStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(Command.Name.APPEND_START, session, request);
        this.broadcast(ftpEvent);
        return super.onAppendStart(session, request);
    }

    @Override
    public FtpletResult onMkdirEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(Command.Name.MKDIR_END, session, request);
        this.broadcast(ftpEvent);
        return super.onMkdirEnd(session, request);
    }

    @Override
    public FtpletResult onMkdirStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(Command.Name.MKDIR_START, session, request);
        this.broadcast(ftpEvent);
        return super.onMkdirStart(session, request);
    }

    @Override
    public FtpletResult onRmdirEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(Command.Name.RMDIR_END, session, request);
        this.broadcast(ftpEvent);
        return super.onRmdirEnd(session, request);
    }

    @Override
    public FtpletResult onRmdirStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(Command.Name.RMDIR_START, session, request);
        this.broadcast(ftpEvent);
        return super.onRmdirStart(session, request);
    }

    @Override
    public FtpletResult onDownloadEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(Command.Name.DOWNLOAD_END, session, request);
        this.broadcast(ftpEvent);
        return super.onDownloadEnd(session, request);
    }

    @Override
    public FtpletResult onDownloadStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(Command.Name.DOWNLOAD_START, session, request);
        this.broadcast(ftpEvent);
        return super.onDownloadStart(session, request);
    }

    @Override
    public FtpletResult onUploadEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(Command.Name.UPLOAD_END, session, request);
        this.broadcast(ftpEvent);
        return super.onUploadEnd(session, request);
    }

    @Override
    public FtpletResult onUploadStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(Command.Name.UPLOAD_START, session, request);
        this.broadcast(ftpEvent);
        return super.onUploadStart(session, request);
    }

    @Override
    public FtpletResult onDeleteEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(Command.Name.DELETE_END, session, request);
        this.broadcast(ftpEvent);
        return super.onDeleteEnd(session, request);
    }

    @Override
    public FtpletResult onDeleteStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(Command.Name.DELETE_START, session, request);
        this.broadcast(ftpEvent);
        return super.onDeleteStart(session, request);
    }

    @Override
    public FtpletResult onDisconnect(FtpSession session) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(Command.Name.DISCONNECT, session);
        this.broadcast(ftpEvent);
        return super.onDisconnect(session);
    }

    @Override
    public FtpletResult onConnect(FtpSession session) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(Command.Name.CONNECT, session);
        this.broadcast(ftpEvent);
        return super.onConnect(session);
    }

    void broadcast(FtpEvent ftpEvent) {
        sendToDedicatedChannel(ftpEvent);
        this.all.fire(ftpEvent);
    }

    void sendToDedicatedChannel(FtpEvent ftpEvent) {
        events.select(new CommandInstance(ftpEvent.getCommand())).fire(ftpEvent);
    }

}
