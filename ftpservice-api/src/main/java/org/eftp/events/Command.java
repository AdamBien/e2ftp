/*
 *
 */
package org.eftp.events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

/**
 *
 * @author adam-bien.com
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Command {

    Name value() default Name.EVERYTHING;

    enum Name {

        LOGIN, SITE,
        RENAME_END, RENAME_START,
        UPLOAD_UNIQUE_START, UPLOAD_UNIQUE_END,
        APPEND_START, APPEND_END,
        MKDIR_START, MKDIR_END,
        RMDIR_START, RMDIR_END,
        DOWNLOAD_START, DOWNLOAD_END,
        UPLOAD_START, UPLOAD_END,
        DELETE_END, DELETE_START,
        DISCONNECT, CONNECT,
        EVERYTHING;
    }
}
