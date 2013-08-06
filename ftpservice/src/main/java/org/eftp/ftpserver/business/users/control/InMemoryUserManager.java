package org.eftp.ftpserver.business.users.control;

import java.util.ArrayList;
import java.util.List;
import org.apache.ftpserver.ftplet.Authentication;
import org.apache.ftpserver.ftplet.AuthenticationFailedException;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.ConcurrentLoginPermission;
import org.apache.ftpserver.usermanager.impl.WritePermission;
import org.eftp.ftpserver.business.boot.boundary.FTPServerWrapper;

/**
 *
 * @author adam-bien.com
 */
public class InMemoryUserManager implements UserManager {

    private static final String JAVA_IO_TEMP_DIR = System.getProperty("java.io.tmpdir");

    @Override
    public User getUserByName(String string) throws FtpException {
        return getUser();

    }

    @Override
    public String[] getAllUserNames() throws FtpException {
        return new String[]{"duke"};
    }

    @Override
    public void delete(String string) throws FtpException {
    }

    @Override
    public void save(User user) throws FtpException {
    }

    @Override
    public boolean doesExist(String string) throws FtpException {
        return true;
    }

    @Override
    public User authenticate(Authentication a) throws AuthenticationFailedException {
        return getUser();
    }

    @Override
    public String getAdminName() throws FtpException {
        return getUser().getName();
    }

    @Override
    public boolean isAdmin(String string) throws FtpException {
        return true;
    }

    public BaseUser getUser() {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new WritePermission());

        authorities.add(new ConcurrentLoginPermission(FTPServerWrapper.MAX_LOGINS, FTPServerWrapper.MAX_LOGINS));

        BaseUser user = new BaseUser();
        user.setAuthorities(authorities);
        user.setName("duke");
        user.setPassword("duke");
        user.setHomeDirectory(JAVA_IO_TEMP_DIR);
        user.setMaxIdleTime(FTPServerWrapper.IDLE_TIME);
        return user;
    }

}
