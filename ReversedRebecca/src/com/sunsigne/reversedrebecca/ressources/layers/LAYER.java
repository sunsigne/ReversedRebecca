package com.sunsigne.reversedrebecca.ressources.layers;

import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public enum LAYER {
	
	//DOWNEST_GROUND("downiest_ground", new Handler(true)),
	//DOWNER_GROUND("downer_ground", new Handler(true)),
	/////////////////////////////////////////
	DOWN_GROUND("down_ground", new Handler(true)),
	DOWN_WORLD_CONTENT("down_world_content", new Handler(true)),
	DOWN_LIGHT("down_light", new Handler(true)),
	/////////////////////////////////////////
	GROUND("ground", new Handler(true)),
	WORLD_CONTENT("world_content", new Handler(true)),
	LIGHT("light", new Handler(true)),
	/////////////////////////////////////////
	UP_GROUND("up_ground", new Handler(true)),
	UP_WORLD_CONTENT("up_world_content", new Handler(true)),
	UP_LIGHT("up_light", new Handler(true)),
	/////////////////////////////////////////
	//UPPER_GROUND("upper_ground", new Handler(true)),
	//UPPEST_GROUND("uppiest_ground", new Handler(true)),
	
	WORLD_TEXT("world_text", new Handler(true)),	
	GUI("gui", new Handler(false)),
	
	PUZZLE("puzzle", new Handler(false)),
	
	MENU("menu", new Handler(false)),
	DEBUG ("debug", new Handler(false)),
	LOADING ("loading", new Handler(false));
	
	public boolean isMapLayer() {
		return getName().contains("ground") | getName().contains("content") | getName().contains("light");
	}
	
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
