package com.sunsigne.reversedrebecca.system.controllers.mouse;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.ControllerAnalyser;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class MouseController extends MouseAdapter {

	public MouseController(MouseUserEvent mouseUserEvent) {
		this.mouseUserEvent = mouseUserEvent;
		Game.getInstance().addMouseListener(this);
	}

	public void removeMouseListener() {
		Game.getInstance().removeMouseListener(this);
	}

	////////// MOUSE ////////////

	private MouseUserEvent mouseUserEvent;

	@Override
	public void mousePressed(MouseEvent e) {
		ControllerAnalyser.setUsingGamepad(false);
		
		if (LAYER.LOADING.getHandler().getList().isEmpty())
			mouseUserEvent.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ControllerAnalyser.setUsingGamepad(false);
		
		if (LAYER.LOADING.getHandler().getList().isEmpty())
			mouseUserEvent.mouseReleased(e);
	}
	
}
