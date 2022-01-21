package com.sunsigne.reversedrebecca.world;

import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public enum LAYER {
	
	GROUND("ground", new Handler(true)),
	WORLD("world", new Handler(true)),
	
	GUI("gui", new Handler(false)),
	DEBUG ("debug", new Handler(false));
	
	
	private String name;
	private Handler handler;
	
	LAYER(String name, Handler handler) {
		this.name = name;
		this.handler = handler;
	}
	
	public String getName() {
		return name;
	}
	
	public Handler getHandler() {
		return handler;
	}
	
	public void addObject(Updatable object) {
		handler.addObject(object);
	}
}
