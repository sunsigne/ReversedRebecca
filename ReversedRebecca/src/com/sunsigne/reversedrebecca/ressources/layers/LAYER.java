package com.sunsigne.reversedrebecca.ressources.layers;

import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public enum LAYER {
	
	//DOWNER_GROUND("downer_ground", new Handler(true)),
	DOWN_GROUND("down_ground", new Handler(true)),
	DOWN_WORLD_CONTENT("down_world_content", new Handler(true)),	
	/////////////////////////////////////////
	GROUND("ground", new Handler(true)),
	WORLD_CONTENT("world_content", new Handler(true)),
	/////////////////////////////////////////
	UP_GROUND("up_ground", new Handler(true)),
	UP_WORLD_CONTENT("up_world_content", new Handler(true)),
	//UPPER_GROUND("upper_ground", new Handler(true)),
	
	WORLD_TEXT("world_text", new Handler(true)),
	
	GUI("gui", new Handler(false)),
	MENU("menu", new Handler(false)),
	DEBUG ("debug", new Handler(false)),
	LOADING ("loading", new Handler(false));
	
	
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
