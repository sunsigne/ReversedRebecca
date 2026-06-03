package com.sunsigne.reversedrebecca.world.mapcreator.mappable.animation;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.animation.ZapAnimationObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableZapAnimationCritical implements Mappable {

	private MappableZapAnimationCritical() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableZapAnimationCritical();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ZapAnimationObject(x, y, true);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 3, 129, 1 };
		return rgb;
	}

}
