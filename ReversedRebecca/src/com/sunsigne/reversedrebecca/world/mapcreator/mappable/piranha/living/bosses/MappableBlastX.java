package com.sunsigne.reversedrebecca.world.mapcreator.mappable.piranha.living.bosses;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.blastx.BlastXBoss;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableBlastX implements Mappable {

	private MappableBlastX() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableBlastX();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new BlastXBoss(x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 255, 255, 1 };
		return rgb;
	}

}
