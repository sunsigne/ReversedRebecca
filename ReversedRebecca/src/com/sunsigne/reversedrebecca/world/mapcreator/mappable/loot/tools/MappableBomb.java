package com.sunsigne.reversedrebecca.world.mapcreator.mappable.loot.tools;

import com.sunsigne.reversedrebecca.characteristics.tools.BombToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableBomb implements MappableTool {

	private MappableBomb() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableBomb();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public ToolPlayer getToolPlayer() {
		return new BombToolPlayer();
	}

	@Override
	public int[] rgbCode() {
		// green = 1 to 6
		int[] rgb = { 2, -1, 255 };
		return rgb;
	}

}
