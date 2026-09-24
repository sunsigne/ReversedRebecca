package com.sunsigne.reversedrebecca.ressources.menu;

public interface UnlockableTask {

	void create(String name);
	
	void unlock(String name);
	
	void reset();
	
}
