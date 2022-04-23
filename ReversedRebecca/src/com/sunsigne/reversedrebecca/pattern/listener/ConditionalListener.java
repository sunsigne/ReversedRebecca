package com.sunsigne.reversedrebecca.pattern.listener;

public interface ConditionalListener extends GenericListener {

	GenericListener getAction();

	boolean canDoAction();

	default void doAction() {
		getAction().doAction();
	}
}
