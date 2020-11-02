package io.github.bhuwanupadhyay.core;

@FunctionalInterface
public interface CommandService<C> {

	void execute(C command);

}
