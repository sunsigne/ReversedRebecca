package com.sunsigne.reversedrebecca.world.mapcreator.mappable.loot.tools.key;

import com.sunsigne.reversedrebecca.characteristics.tools.KeyToolPlayer;
import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.loot.ToolObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableCyanKey implements Mappable {

	private MappableCyanKey() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableCyanKey();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ToolObject(new KeyToolPlayer(), LVL.CYAN, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 1, 1, 255 };
		return rgb;
	}

}
