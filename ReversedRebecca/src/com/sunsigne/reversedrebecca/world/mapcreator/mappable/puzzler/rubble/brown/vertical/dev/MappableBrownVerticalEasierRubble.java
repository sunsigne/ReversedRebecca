package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.rubble.brown.vertical.dev;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.RubbleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableBrownVerticalEasierRubble implements Mappable {

	private MappableBrownVerticalEasierRubble() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableBrownVerticalEasierRubble();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new RubbleObject(DEV_LVL.EASIER, COLOR.BROWN, false, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 220, 8 };
		return rgb;
	}

}
