package com.sunsigne.reversedrebecca.object.extrabehaviors.menu;

import java.awt.event.MouseEvent;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.MouseBehavior;
import com.sunsigne.reversedrebecca.pattern.Cycloid;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;

public class TestBehavior implements MouseBehavior {

	public TestBehavior(ButtonObject clickable) {
		this.clickable = clickable;
	}

	////////// BEHAVIOR ////////////

	private ButtonObject clickable;

	@Override
	public ButtonObject getExtraBehaviorsObject() {
		return clickable;
	}

	////////// MOUSE ////////////

	Cycloid<String> cycloid = new Cycloid<>("hello", "one", "two");

	@Override
	public MouseController getMouseController() {
		return clickable.getMouseController();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (mouseOver(e, clickable.getRect())) {
			System.out.println(cycloid.getState());
			cycloid.cycle();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}