package org.eftp.ftpserver.business.boot.control;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.enterprise.concurrent.ManagedThreadFactory;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import org.apache.ftpserver.ConnectionConfig;
import org.apache.ftpserver.command.CommandFactory;
import org.apache.ftpserver.ftplet.FileSystemFactory;
import org.apache.ftpserver.ftplet.FtpStatistics;
import org.apache.ftpserver.ftplet.Ftplet;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.ftpletcontainer.FtpletContainer;
import org.apache.ftpserver.ftpletcontainer.impl.DefaultFtpletContainer;
import org.apache.ftpserver.impl.DefaultFtpServerContext;
import org.apache.ftpserver.impl.FtpServerContext;
import org.apache.ftpserver.listener.Listener;
import org.apache.ftpserver.message.MessageResource;
import org.eftp.ftpserver.business.files.boundary.InstrumendFileSystemFactory;
import org.eftp.ftpserver.business.logger.boundary.Log;
import org.eftp.ftpserver.business.monitoring.boundary.CallTracker;
import org.eftp.ftpserver.business.plugins.boundary.Plugin;
import org.eftp.ftpserver.business.users.control.InMemoryUserManager;

/**
 *
 * @author adam-bien.com
 */
@LocalBean
@Singleton
@Interceptors(CallTracker.class)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class ManagedFtpServerContext implements FtpServerContext {

    BlockingQueue<Runnable> workQueue;

    @Resource
    ManagedThreadFactory threadFactory;

    @Inject
    InstrumendFileSystemFactory fileSystemFactory;

    private DefaultFtpServerContext delegate;

    @Inject
    private ThreadPoolExecutor executor;

    private DefaultFtpletContainer ftpletContainer;

    @Inject
    @Plugin
    private Instance<Ftplet> hooks;

    @Inject
    Log LOG;

    @PostConstruct
    public void init() {
        this.delegate = new DefaultFtpServerContext();
        this.delegate.setFileSystemManager(fileSystemFactory);
        this.ftpletContainer = (DefaultFtpletContainer) this.delegate.getFtpletContainer();
        this.installPlugins();
    }

    @Override
    public ConnectionConfig getConnectionConfig() {
        return delegate.getConnectionConfig();
    }

    @Override
    public MessageResource getMessageResource() {
        return delegate.getMessageResource();
    }

    @Override
    public FtpletContainer getFtpletContainer() {
        return delegate.getFtpletContainer();
    }

    @Override
    public Listener getListener(String string) {
        return delegate.getListener(string);
    }

    @Override
    public Map<String, Listener> getListeners() {
        return delegate.getListeners();
    }

    @Override
    public CommandFactory getCommandFactory() {
        return delegate.getCommandFactory();
    }

    @Override
    public void dispose() {
        delegate.dispose();
    }

    @Override
    public UserManager getUserManager() {
        return delegate.getUserManager();
    }

    @Override
    public FileSystemFactory getFileSystemManager() {
        return this.fileSystemFactory;
    }

    @Override
    @Produces
    public FtpStatistics getFtpStatistics() {
        return delegate.getFtpStatistics();
    }

    @Override
    public Ftplet getFtplet(String string) {
        return delegate.getFtplet(string);
    }

    @Override
    public ThreadPoolExecutor getThreadPoolExecutor() {
        return this.executor;
    }

    public void setConnectionConfig(ConnectionConfig createConnectionConfig) {
        this.delegate.setConnectionConfig(createConnectionConfig);
    }

    public void setUserManager(InMemoryUserManager userManager) {
        this.delegate.setUserManager(userManager);
    }

    public void addListener(String adefault, Listener createListener) {
        this.delegate.addListener(adefault, createListener);
    }

    void installPlugins() {
        for (Ftplet ftplet : hooks) {
            LOG.info("Installing: " + ftplet);
            this.ftpletContainer.getFtplets().put(ftplet.getClass().getName(), ftplet);
        }
    }
}
