package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.rubble.white.horizontal.dev;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.RubbleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableWhiteHorizontalEasiestRubble implements Mappable {

	private MappableWhiteHorizontalEasiestRubble() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableWhiteHorizontalEasiestRubble();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new RubbleObject(DEV_LVL.EASIEST, COLOR.WHITE, true, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 235, 7 };
		return rgb;
	}

}
