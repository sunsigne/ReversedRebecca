package com.sunsigne.reversedrebecca.object.characteristics;

import com.sunsigne.reversedrebecca.ressources.layers.LayerDualizer;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public interface GroundLayerSendable extends Updatable {

	Updatable getReplacementUpdatable();

	default Updatable getGroundUpdatable() {
		return this;
	}

	default void sendToGround() {
		Handler content = getHandler();
		Handler ground = new LayerDualizer().getGroundFromContent(content).getHandler();

		content.removeObject(this);
		content.addObject(getReplacementUpdatable());
		ground.addObject(getGroundUpdatable());
	}

}
