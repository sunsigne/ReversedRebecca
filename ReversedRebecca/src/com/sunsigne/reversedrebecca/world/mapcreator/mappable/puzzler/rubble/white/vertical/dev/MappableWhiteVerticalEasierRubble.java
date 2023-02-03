package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.rubble.white.vertical.dev;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.RubbleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableWhiteVerticalEasierRubble implements Mappable {

	private MappableWhiteVerticalEasierRubble() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableWhiteVerticalEasierRubble();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new RubbleObject(DEV_LVL.EASIER, COLOR.WHITE, false, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 230, 8 };
		return rgb;
	}

}
