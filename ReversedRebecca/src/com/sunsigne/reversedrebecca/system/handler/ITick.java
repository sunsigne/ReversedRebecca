package com.sunsigne.reversedrebecca.system.handler;

public interface ITick {

	/**
	 * WARNING ! To actually call this method, the object whose the class implements
	 * ITick must, somehow, be add to handler_tick_list in HandlerTick class
	 */ 
	void tick();

}
