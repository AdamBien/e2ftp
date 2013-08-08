/*
 *
 */
package org.eftp.ftpserver.business.users.boundary;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.eftp.ftpserver.business.logger.boundary.Log;
import org.eftp.ftpserver.business.users.control.JPAUserStore;

/**
 *
 * @author adam-bien.com
 */
@Startup
@Singleton
public class UserManagementInitializer {

    @Inject
    JPAUserStore userStore;

    @Inject
    Log LOG;

    @PostConstruct
    public void setupDefaults() {
        LOG.info("Establishing default settings for users");
        userStore.createDefaultUser();
    }
}
