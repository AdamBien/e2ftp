/*
 *
 */
package org.eftp.ftpserver.business.hooks.entity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author adam-bien.com
 */
public class HookValidator implements ConstraintValidator<Checks, Hook> {

    private Checks annotation;

    @Override
    public void initialize(Checks annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(Hook hook, ConstraintValidatorContext context) {
        return hook.isValid();
    }
}
