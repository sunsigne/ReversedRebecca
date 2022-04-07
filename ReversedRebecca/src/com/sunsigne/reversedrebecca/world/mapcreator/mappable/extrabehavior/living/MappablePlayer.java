package com.sunsigne.reversedrebecca.world.mapcreator.mappable.extrabehavior.living;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.Player;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappablePlayer implements Mappable {

	private MappablePlayer() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappablePlayer();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new Player(x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 255, 0, 0 };
		return rgb;
	}

}
