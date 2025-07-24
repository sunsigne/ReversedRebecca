package com.sunsigne.reversedrebecca.world.mapcreator.mappable.piranha.living.bosses;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley.DoubleYBoss;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableDoubleY implements Mappable {

	private MappableDoubleY() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableDoubleY();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new DoubleYBoss(x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 255, 255, 2 };
		return rgb;
	}

}
