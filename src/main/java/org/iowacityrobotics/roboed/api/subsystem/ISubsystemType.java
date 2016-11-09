package org.iowacityrobotics.roboed.api.subsystem;

import org.iowacityrobotics.roboed.api.subsystem.provider.ISubsystemProvider;

/**
 * Describes a subsystem type.
 * @author Evan Geng
 */
public interface ISubsystemType<I, O, P extends ISubsystemProvider<I, O>> { }
