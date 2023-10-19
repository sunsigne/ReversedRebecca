package com.sunsigne.reversedrebecca.world.mapcreator.mappable.loot.tools;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.loot.ToolObject;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.MappableComplexe;

public interface MappableTool extends MappableComplexe {

	////////// MAPPABLE ////////////

	public ToolPlayer getToolPlayer();

	@Override
	public default GameObject createObject(int red, int green, int blue, int x, int y) {
		LVL difficulty = LVL.NULL;

		while (green > 0) {
			green--;
			difficulty = difficulty.getNext();
		}

		return new ToolObject(getToolPlayer(), difficulty, x, y);
	}

	@Override
	public default boolean isValidGreen(int green) {
		return 1 <= green && green <= 6;
	}

}
