package com.sunsigne.reversedrebecca.object.characteristics;

import com.sunsigne.reversedrebecca.ressources.layers.LayerDualizer;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public interface LayerSendable extends Updatable {

	Updatable getReplacementUpdatable();

	default Updatable getUpdatable() {
		return this;
	}

	default void sendToGround() {
		Handler content = getHandler();
		Handler ground = new LayerDualizer().getGroundFromContent(content).getHandler();

		content.removeObject(this);
		content.addObject(getReplacementUpdatable());
		ground.addObject(getUpdatable());
	}

	default void sendToLight() {
		Handler content = getHandler();
		Handler light = new LayerDualizer().getLightFromContent(content).getHandler();

		content.removeObject(this);
		content.addObject(getReplacementUpdatable());
		light.addObject(getUpdatable());
	}

}
