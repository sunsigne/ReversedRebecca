package com.sunsigne.reversedrebecca.world.mapcreator.mappable.other;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.other.decoration.Candle;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableCandle implements Mappable {

	private MappableCandle() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableCandle();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new Candle(x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 252, 0, 255 };
		return rgb;
	}

}
