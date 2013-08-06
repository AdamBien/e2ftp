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
        final FtpEvent ftpEvent = new FtpEvent(session, request);
        this.broadcast(ftpEvent);
        this.sendTo(ftpEvent, Command.Name.LOGIN);
        return super.onLogin(session, request);
    }

    @Override
    public FtpletResult onSite(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(session, request);
        this.broadcast(ftpEvent);
        this.sendTo(ftpEvent, Command.Name.SITE);
        return super.onSite(session, request);
    }

    @Override
    public FtpletResult onRenameEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(session, request);
        this.broadcast(ftpEvent);
        this.sendTo(ftpEvent, Command.Name.RENAME_END);
        return super.onRenameEnd(session, request);
    }

    @Override
    public FtpletResult onRenameStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(session, request);
        this.broadcast(ftpEvent);
        this.sendTo(ftpEvent, Command.Name.RENAME_END);
        return super.onRenameStart(session, request);
    }

    @Override
    public FtpletResult onUploadUniqueEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(session, request);
        this.broadcast(ftpEvent);
        this.sendTo(ftpEvent, Command.Name.RENAME_END);

        return super.onUploadUniqueEnd(session, request);
    }

    @Override
    public FtpletResult onUploadUniqueStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(session, request);
        this.broadcast(ftpEvent);
        this.sendTo(ftpEvent, Command.Name.UPLOAD_UNIQUE_START);

        return super.onUploadUniqueStart(session, request);
    }

    @Override
    public FtpletResult onAppendEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(session, request);
        this.broadcast(ftpEvent);
        this.sendTo(ftpEvent, Command.Name.APPEND_END);
        return super.onAppendEnd(session, request);
    }

    @Override
    public FtpletResult onAppendStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(session, request);
        this.broadcast(ftpEvent);
        this.sendTo(ftpEvent, Command.Name.APPEND_START);
        return super.onAppendStart(session, request);
    }

    @Override
    public FtpletResult onMkdirEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(session, request);
        this.broadcast(ftpEvent);
        this.sendTo(ftpEvent, Command.Name.MKDIR_END);
        return super.onMkdirEnd(session, request);
    }

    @Override
    public FtpletResult onMkdirStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(session, request);
        this.broadcast(ftpEvent);
        this.sendTo(ftpEvent, Command.Name.MKDIR_START);
        return super.onMkdirStart(session, request);
    }

    @Override
    public FtpletResult onRmdirEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(session, request);
        this.broadcast(ftpEvent);
        this.sendTo(ftpEvent, Command.Name.RMDIR_END);
        return super.onRmdirEnd(session, request);
    }

    @Override
    public FtpletResult onRmdirStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(session, request);
        this.broadcast(ftpEvent);
        this.sendTo(ftpEvent, Command.Name.RMDIR_START);
        return super.onRmdirStart(session, request);
    }

    @Override
    public FtpletResult onDownloadEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(session, request);
        this.broadcast(ftpEvent);
        this.sendTo(ftpEvent, Command.Name.DOWNLOAD_END);
        return super.onDownloadEnd(session, request);
    }

    @Override
    public FtpletResult onDownloadStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(session, request);
        this.broadcast(ftpEvent);
        this.sendTo(ftpEvent, Command.Name.DOWNLOAD_START);
        return super.onDownloadStart(session, request);
    }

    @Override
    public FtpletResult onUploadEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(session, request);
        this.broadcast(ftpEvent);
        this.sendTo(ftpEvent, Command.Name.UPLOAD_END);
        return super.onUploadEnd(session, request);
    }

    @Override
    public FtpletResult onUploadStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(session, request);
        this.broadcast(ftpEvent);
        this.sendTo(ftpEvent, Command.Name.UPLOAD_START);
        return super.onUploadStart(session, request);
    }

    @Override
    public FtpletResult onDeleteEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(session, request);
        this.broadcast(ftpEvent);
        this.sendTo(ftpEvent, Command.Name.DELETE_END);
        return super.onDeleteEnd(session, request);
    }

    @Override
    public FtpletResult onDeleteStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(session, request);
        this.broadcast(ftpEvent);
        this.sendTo(ftpEvent, Command.Name.DELETE_START);
        return super.onDeleteStart(session, request);
    }

    @Override
    public FtpletResult onDisconnect(FtpSession session) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(session);
        this.broadcast(ftpEvent);
        this.sendTo(ftpEvent, Command.Name.DISCONNECT);

        return super.onDisconnect(session);
    }

    @Override
    public FtpletResult onConnect(FtpSession session) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(session);
        this.broadcast(ftpEvent);
        this.sendTo(ftpEvent, Command.Name.CONNECT);
        return super.onConnect(session);
    }

    void broadcast(FtpEvent ftpEvent) {
        this.all.fire(ftpEvent);
    }

    void sendTo(FtpEvent ftpEvent, Command.Name name) {
        events.select(new CommandInstance(name)).fire(ftpEvent);
    }

}
