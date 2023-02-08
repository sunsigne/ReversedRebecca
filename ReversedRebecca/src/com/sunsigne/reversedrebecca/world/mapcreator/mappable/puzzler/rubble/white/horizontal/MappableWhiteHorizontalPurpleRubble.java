package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.rubble.white.horizontal;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.RubbleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableWhiteHorizontalPurpleRubble implements Mappable {

	private MappableWhiteHorizontalPurpleRubble() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableWhiteHorizontalPurpleRubble();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new RubbleObject(LVL.PURPLE, COLOR.WHITE, true, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 235, 6 };
		return rgb;
	}
	
}