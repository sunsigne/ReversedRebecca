package com.sunsigne.reversedrebecca.ressources;

import com.sunsigne.reversedrebecca.system.main.HandlerTick;

public interface IRessources {

	public default void startRessources() {
		HandlerRessources.getInstance().addObject(this);
	}

	void loadMinimalRessources();

	void loadRessources();
}
