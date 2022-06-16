package com.sunsigne.reversedrebecca.world.mapcreator.mappable.loot.tools.shovel;

import com.sunsigne.reversedrebecca.characteristics.tools.ShovelToolPlayer;
import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.loot.ToolObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableOrangeShovel implements Mappable {

	private MappableOrangeShovel() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableOrangeShovel();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ToolObject(new ShovelToolPlayer(), LVL.ORANGE, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 4, 4, 255 };
		return rgb;
	}

}
