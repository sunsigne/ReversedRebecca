package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.animation;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.puzzler.animation.LockAnimationObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableLockAnimation implements Mappable {

	private MappableLockAnimation() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableLockAnimation();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new LockAnimationObject(x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 1, 128, 2 };
		return rgb;
	}

}
