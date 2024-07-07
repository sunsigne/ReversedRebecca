package com.sunsigne.reversedrebecca.world.mapcreator.mappable.animation;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.animation.ExplosionAnimationObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableExplosionAnimation implements Mappable {

	private MappableExplosionAnimation() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableExplosionAnimation();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ExplosionAnimationObject(x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 128, 1 };
		return rgb;
	}

}
