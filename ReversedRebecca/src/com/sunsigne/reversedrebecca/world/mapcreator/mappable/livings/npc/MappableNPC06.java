package com.sunsigne.reversedrebecca.world.mapcreator.mappable.livings.npc;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableNPC06 implements Mappable {

	private MappableNPC06() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableNPC06();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new NPC("NPC-06", x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 255, 6, 0 };
		return rgb;
	}

}
