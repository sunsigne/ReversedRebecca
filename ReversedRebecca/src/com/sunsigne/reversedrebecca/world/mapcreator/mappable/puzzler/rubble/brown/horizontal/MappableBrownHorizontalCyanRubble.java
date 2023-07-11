package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.rubble.brown.horizontal;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.RubbleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableBrownHorizontalCyanRubble implements Mappable {

	private MappableBrownHorizontalCyanRubble() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableBrownHorizontalCyanRubble();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new RubbleObject(LVL.CYAN, COLOR.BROWN, true, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 225, 1 };
		return rgb;
	}
	
}
