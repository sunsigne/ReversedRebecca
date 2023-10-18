package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.rubble.brown_sugar.vertical;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.RubbleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableBrownVerticalYellowRubble implements Mappable {

	private MappableBrownVerticalYellowRubble() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableBrownVerticalYellowRubble();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new RubbleObject(LVL.YELLOW, COLOR.BROWN_SUGAR, false, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 210, 3 };
		return rgb;
	}

}
