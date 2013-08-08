package org.eftp.ftpserver.business.boot.boundary;

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

    private FtpServer ftpServer;

    @Inject
    private int SERVER_PORT;
    @Inject
    private int MAX_LOGINS;

    @Inject
    UserManagerIntegrationAdapter userManager;

    @Inject
    ManagedFtpServerContext managedContext;

    @PostConstruct
    public void init() {
        ConnectionConfigFactory ccf = new ConnectionConfigFactory();
        ccf.setMaxLogins(MAX_LOGINS);
        ccf.setAnonymousLoginEnabled(true);
        ccf.setMaxThreads(2);
        ccf.setMaxAnonymousLogins(MAX_LOGINS);
        this.managedContext.setConnectionConfig(ccf.createConnectionConfig());
        this.managedContext.setUserManager(userManager);
        ListenerFactory factory = new ListenerFactory();
        factory.setPort(SERVER_PORT);
        this.managedContext.addListener("default", factory.createListener());
        this.ftpServer = new DefaultFtpServer(this.managedContext);
        try {
            this.ftpServer.start();
        } catch (FtpException ex) {
            throw new IllegalStateException("Cannot start server: ", ex);
        }
    }

    @PreDestroy
    public void shutdown() {
        this.ftpServer.stop();
    }

}
