/*
 *
 */
package org.eftp.ftpserver.business;

import javax.ejb.TransactionRolledbackLocalException;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author adam-bien.com
 */
@Provider
public class ConstraintViolationMapper implements ExceptionMapper<TransactionRolledbackLocalException> {

    @Override
    public Response toResponse(TransactionRolledbackLocalException exception) {
        if (exception.getCausedByException() instanceof ConstraintViolationException) {
            return Response.status(Status.BAD_REQUEST).header("x-error-detail", exception.getMessage()).build();
        } else {
            return Response.status(Status.INTERNAL_SERVER_ERROR).header("x-error-detail", exception.getMessage()).build();
        }
    }
}
