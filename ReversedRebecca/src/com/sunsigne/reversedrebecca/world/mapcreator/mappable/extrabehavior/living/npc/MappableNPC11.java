package com.sunsigne.reversedrebecca.world.mapcreator.mappable.extrabehavior.living.npc;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.npc.NPC;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableNPC11 implements Mappable {

	private MappableNPC11() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableNPC11();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new NPC("NPC-11", x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 255, 11, 0 };
		return rgb;
	}

}
