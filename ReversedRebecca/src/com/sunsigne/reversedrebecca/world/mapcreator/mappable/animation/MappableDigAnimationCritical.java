package com.sunsigne.reversedrebecca.world.mapcreator.mappable.animation;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.animation.DigAnimationObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableDigAnimationCritical implements Mappable {

	private MappableDigAnimationCritical() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableDigAnimationCritical();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new DigAnimationObject(x, y, true);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 4, 129, 1 };
		return rgb;
	}

}
