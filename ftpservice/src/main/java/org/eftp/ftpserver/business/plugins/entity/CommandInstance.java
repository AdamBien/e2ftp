/*
 *
 */
package org.eftp.ftpserver.business.plugins.entity;

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

import javax.enterprise.util.AnnotationLiteral;
import org.eftp.events.Command;
import org.eftp.events.FtpEventName;

/**
 *
 * @author adam-bien.com
 */
public class CommandInstance extends AnnotationLiteral<Command> implements Command {

    private FtpEventName name;

    public CommandInstance(FtpEventName name) {
        this.name = name;
    }

    @Override
    public FtpEventName value() {
        return this.name;
    }
}
