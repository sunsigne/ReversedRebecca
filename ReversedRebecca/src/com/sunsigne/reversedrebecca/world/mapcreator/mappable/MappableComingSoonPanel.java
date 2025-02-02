package com.sunsigne.reversedrebecca.world.mapcreator.mappable;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.other.ComingSoonPanel;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;

public class MappableComingSoonPanel implements Mappable {

	private MappableComingSoonPanel() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableComingSoonPanel();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ComingSoonPanel(x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 255, 128, 0 };
		return rgb;
	}

}
