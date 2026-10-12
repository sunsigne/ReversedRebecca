package com.sunsigne.reversedrebecca.ressources.menu.drawing;

import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.menu.UnlockableList;
import com.sunsigne.reversedrebecca.ressources.menu.UnlockableTask;
import com.sunsigne.reversedrebecca.ressources.menu.Unlockables;

public class DrawingTask implements UnlockableTask {

	@Override
	public void create(String name) {
		Drawing drawing = new Drawing(name);
		var map = UnlockableList.getDrawingMap();
		map.put(name, drawing);
	}

	@Override
	public void unlock(String name) {
		var map = UnlockableList.getDrawingMap();
		Drawing drawing = map.get(name.toLowerCase());

		if (drawing.isLocked() == false)
			return;

		drawing.unlocked();
		System.out.println("DRAWING : " + drawing.getName().toUpperCase());

	}

	@Override
	public void reset() {
		new FileTask().write(Drawing.file, "");
		UnlockableList.getDrawingMap().clear();
		new Unlockables().loadRessources();
		unlock("dave_chair");
	}

}
