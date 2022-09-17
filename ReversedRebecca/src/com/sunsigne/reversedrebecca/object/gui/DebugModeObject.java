package com.sunsigne.reversedrebecca.object.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.physic.debug.DebugMode;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class DebugModeObject extends GameObject implements TickFree, MouseUserEvent {

	public DebugModeObject(DebugMode debugMode) {
		super(Window.WIDHT - Size.L, Window.HEIGHT - Size.L, Size.L, Size.L);
		this.debugMode = debugMode;
		setY(getY() - (debugMode.getIndex()) * getHeight());
		loadImages();
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "DEBUG GUI";
		var name = debugMode.getName().split("debugmode_")[1].toUpperCase();
		return clazz + " : " + name + " / " + " ACTIVE : " + debugMode.getState();
	}

	////////// DEBUG MODE ////////////

	private DebugMode debugMode;

	////////// TEXTURE ////////////

	private BufferedImage image;
	private BufferedImage active_image;

	private void loadImages() {
		image = new ImageTask().loadImage("textures/gui/" + debugMode.getName());
		active_image = new ImageTask().loadImage("textures/gui/" + debugMode.getName() + "_active");
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);

		if (debugMode.getState())
			g.drawImage(active_image, getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// MOUSE ////////////

	private MouseController mouseController = new MouseController(this);

	@Override
	public MouseController getMouseController() {
		return mouseController;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (isSelected() == false)
			return;

		debugMode.cycle();
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
