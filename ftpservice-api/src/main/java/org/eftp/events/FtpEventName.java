/*
 *
 */
package org.eftp.events;

/*
 * #%L
 * ftpservice-api
 * %%
 * Copyright (C) 2013 e2ftp
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

/**
 *
 * @author adam-bien.com
 */
public enum FtpEventName {

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
