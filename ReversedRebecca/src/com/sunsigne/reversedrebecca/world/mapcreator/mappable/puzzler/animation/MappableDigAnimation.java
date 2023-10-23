package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.animation;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.puzzler.animation.DigAnimationObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableDigAnimation implements Mappable {

	private MappableDigAnimation() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableDigAnimation();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new DigAnimationObject(x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 4, 128, 1 };
		return rgb;
	}

}
