/*
 *
 */
package org.eftp.ftpserver.business.users.entity;

import javax.persistence.Entity;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.usermanager.impl.TransferRatePermission;

/**
 *
 * @author adam-bien.com
 */
@Entity
public class FtpTransferRatePermision extends FtpPermission {

    private int maxDownloadRate;
    private int maxUploadRate;

    public FtpTransferRatePermision(int maxDownloadRate, int maxUploadRate) {
        this.maxDownloadRate = maxDownloadRate;
        this.maxUploadRate = maxUploadRate;
    }

    public FtpTransferRatePermision() {
    }

    public int getMaxDownloadRate() {
        return maxDownloadRate;
    }

    public void setMaxDownloadRate(int maxDownloadRate) {
        this.maxDownloadRate = maxDownloadRate;
    }

    public int getMaxUploadRate() {
        return maxUploadRate;
    }

    public void setMaxUploadRate(int maxUploadRate) {
        this.maxUploadRate = maxUploadRate;
    }

    @Override
    public Authority getAuthority() {
        return new TransferRatePermission(maxDownloadRate, maxUploadRate);
    }

}
