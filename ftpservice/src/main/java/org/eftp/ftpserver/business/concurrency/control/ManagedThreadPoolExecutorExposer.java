/*
 *
 */
package org.eftp.ftpserver.business.concurrency.control;

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
