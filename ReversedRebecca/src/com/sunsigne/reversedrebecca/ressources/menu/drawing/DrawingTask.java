package com.sunsigne.reversedrebecca.ressources.menu.drawing;

import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.menu.UnlockableList;
import com.sunsigne.reversedrebecca.ressources.menu.UnlockableTask;
import com.sunsigne.reversedrebecca.ressources.menu.Unlockables;

public class DrawingTask implements UnlockableTask {

	@Override
	public void create(String name) {
		Drawing drawing = new Drawing(name);
		var list = UnlockableList.getDrawingList();
		list.addObject(drawing);
	}

	@Override
	public void unlock(String name) {
		var list = UnlockableList.getDrawingList();
		list.getList().forEach(tempDrawing -> {
			if (tempDrawing.getName().equalsIgnoreCase(name)) {
				if (tempDrawing.isLocked() == false)
					return;

				tempDrawing.unlocked();
				System.out.println("DRAWING : " + tempDrawing.getName().toUpperCase());
			}
		});
	}

	@Override
	public void reset() {
		new FileTask().write(Drawing.file, "");
		UnlockableList.getDrawingList().clear();
		new Unlockables().loadRessources();
	}

}
