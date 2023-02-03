package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.rubble.white.vertical;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.RubbleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableWhiteVerticalPurpleRubble implements Mappable {

	private MappableWhiteVerticalPurpleRubble() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableWhiteVerticalPurpleRubble();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new RubbleObject(LVL.PURPLE, COLOR.WHITE, false, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 230, 6 };
		return rgb;
	}

}
