package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.pattern.render.RectDecoration;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.ressources.sound.Volume;
import com.sunsigne.reversedrebecca.ressources.sound.VolumeMusic;
import com.sunsigne.reversedrebecca.ressources.sound.VolumeSound;
import com.sunsigne.reversedrebecca.ressources.sound.VolumeVoice;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
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

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "BUTTON VOLUME SCALE";
		var name = volume.getValueToRead().toUpperCase();
		var currentVolume = volume.getRegisteredVolume();
		return clazz + " : " + name + " = " + currentVolume;
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
		new SoundTask().changeMusicVol(VolumeMusic.getVolume());
	}

	private ButtonEvent request;

	public void updateRequest(ButtonEvent e) {
		if (e.getKey() != ButtonEvent.LEFT && e.getKey() != ButtonEvent.RIGHT)
			gamepad_holding = false;

		this.request = e;
	}
	
	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.MENU;
	}
	
	////////// TICK ////////////

	private boolean holding;
	private boolean gamepad_holding;

	@Override
	public void tick() {
		gamepad_holding = false;

		if (isExtendedSelected() && ControllerManager.getInstance().isUsingGamepad())
			gamepad_holding = true;

		if (holding)
			tickHodling();
		if (gamepad_holding)
			tickGamepadHolding();
	}

	private void tickHodling() {
		playHelpingSound();

		MousePos mousePos = new MousePos();

		setX(mousePos.getX());
		refreshVolume();

		if (mousePos.getX() > xmax) {
			setX(xmax);
			mousePos.setX(xmax);
		}

		if (mousePos.getX() < xmin) {
			setX(xmin);
			mousePos.setX(xmin);
		}

		if (mousePos.getY() < getY() || mousePos.getY() > getY() + getHeight())
			mousePos.setY(getY() + getHeight() / 2);
	}

	private final int STEP = 2;

	private void tickGamepadHolding() {
		playHelpingSound();

		if (request == null)
			return;

		if (request.getKey() == ButtonEvent.LEFT) {
			setX(Math.max(getX() - STEP, xmin));
			refreshVolume();
		}

		if (request.getKey() == ButtonEvent.RIGHT) {
			setX(Math.min(getX() + STEP, xmax));
			refreshVolume();
		}
	}

	private void playHelpingSound() {
		if (volume instanceof VolumeSound)
			playSound();

		if (volume instanceof VolumeVoice)
			playVoice();
	}

	private int time;

	private void playSound() {
		if (time > 0) {
			time--;
			return;
		}

		time = 40;
		new SoundTask().playSound(SOUNDTYPE.SOUND, "jump");
	}

	private void playVoice() {
		if (time > 0)
			time--;
		else
			time = 40;

		if (time > 30 && time % 2 == 0)
			new SoundTask().playSound(SOUNDTYPE.VOICE, "rebecca");
	}

	////////// RENDER ////////////

	private RectDecoration rect = new RectDecoration();

	@Override
	public void render(Graphics g) {
		Color text_color = new Color(255, 204, 0);
		Color shadow_color = new Color(255, 163, 0, 80);

		if (isSelected() || holding || gamepad_holding)
			text_color = new Color(255, 232, 170);

		int gap = 4;
		g.setColor(shadow_color);
		g.fillRect(getX() + gap, getY() + gap, getWidth(), getHeight());
		g.setColor(text_color);
		g.fillRect(getX(), getY(), getWidth(), getHeight());

		if (gamepad_holding)
			rect.drawRoundRect(g, getExtendedRect(), null);
	}

	////////// MOUSE ////////////

	private MouseController mouseController = new MouseController(this);

	@Override
	public MouseController getMouseController() {
		return mouseController;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (isExtendedSelected() == false)
			return;

		holding = true;
		new GameCursor().setCursor(null);

		if (isSelected())
			new MousePos().setX(getX());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		boolean flag = holding;
		holding = false;

		if (flag) {
			MousePos mousePos = new MousePos();
			mousePos.setX(getX() + getGap());
			mousePos.setY(getY() + getHeight() / 2);
		}

		new GameCursor().setCursor(CURSOR_TYPE.NORMAL);
	}

	private int getGap() {
		int gap = getWidth() / 2;

		if (getX() <= xmin)
			gap = 0;
		if (getX() >= xmax)
			gap = getWidth() - 2;

		return gap;
	}

	@Override
	public boolean isSelected() {
		return mouseOver(new MousePos().get(), getRect()) && isClickable();
	}

	public boolean isExtendedSelected() {
		return mouseOver(new MousePos().get(), getExtendedRect()) && isClickable();
	}

	////////// SIZE ////////////

	@Override
	public int[] getRect() {
		int[] rect = new int[4];
		rect[0] = getX() - 1;
		rect[1] = getY();
		rect[2] = getWidth();
		rect[3] = getHeight();
		return rect;
	}

	public int[] getExtendedRect() {
		int[] rect = new int[4];
		rect[0] = xmin - 1 - 2;
		rect[1] = getY() - 2;
		rect[2] = xmax - (xmin - 1) + getWidth() + 10;
		rect[3] = getHeight() + 4;
		return rect;
	}

}
