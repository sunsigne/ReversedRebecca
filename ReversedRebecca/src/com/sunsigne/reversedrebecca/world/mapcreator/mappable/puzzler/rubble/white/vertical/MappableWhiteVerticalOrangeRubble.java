package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.rubble.white.vertical;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.RubbleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableWhiteVerticalOrangeRubble implements Mappable {

	private MappableWhiteVerticalOrangeRubble() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableWhiteVerticalOrangeRubble();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new RubbleObject(LVL.ORANGE, COLOR.WHITE, false, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 230, 4 };
		return rgb;
	}

}
