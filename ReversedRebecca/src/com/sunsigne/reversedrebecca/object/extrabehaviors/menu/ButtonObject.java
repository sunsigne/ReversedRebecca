package com.sunsigne.reversedrebecca.object.extrabehaviors.menu;

import java.awt.event.MouseEvent;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;

public class ButtonObject extends ExtraBehaviorsObject {

	public ButtonObject(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	////////// BEHAVIOR ////////////

	@Override
	public void mousePressed(MouseEvent e) {
		if (mouseOver(e, getRect())) {
			System.out.println("lel");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}	

}
