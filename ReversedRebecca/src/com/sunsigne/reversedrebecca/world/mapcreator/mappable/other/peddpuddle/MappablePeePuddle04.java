package com.sunsigne.reversedrebecca.world.mapcreator.mappable.other.peddpuddle;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.other.decoration.DecorationObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappablePeePuddle04 implements Mappable {

	private MappablePeePuddle04() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappablePeePuddle04();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new DecorationObject(x, y, "pee_puddle_04");
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 254, 0, 251 };
		return rgb;
	}

}
