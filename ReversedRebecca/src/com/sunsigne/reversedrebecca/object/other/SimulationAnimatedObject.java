package com.sunsigne.reversedrebecca.object.other;

public class SimulationAnimatedObject extends AnimatedDecorationObject {

	public SimulationAnimatedObject(int x, int y, int w, int h, String path, String name) {
		super(x, y, w, h, name, 5, 3, false);
	}

	////////// NAME ////////////

	@Override
	protected String getPath() {
		return "textures/workshop/" + "000" + "/";
	}

	@Override
	public String toString() {
		var clazz = "SIMULATION";
		return clazz + " : " + getName().toUpperCase();
	}

}
