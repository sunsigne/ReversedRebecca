package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.rubble.dev;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.RubbleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableBlueVerticalHarderRubble implements Mappable {

	private MappableBlueVerticalHarderRubble() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableBlueVerticalHarderRubble();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new RubbleObject(DEV_LVL.HARDER, false, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 250, 9 };
		return rgb;
	}

}
