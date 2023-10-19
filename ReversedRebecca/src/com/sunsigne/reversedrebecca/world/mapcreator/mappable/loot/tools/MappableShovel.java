package com.sunsigne.reversedrebecca.world.mapcreator.mappable.loot.tools;

import com.sunsigne.reversedrebecca.characteristics.tools.ShovelToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableShovel implements MappableTool {

	private MappableShovel() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableShovel();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public ToolPlayer getToolPlayer() {
		return new ShovelToolPlayer();
	}

	@Override
	public int[] rgbCode() {
		// green = 1 to 6
		int[] rgb = { 4, -1, 255 };
		return rgb;
	}

}
