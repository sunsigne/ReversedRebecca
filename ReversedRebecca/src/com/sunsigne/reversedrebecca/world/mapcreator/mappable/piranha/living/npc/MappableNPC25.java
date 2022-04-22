package com.sunsigne.reversedrebecca.world.mapcreator.mappable.piranha.living.npc;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.piranha.living.NPC;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableNPC25 implements Mappable {

	private MappableNPC25() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableNPC25();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new NPC("NPC-25", x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 255, 25, 0 };
		return rgb;
	}

}
