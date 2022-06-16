package com.sunsigne.reversedrebecca.world.mapcreator.mappable.loot.tools.shovel;

import com.sunsigne.reversedrebecca.characteristics.tools.ShovelToolPlayer;
import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.loot.ToolObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableGreenShovel implements Mappable {

	private MappableGreenShovel() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableGreenShovel();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ToolObject(new ShovelToolPlayer(), LVL.GREEN, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 4, 2, 255 };
		return rgb;
	}

}
