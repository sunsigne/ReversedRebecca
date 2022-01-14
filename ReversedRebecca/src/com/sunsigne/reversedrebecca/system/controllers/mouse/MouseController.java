package com.sunsigne.reversedrebecca.system.controllers.mouse;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class MouseController extends MouseAdapter {

	public MouseController(MouseClickEvent mouseClickEvent) {
		this.mouseClickEvent = mouseClickEvent;
		Game.getInstance().addMouseListener(this);
		Game.getInstance().requestFocus();
	}
	
	////////// MOUSE ////////////

	private MouseClickEvent mouseClickEvent;
	
	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		mouseClickEvent.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseClickEvent.mouseReleased(e);
	}
}
