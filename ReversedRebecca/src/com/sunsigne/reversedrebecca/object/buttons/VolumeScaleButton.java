package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.ressources.sound.Volume;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor.CURSOR_TYPE;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;

public class VolumeScaleButton extends GameObject implements MouseUserEvent {

	public VolumeScaleButton(int y, Volume volume) {
		super(864, y, 30, 60);

		this.volume = volume;
		setX(getX() + getScale(volume.loadVolume()));
	}

	////////// VOLUME ////////////

	private Volume volume;
	private final int xmin = 864;
	private final int xmax = 1284;

	// convert double between 0 and 2.0 to int between 0 and 420
	private int getScale(double d) {
		return (int) (d * ((xmax - xmin) / 2));
	}

	// convert int between 0 and 420 to double between 0 and 2.0
	private double getScale(int i) {
		return (double) (i / (double) ((xmax - xmin) / 2));
	}

	private void refreshVolume() {
		double newVolume = getScale(getX() - xmin);
		volume.registerVolume(newVolume);
	}

	////////// TICK ////////////

	private boolean holding;

	@Override
	public void tick() {
		if (holding == false)
			return;

		int mouseX = new MousePos().get()[0];

		setX(mouseX);
		refreshVolume();

		if (mouseX > xmax) {
			setX(xmax);
			new MousePos().setX(xmax);
		}

		if (mouseX < xmin) {
			setX(xmin);
			new MousePos().setX(xmin);
		}
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color text_color = new Color(255, 204, 0);
		Color shadow_color = new Color(255, 163, 0, 80);

		if (isSelected() || holding)
			text_color = new Color(255, 232, 170);

		int gap = 4;
		g.setColor(shadow_color);
		g.fillRect(getX() + gap, getY() + gap, getWidth(), getHeight());
		g.setColor(text_color);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
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

		holding = true;
		new GameCursor().setCursor(null);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		holding = false;
		new GameCursor().setCursor(CURSOR_TYPE.NORMAL);
	}

}
