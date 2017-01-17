package org.iowacityrobotics.roboed.api.subsystem.provider;

import java.lang.annotation.*;

/**
 * Marks a method in a subsystem provider as the method that provides subsystems.
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Providing { }
