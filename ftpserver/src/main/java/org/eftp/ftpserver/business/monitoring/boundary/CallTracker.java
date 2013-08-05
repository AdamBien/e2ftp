/*
 *
 */
package org.eftp.ftpserver.business.monitoring.boundary;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import org.eftp.ftpserver.business.logger.boundary.Log;

/**
 *
 * @author adam-bien.com
 */
public class CallTracker {

    @Inject
    Log LOG;

    @AroundInvoke
    public Object log(InvocationContext ic) throws Exception {
        LOG.info(String.valueOf(ic.getMethod()));
        return ic.proceed();
    }
}
