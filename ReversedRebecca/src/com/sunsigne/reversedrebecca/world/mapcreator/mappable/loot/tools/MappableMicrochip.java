package com.sunsigne.reversedrebecca.world.mapcreator.mappable.loot.tools;

import com.sunsigne.reversedrebecca.characteristics.tools.MicrochipToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableMicrochip implements MappableTool {

	private MappableMicrochip() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableMicrochip();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public ToolPlayer getToolPlayer() {
		return new MicrochipToolPlayer();
	}

	@Override
	public int[] rgbCode() {
		// green = 1 to 6
		int[] rgb = { 3, -1, 255 };
		return rgb;
	}

}
