package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.animation;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.puzzler.animation.ExplodeRubbleAnimationObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableExplodeRubbleAnimation implements Mappable {

	private MappableExplodeRubbleAnimation() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableExplodeRubbleAnimation();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ExplodeRubbleAnimationObject(x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 128, 1 };
		return rgb;
	}

}
