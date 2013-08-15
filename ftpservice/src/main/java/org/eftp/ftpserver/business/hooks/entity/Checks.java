/*
 *
 */
package org.eftp.ftpserver.business.hooks.entity;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author adam-bien.com
 */
@Documented
@Constraint(validatedBy = HookValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Checks {

    String message() default "{org.eftp.ftpserver.business.monitoring.boundary.Checks}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
