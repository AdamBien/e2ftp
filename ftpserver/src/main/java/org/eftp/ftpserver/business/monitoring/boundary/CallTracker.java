/*
 *
 */
package org.eftp.ftpserver.business.monitoring.boundary;

import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author adam-bien.com
 */
public class CallTracker {

    private static final Logger LOG = Logger.getLogger(CallTracker.class.getName());

    @AroundInvoke
    public Object log(InvocationContext ic) throws Exception {
        LOG.info(String.valueOf(ic.getMethod()));
        return ic.proceed();
    }
}
