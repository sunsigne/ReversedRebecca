package com.sunsigne.reversedrebecca.object.puzzler.chest;

import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.door.UnlockDoorAction;

public class UnlockChestAction extends UnlockDoorAction {

	public UnlockChestAction(PuzzlerObject puzzlerObject) {
		super(puzzlerObject);
	}

	////////// PUZZLE ////////////

	@Override
	public PuzzlerObject getNullObject(PuzzlerObject puzzlerObject, int x, int y) {
		boolean little = ((ChestObject) puzzlerObject).isLittle();
		return new FreeChestObject(0, x, y, little) {

			public String getLootFile() {
				return ((ChestObject) puzzlerObject).getLootFile();
			}

		};
	}

}
