package com.sunsigne.reversedrebecca.world.mapcreator.mappable.piranha.living.npc;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.piranha.living.NPC;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableNPC08 implements Mappable {

	private MappableNPC08() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableNPC08();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new NPC("NPC-08", x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 255, 8, 0 };
		return rgb;
	}

}
