package com.sunsigne.reversedrebecca.world.mapcreator.mappable.loot.tools;

import com.sunsigne.reversedrebecca.characteristics.tools.KeyToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableKey implements MappableTool {

	private MappableKey() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableKey();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public ToolPlayer getToolPlayer() {
		return new KeyToolPlayer();
	}

	@Override
	public int[] rgbCode() {
		// green = 1 to 6
		int[] rgb = { 1, -1, 255 };
		return rgb;
	}

}
