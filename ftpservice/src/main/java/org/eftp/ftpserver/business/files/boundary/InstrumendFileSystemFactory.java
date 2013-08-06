package org.eftp.ftpserver.business.files.boundary;

import org.apache.ftpserver.filesystem.nativefs.impl.NativeFileSystemView;
import org.apache.ftpserver.ftplet.FileSystemFactory;
import org.apache.ftpserver.ftplet.FileSystemView;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.User;

/**
 *
 * @author adam-bien.com
 */
public class InstrumendFileSystemFactory implements FileSystemFactory {

    @Override
    public FileSystemView createFileSystemView(User user) throws FtpException {
        return new NativeFileSystemView(user, true);
    }
}
