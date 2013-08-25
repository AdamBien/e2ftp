/*
 *
 */
package org.eftp.ftpserver.business.plugins.boundary;

/*
 * #%L
 * ftpservice
 * %%
 * Copyright (C) 2013 e2ftp
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
import org.eftp.events.FtpEventName;
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
        final FtpEvent ftpEvent = new FtpEvent(FtpEventName.LOGIN, session, request);
        this.broadcast(ftpEvent);
        return super.onLogin(session, request);
    }

    @Override
    public FtpletResult onSite(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(FtpEventName.SITE, session, request);
        this.broadcast(ftpEvent);
        return super.onSite(session, request);
    }

    @Override
    public FtpletResult onRenameEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(FtpEventName.RENAME_END, session, request);
        this.broadcast(ftpEvent);
        return super.onRenameEnd(session, request);
    }

    @Override
    public FtpletResult onRenameStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(FtpEventName.RENAME_END, session, request);
        this.broadcast(ftpEvent);
        return super.onRenameStart(session, request);
    }

    @Override
    public FtpletResult onUploadUniqueEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(FtpEventName.UPLOAD_UNIQUE_END, session, request);
        this.broadcast(ftpEvent);
        return super.onUploadUniqueEnd(session, request);
    }

    @Override
    public FtpletResult onUploadUniqueStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(FtpEventName.UPLOAD_UNIQUE_START, session, request);
        this.broadcast(ftpEvent);
        return super.onUploadUniqueStart(session, request);
    }

    @Override
    public FtpletResult onAppendEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(FtpEventName.APPEND_END, session, request);
        this.broadcast(ftpEvent);
        return super.onAppendEnd(session, request);
    }

    @Override
    public FtpletResult onAppendStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(FtpEventName.APPEND_START, session, request);
        this.broadcast(ftpEvent);
        return super.onAppendStart(session, request);
    }

    @Override
    public FtpletResult onMkdirEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(FtpEventName.MKDIR_END, session, request);
        this.broadcast(ftpEvent);
        return super.onMkdirEnd(session, request);
    }

    @Override
    public FtpletResult onMkdirStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(FtpEventName.MKDIR_START, session, request);
        this.broadcast(ftpEvent);
        return super.onMkdirStart(session, request);
    }

    @Override
    public FtpletResult onRmdirEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(FtpEventName.RMDIR_END, session, request);
        this.broadcast(ftpEvent);
        return super.onRmdirEnd(session, request);
    }

    @Override
    public FtpletResult onRmdirStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(FtpEventName.RMDIR_START, session, request);
        this.broadcast(ftpEvent);
        return super.onRmdirStart(session, request);
    }

    @Override
    public FtpletResult onDownloadEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(FtpEventName.DOWNLOAD_END, session, request);
        this.broadcast(ftpEvent);
        return super.onDownloadEnd(session, request);
    }

    @Override
    public FtpletResult onDownloadStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(FtpEventName.DOWNLOAD_START, session, request);
        this.broadcast(ftpEvent);
        return super.onDownloadStart(session, request);
    }

    @Override
    public FtpletResult onUploadEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(FtpEventName.UPLOAD_END, session, request);
        this.broadcast(ftpEvent);
        return super.onUploadEnd(session, request);
    }

    @Override
    public FtpletResult onUploadStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(FtpEventName.UPLOAD_START, session, request);
        this.broadcast(ftpEvent);
        return super.onUploadStart(session, request);
    }

    @Override
    public FtpletResult onDeleteEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(FtpEventName.DELETE_END, session, request);
        this.broadcast(ftpEvent);
        return super.onDeleteEnd(session, request);
    }

    @Override
    public FtpletResult onDeleteStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(FtpEventName.DELETE_START, session, request);
        this.broadcast(ftpEvent);
        return super.onDeleteStart(session, request);
    }

    @Override
    public FtpletResult onDisconnect(FtpSession session) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(FtpEventName.DISCONNECT, session);
        this.broadcast(ftpEvent);
        return super.onDisconnect(session);
    }

    @Override
    public FtpletResult onConnect(FtpSession session) throws FtpException, IOException {
        final FtpEvent ftpEvent = new FtpEvent(FtpEventName.CONNECT, session);
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
