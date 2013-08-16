/*
 *
 */
package org.eftp.ftpserver.business.hooks.control;

import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.eftp.events.Command;
import org.eftp.events.FtpEvent;
import org.eftp.ftpserver.business.hooks.boundary.HooksRegistry;
import org.eftp.ftpserver.business.hooks.entity.Hook;

/**
 *
 * @author adam-bien.com
 */
@Stateless
public class FtpEventListener {

    @Inject
    HookInvoker hookInvoker;

    @Inject
    HooksRegistry registry;

    public void onFtpEventArrival(@Observes @Command FtpEvent event) {
        List<Hook> hooks = registry.findByCommand(event.getCommand());
        for (Hook hook : hooks) {
            this.hookInvoker.callback(hook, event);
        }
    }
}
