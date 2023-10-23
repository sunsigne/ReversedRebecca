package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.animation;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.puzzler.animation.HackingAnimationObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableHackingAnimation implements Mappable {

	private MappableHackingAnimation() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableHackingAnimation();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new HackingAnimationObject(x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 3, 128, 1 };
		return rgb;
	}

}
