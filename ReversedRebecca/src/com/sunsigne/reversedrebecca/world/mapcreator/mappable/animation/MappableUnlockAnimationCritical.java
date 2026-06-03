package com.sunsigne.reversedrebecca.world.mapcreator.mappable.animation;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.animation.UnlockAnimationObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableUnlockAnimationCritical implements Mappable {

	private MappableUnlockAnimationCritical() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableUnlockAnimationCritical();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new UnlockAnimationObject(x, y, true);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 1, 129, 1 };
		return rgb;
	}

}
