package org.eftp.ftpserver.business.boot.control;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.enterprise.concurrent.ManagedThreadFactory;
import org.apache.ftpserver.ConnectionConfig;
import org.apache.ftpserver.command.CommandFactory;
import org.apache.ftpserver.ftplet.FileSystemFactory;
import org.apache.ftpserver.ftplet.FtpStatistics;
import org.apache.ftpserver.ftplet.Ftplet;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.ftpletcontainer.FtpletContainer;
import org.apache.ftpserver.impl.DefaultFtpServerContext;
import org.apache.ftpserver.impl.FtpServerContext;
import org.apache.ftpserver.listener.Listener;
import org.apache.ftpserver.message.MessageResource;
import org.eftp.ftpserver.business.users.control.InMemoryUserManager;

/**
 *
 * @author adam-bien.com
 */
@LocalBean
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class ManagedFtpServerContext implements FtpServerContext {

    int corePoolSize = 2;
    int maximumPoolSize = 8;
    long keepAliveTimeInHours = 1;

    BlockingQueue<Runnable> workQueue;

    @Resource
    ManagedThreadFactory threadFactory;

    private DefaultFtpServerContext delegate;
    private ThreadPoolExecutor executor;

    @PostConstruct
    public void init() {
        this.workQueue = new LinkedBlockingQueue<>();
        this.delegate = new DefaultFtpServerContext();
        //Problems with passing ThreadFactory
        this.executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTimeInHours, TimeUnit.HOURS, workQueue);
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
        return delegate.getFileSystemManager();
    }

    @Override
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
}
