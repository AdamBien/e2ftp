/*
 *
 */
package org.eftp.ftpserver.business.files.boundary;

import java.io.File;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.eftp.ftpserver.business.users.boundary.JPAUserStore;
import org.eftp.ftpserver.business.users.entity.FtpUser;

/**
 *
 * @author adam-bien.com
 */
@Stateless
public class Files {

    @Inject
    JPAUserStore userStore;

    public File getFile(String userName, String fileName) {
        FtpUser user = this.userStore.find(userName);
        if (user == null) {
            return null;
        }
        String homeDir = user.getHomeDir();
        return new File(homeDir, fileName);
    }

}
