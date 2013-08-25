/*
 *
 */
package org.eftp.ftpserver.business.monitoring.boundary;

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
