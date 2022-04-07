package com.sunsigne.reversedrebecca.world.mapcreator.mappable.extrabehavior.living.npc;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.npc.NPC;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableNPC20 implements Mappable {

	private MappableNPC20() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableNPC20();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new NPC("NPC-20", x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 255, 20, 0 };
		return rgb;
	}

}
