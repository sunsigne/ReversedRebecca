package com.sunsigne.reversedrebecca.world.mapcreator.mappable.other;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.other.decoration.DecorationObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableTowel implements Mappable {

	private MappableTowel() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableTowel();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new DecorationObject(x, y, "towel");
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 251, 0, 255 };
		return rgb;
	}

}
