package com.sunsigne.reversedrebecca.system.controllers.mouse;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class MouseController extends MouseAdapter {

	public MouseController(MouseUserEvent mouseUserEvent) {
		this.mouseUserEvent = mouseUserEvent;
		Game.getInstance().addMouseListener(this);
		Game.getInstance().addMouseMotionListener(this);
	}

	public void removeMouseListener() {
		Game.getInstance().removeMouseListener(this);
		Game.getInstance().removeMouseMotionListener(this);
	}

	////////// MOUSE ////////////

	private MouseUserEvent mouseUserEvent;

	@Override
	public void mouseMoved(MouseEvent e) {
		if (PresetMousePos.usingPreset == false)
			ControllerManager.getInstance().setUsingGamepad(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ControllerManager.getInstance().setUsingGamepad(false);

		if (LAYER.LOADING.getHandler().getList().isEmpty())
			mouseUserEvent.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ControllerManager.getInstance().setUsingGamepad(false);

		if (LAYER.LOADING.getHandler().getList().isEmpty())
			mouseUserEvent.mouseReleased(e);
	}

}
