/*
 *
 */
package org.eftp.ftpserver.business.concurrency.control;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 *
 * @author adam-bien.com
 */
@Singleton
public class ManagedThreadPoolExecutorExposer {

    @Inject
    private int corePoolSize;
    @Inject
    private int maximumPoolSize;

    @Inject
    private long keepAliveTimeInHours;

    @Inject
    private int incomingRequestQueueDepth;

    private ThreadPoolExecutor executor;

    @PostConstruct
    public void init() {
        LinkedBlockingQueue workQueue = new LinkedBlockingQueue<>(incomingRequestQueueDepth);
        this.executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTimeInHours, TimeUnit.DAYS, workQueue);
    }

    @Produces
    public ThreadPoolExecutor poolExecutor() {
        return this.executor;
    }
}
