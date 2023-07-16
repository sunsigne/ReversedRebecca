package com.sunsigne.reversedrebecca.world.mapcreator.mappable.rocks.pyramid;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.piranha.RockPiranhaObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.rocks.RockSize;

public class MappablePyramidRock1212 implements Mappable {

	private MappablePyramidRock1212() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappablePyramidRock1212();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		var rock = new RockPiranhaObject(x, y);
		rock.setBounds(12, 12, RockSize.PYRAMID[0], RockSize.PYRAMID[1]);		
		return rock;
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 142, 142, 254 };
		return rgb;
	}

}
