package com.sunsigne.reversedrebecca.world.mapcreator.mappable.loot.tools.key;

import com.sunsigne.reversedrebecca.characteristics.tools.KeyToolPlayer;
import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.loot.ToolObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableYellowKey implements Mappable {

	private MappableYellowKey() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableYellowKey();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ToolObject(new KeyToolPlayer(), LVL.YELLOW, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 0, 2, 255 };
		return rgb;
	}

}
