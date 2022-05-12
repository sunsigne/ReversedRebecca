package com.sunsigne.reversedrebecca.world.mapcreator.mappable.other;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.other.Book;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableBook implements Mappable {

	private MappableBook() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableBook();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new Book(x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 251, 0, 255 };
		return rgb;
	}

}
