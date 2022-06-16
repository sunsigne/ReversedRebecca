package com.sunsigne.reversedrebecca.world.mapcreator.mappable.loot.tools.microchip;

import com.sunsigne.reversedrebecca.characteristics.tools.MicrochipToolPlayer;
import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.loot.ToolObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableYellowMicrochip implements Mappable {

	private MappableYellowMicrochip() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableYellowMicrochip();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ToolObject(new MicrochipToolPlayer(), LVL.YELLOW, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 3, 3, 255 };
		return rgb;
	}

}
