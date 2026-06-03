package com.sunsigne.reversedrebecca.world.mapcreator.mappable.animation;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.animation.ExplosionAnimationObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableExplosionAnimationCritical implements Mappable {

	private MappableExplosionAnimationCritical() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableExplosionAnimationCritical();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ExplosionAnimationObject(x, y, true);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 129, 1 };
		return rgb;
	}

}
