/*
 *
 */
package org.eftp.ftpserver.business.users.control;

/*
 * #%L
 * ftpservice
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

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author adam-bien.com
 */
public class Digester {

    private static final String CHARSET = "UTF-8";
    private static final String ALGORITHM = "SHA-256";

    public static String computeHash(String password) {
        MessageDigest messageDigest = null;
        final byte[] bytes;
        try {
            messageDigest = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("Cannot find digest algorithm: " + ALGORITHM, ex);
        }
        try {
            bytes = password.getBytes(CHARSET);
        } catch (UnsupportedEncodingException ex) {
            throw new IllegalStateException("Charset: " + CHARSET + " is not supported", ex);
        }
        messageDigest.update(bytes);
        byte[] digest = messageDigest.digest();
        BigInteger bigInteger = new BigInteger(1, digest);
        return String.format("%x", bigInteger);
    }

}
