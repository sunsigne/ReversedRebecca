package com.sunsigne.reversedrebecca.ressources.layers;

import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public enum LAYER {
	
	DOWN_GROUND("down_ground", new Handler(true)),
	DOWN_WORLD_CONTENT("down_world_content", new Handler(true)),	
	/////////////////////////////////////////
	GROUND("ground", new Handler(true)),
	WORLD_CONTENT("world_content", new Handler(true)),
	/////////////////////////////////////////
	UP_GROUND("up_ground", new Handler(true)),
	UP_WORLD_CONTENT("up_world_content", new Handler(true)),
		
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
