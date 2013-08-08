package org.eftp.ftpserver.business.users.control;

import org.eftp.ftpserver.business.users.boundary.JPAUserStore;
import java.util.List;
import javax.inject.Inject;
import org.apache.ftpserver.ftplet.Authentication;
import org.apache.ftpserver.ftplet.AuthenticationFailedException;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.usermanager.AnonymousAuthentication;
import org.apache.ftpserver.usermanager.UsernamePasswordAuthentication;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.eftp.ftpserver.business.users.entity.FtpUser;

/**
 *
 * @author adam-bien.com
 */
public class UserManagerIntegrationAdapter implements UserManager {

    @Inject
    JPAUserStore userStore;

    @Override
    public User getUserByName(String string) throws FtpException {
        return userStore.find(string).getBaseUser();

    }

    @Override
    public String[] getAllUserNames() throws FtpException {
        List<FtpUser> allUsers = this.userStore.allUsers();
        String userNames[] = new String[allUsers.size()];
        int counter = 0;
        for (FtpUser ftpUser : allUsers) {
            userNames[counter++] = ftpUser.getUserName();
        }
        return userNames;
    }

    @Override
    public void delete(String userName) throws FtpException {
        this.userStore.remove(userName);
    }

    @Override
    public void save(User user) throws FtpException {
        throw new UnsupportedOperationException("User management only supported through JPA store");
    }

    @Override
    public boolean doesExist(String userName) throws FtpException {
        FtpUser found = this.userStore.find(userName);
        return found != null;
    }

    @Override
    public User authenticate(Authentication a) throws AuthenticationFailedException {
        if (a instanceof UsernamePasswordAuthentication) {
            UsernamePasswordAuthentication upa = (UsernamePasswordAuthentication) a;
            FtpUser user = this.userStore.find(upa.getUsername(), upa.getPassword());
            return user.getBaseUser();
        } else if (a instanceof AnonymousAuthentication) {
            FtpUser found = this.userStore.find("anonymous");
            if (found != null) {
                return found.getBaseUser();
            }

        }
        return null;
    }

    @Override
    public String getAdminName() throws FtpException {
        FtpUser adminUser = this.userStore.findAdmin();
        if (adminUser != null) {
            return adminUser.getUserName();
        }
        return adminUser.getUserName();
    }

    @Override
    public boolean isAdmin(String userName) throws FtpException {
        return this.userStore.isAdmin(userName);
    }

    public BaseUser getUser() {
        return this.userStore.getDefaultUser("duke", "duke").getBaseUser();
    }
}
