package com.sunsigne.reversedrebecca.world.mapcreator.mappable.loot.tools.bomb;

import com.sunsigne.reversedrebecca.characteristics.tools.BombToolPlayer;
import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.loot.ToolObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableYellowBomb implements Mappable {

	private MappableYellowBomb() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableYellowBomb();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ToolObject(new BombToolPlayer(), LVL.YELLOW, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 3, 255 };
		return rgb;
	}

}
