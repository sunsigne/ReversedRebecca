package com.sunsigne.reversedrebecca.world.mapcreator.mappable.extrabehavior.living.npc;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.npc.NPC;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableNPC01 implements Mappable {

	private MappableNPC01() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableNPC01();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new NPC("NPC-01", x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 255, 1, 0 };
		return rgb;
	}

}
