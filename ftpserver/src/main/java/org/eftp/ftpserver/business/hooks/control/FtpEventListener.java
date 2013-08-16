/*
 *
 */
package org.eftp.ftpserver.business.hooks.control;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.eftp.events.Command;
import org.eftp.events.FtpEvent;
import org.eftp.events.FtpEventName;
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
        List<Hook> allListeners = new ArrayList<>();
        List<Hook> everythingListeners = registry.findByCommand(FtpEventName.EVERYTHING);
        List<Hook> hooks = registry.findByCommand(event.getCommand());
        allListeners.addAll(everythingListeners);
        allListeners.addAll(hooks);
        for (Hook hook : allListeners) {
            this.hookInvoker.callback(hook, event);
        }
    }
}
