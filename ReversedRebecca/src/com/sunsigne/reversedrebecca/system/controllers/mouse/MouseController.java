package com.sunsigne.reversedrebecca.system.controllers.mouse;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ConcurrentLinkedQueue;

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
	public ConcurrentLinkedQueue<MouseEvent> mousePressedEvent = new ConcurrentLinkedQueue<>();
	public ConcurrentLinkedQueue<MouseEvent> mouseReleasedEvent = new ConcurrentLinkedQueue<>();

	@Override
	public void mouseMoved(MouseEvent e) {
		if (PresetMousePos.usingPreset == false)
			ControllerManager.getInstance().setUsingGamepad(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ControllerManager.getInstance().setUsingGamepad(false);

		if (LAYER.LOADING.getHandler().getList().isEmpty())
			mousePressedEvent.add(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ControllerManager.getInstance().setUsingGamepad(false);

		if (LAYER.LOADING.getHandler().getList().isEmpty())
			mouseReleasedEvent.add(e);
	}

	////////// TICK ////////////

	public static void tick() {
		MouseEvent e;
		for (MouseListener tempMouseListener : Game.getInstance().getMouseListeners()) {
			if (tempMouseListener instanceof MouseController == false)
				continue;

			MouseController tempMouseController = (MouseController) tempMouseListener;
			while ((e = tempMouseController.mousePressedEvent.poll()) != null)
				tempMouseController.mouseUserEvent.mousePressed(e);

			while ((e = tempMouseController.mouseReleasedEvent.poll()) != null)
				tempMouseController.mouseUserEvent.mouseReleased(e);
		}
	}

}
