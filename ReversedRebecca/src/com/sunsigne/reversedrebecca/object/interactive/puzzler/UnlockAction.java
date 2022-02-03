package com.sunsigne.reversedrebecca.object.interactive.puzzler;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.characteristics.KeyToolPlayer;
import com.sunsigne.reversedrebecca.object.interactive.InteractiveControlObject;
import com.sunsigne.reversedrebecca.object.interactive.ToolAction;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class UnlockAction extends ToolAction {

	public UnlockAction(InteractiveControlObject interactiveControlObject) {
		super(interactiveControlObject, new Translatable().getTranslatedText("UNLOCK", "actions/door.csv", 2), new KeyToolPlayer(), unlockAction(interactiveControlObject),
				KeyEvent.VK_E);
	}

	////////// INTERACTION ////////////

	private static GenericListener unlockAction(InteractiveControlObject interactiveControlObject) {
		GenericListener listener = () -> {
			NullDoorObject nullDoor = new NullDoorObject(interactiveControlObject.getX(), interactiveControlObject.getY());
			interactiveControlObject.getHandler().addObject(nullDoor);
			interactiveControlObject.getHandler().removeObject(interactiveControlObject);
		};
		return listener;
	}

}
