package com.sunsigne.reversedrebecca.world;

import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class Layer {
	
	public static final Handler WORLD = new Handler(true);
	
	public static final Handler GUI = new Handler(false);
	
	public static final Handler DEBUG = new Handler(false);
}
