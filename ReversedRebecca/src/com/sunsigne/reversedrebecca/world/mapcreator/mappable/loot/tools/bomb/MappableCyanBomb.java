package com.sunsigne.reversedrebecca.world.mapcreator.mappable.loot.tools.bomb;

import com.sunsigne.reversedrebecca.characteristics.tools.BombToolPlayer;
import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.loot.ToolObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableCyanBomb implements Mappable {

	private MappableCyanBomb() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableCyanBomb();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ToolObject(new BombToolPlayer(), LVL.CYAN, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 1, 255 };
		return rgb;
	}

}
