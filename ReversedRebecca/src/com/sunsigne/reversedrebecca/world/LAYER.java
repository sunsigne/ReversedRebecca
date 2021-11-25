package com.sunsigne.reversedrebecca.world;

import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public enum LAYER {
	
	WORLD(new Handler(true)),
	GUI(new Handler(false)),
	DEBUG (new Handler(false));
	
	private Handler handler;
	
	LAYER(Handler handler) {
		this.handler = handler;
	}
	
	public Handler getHandler() {
		return handler;
	}
	
	public void addObject(Updatable object) {
		handler.addObject(object);
	}
}
