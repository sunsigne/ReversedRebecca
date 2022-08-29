package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.rubble.green.vertical;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.RubbleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableGreenVerticalCyanRubble implements Mappable {

	private MappableGreenVerticalCyanRubble() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableGreenVerticalCyanRubble();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new RubbleObject(LVL.CYAN, COLOR.GREEN, false, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 240, 1 };
		return rgb;
	}

}
