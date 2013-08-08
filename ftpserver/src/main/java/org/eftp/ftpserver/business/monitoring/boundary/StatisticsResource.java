package org.eftp.ftpserver.business.monitoring.boundary;

import java.util.concurrent.ThreadPoolExecutor;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.apache.ftpserver.ftplet.FtpStatistics;

/**
 *
 * @author adam-bien.com
 */
@Stateless
@Path("statistics")
public class StatisticsResource {

    @Inject
    FtpStatistics statistics;

    @Inject
    ThreadPoolExecutor executor;

    private static final String EXECUTOR_PREFIX = "ExecutorService.";

    @GET
    public JsonObject all() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("currentAnonymousLoginNumber", this.statistics.getCurrentAnonymousLoginNumber());
        builder.add("currentConnectionNumber", this.statistics.getCurrentConnectionNumber());
        builder.add("currentLoginNumber", this.statistics.getCurrentLoginNumber());
        builder.add("totalAnonymousLoginNumber", this.statistics.getTotalAnonymousLoginNumber());
        builder.add("totalConnectionNumber", this.statistics.getTotalConnectionNumber());
        builder.add("totalDeleteNumber", this.statistics.getTotalDeleteNumber());
        builder.add("totalDirectoryCreated", this.statistics.getTotalDirectoryCreated());
        builder.add("totalDirectoryRemoved", this.statistics.getTotalDirectoryRemoved());
        builder.add("totalDownloadNumber", this.statistics.getTotalDownloadNumber());
        builder.add("totalDownloadSize", this.statistics.getTotalDownloadSize());
        builder.add("totalFailedLoginNumber", this.statistics.getTotalFailedLoginNumber());
        builder.add("totalLoginNumber", this.statistics.getTotalLoginNumber());
        builder.add("totalUploadNumber", this.statistics.getTotalUploadNumber());
        builder.add("totalUploadSize", this.statistics.getTotalUploadSize());
        builder.add("startTime", this.statistics.getStartTime().toString());
        builder.add(EXECUTOR_PREFIX + "activeCount", executor.getActiveCount());
        builder.add(EXECUTOR_PREFIX + "completedTaskCount", executor.getCompletedTaskCount());
        builder.add(EXECUTOR_PREFIX + "corePoolSize", executor.getCorePoolSize());
        builder.add(EXECUTOR_PREFIX + "largestPoolSize", executor.getLargestPoolSize());
        builder.add(EXECUTOR_PREFIX + "maximumPoolSize", executor.getMaximumPoolSize());
        builder.add(EXECUTOR_PREFIX + "poolSize", executor.getPoolSize());
        builder.add(EXECUTOR_PREFIX + "taskCount", executor.getTaskCount());
        builder.add(EXECUTOR_PREFIX + "taskQueueSize", executor.getQueue().size());
        return builder.build();
    }
}
