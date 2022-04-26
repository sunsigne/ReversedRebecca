package com.sunsigne.reversedrebecca.world.mapcreator.mappable.loot.tools.bomb;

import com.sunsigne.reversedrebecca.characteristics.tools.BombToolPlayer;
import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.loot.ToolObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableOrangeBomb implements Mappable {

	private MappableOrangeBomb() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableOrangeBomb();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ToolObject(new BombToolPlayer(), LVL.ORANGE, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 4, 255 };
		return rgb;
	}

}
