package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.rubble.dev;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.DevDifficulty.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.dev.DevRubbleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableEasiestRubble implements Mappable {

	private MappableEasiestRubble() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableEasiestRubble();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new DevRubbleObject(DEV_LVL.EASIEST, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 255, 7 };
		return rgb;
	}

}
