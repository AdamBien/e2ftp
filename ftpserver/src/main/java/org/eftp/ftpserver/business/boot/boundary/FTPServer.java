package org.eftp.ftpserver.business.boot.boundary;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.apache.ftpserver.ConnectionConfigFactory;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authentication;
import org.apache.ftpserver.ftplet.AuthenticationFailedException;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.ConcurrentLoginPermission;
import org.apache.ftpserver.usermanager.impl.WritePermission;

/**
 *
 * @author adam-bien.com
 */
@Startup
@Singleton
public class FTPServer {

    private FtpServer ftpServer;
    private final int SERVER_PORT = 8888;
    private int MAX_LOGINS = 100;
    private int IDLE_TIME = 30 * 1000;

    @PostConstruct
    public void init() {
        ConnectionConfigFactory ccf = new ConnectionConfigFactory();
        ccf.setMaxLogins(MAX_LOGINS);
        ccf.setAnonymousLoginEnabled(true);
        ccf.setMaxThreads(2);
        ccf.setMaxAnonymousLogins(MAX_LOGINS);
        FtpServerFactory fsf = new FtpServerFactory();
        fsf.setConnectionConfig(ccf.createConnectionConfig());
        fsf.setUserManager(new UserManager() {

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
        });
        ListenerFactory factory = new ListenerFactory();
        factory.setPort(SERVER_PORT);
        fsf.addListener("default", factory.createListener());
        this.ftpServer = fsf.createServer();
        try {
            this.ftpServer.start();
        } catch (FtpException ex) {
            Logger.getLogger(FTPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void shutdown() {
        this.ftpServer.stop();
    }

    public BaseUser getUser() {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new WritePermission());

        authorities.add(new ConcurrentLoginPermission(MAX_LOGINS, MAX_LOGINS));

        BaseUser user = new BaseUser();
        user.setAuthorities(authorities);
        user.setName("duke");
        user.setPassword("duke");
        user.setHomeDirectory("/tmp");
        user.setMaxIdleTime(IDLE_TIME);
        return user;
    }

}
