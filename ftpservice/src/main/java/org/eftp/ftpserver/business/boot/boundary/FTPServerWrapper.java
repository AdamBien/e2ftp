package org.eftp.ftpserver.business.boot.boundary;

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
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.apache.ftpserver.ConnectionConfigFactory;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.impl.DefaultFtpServer;
import org.apache.ftpserver.listener.ListenerFactory;
import org.eftp.ftpserver.business.boot.control.ManagedFtpServerContext;
import org.eftp.ftpserver.business.users.control.UserManagerIntegrationAdapter;

/**
 *
 * @author adam-bien.com
 */
@Startup
@Singleton
@DependsOn("ConfigurationStartup")
public class FTPServerWrapper {

    protected FtpServer ftpServer;
    @Inject
    protected int serverPort;
    @Inject
    protected int maxLogins;
    @Inject
    private boolean anonymousLoginEnabled;

    @Inject
    protected boolean startServerOnStartup;
    @Inject
    UserManagerIntegrationAdapter userManager;

    @Inject
    ManagedFtpServerContext managedContext;

    @PostConstruct
    public void init() {
        if (startServerOnStartup) {
            this.start();
        }
    }

    @PreDestroy
    public void shutdown() {
        this.stop();
    }

    public void start() {
        if (this.ftpServer == null || this.ftpServer.isStopped()) {
            ConnectionConfigFactory ccf = new ConnectionConfigFactory();
            ccf.setMaxLogins(maxLogins);
            ccf.setAnonymousLoginEnabled(true);
            ccf.setMaxThreads(2);
            ccf.setMaxAnonymousLogins(maxLogins);
            this.managedContext.setConnectionConfig(ccf.createConnectionConfig());
            this.managedContext.setUserManager(userManager);
            ListenerFactory factory = new ListenerFactory();
            factory.setPort(serverPort);
            this.managedContext.addListener("default", factory.createListener());
            this.ftpServer = new DefaultFtpServer(this.managedContext);
            try {
                this.ftpServer.start();
            } catch (FtpException ex) {
                throw new IllegalStateException("Cannot start server: ", ex);
            }
        }
    }

    public void stop() {
        if (this.isRunning()) {
            this.ftpServer.stop();
        }
    }

    public void restart() {
        this.stop();
        this.start();
    }

    public boolean isRunning() {
        return !this.ftpServer.isStopped();
    }
}
