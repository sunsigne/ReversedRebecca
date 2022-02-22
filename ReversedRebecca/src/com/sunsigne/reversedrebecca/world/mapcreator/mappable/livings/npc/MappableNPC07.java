package com.sunsigne.reversedrebecca.world.mapcreator.mappable.livings.npc;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableNPC07 implements Mappable {

	private MappableNPC07() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableNPC07();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new NPC("NPC-07", x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 255, 7, 0 };
		return rgb;
	}

}
