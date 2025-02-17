package com.sunsigne.reversedrebecca.object.puzzle.bomb;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;

public class BulletBombObject extends PuzzleObject implements SheetableImage, MouseUserEvent, GamepadEvent {

	public BulletBombObject(Puzzle puzzle, int num, boolean critical) {
		super(puzzle, critical, 12 * Size.L + Size.S, 2 * Size.XS + Size.L, Size.M, Size.M);

		if (isCritical() == false)
			setCount(num);
		else
			setCount(999);
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "PUZZLE : BULLET";
		return clazz + " : " + count;
	}

	////////// COUNT ////////////

	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void removeCount() {
		if (isCritical() == false)
			setCount(getCount() - 1);

		new SoundTask().playSound(SOUNDTYPE.SOUND, "shoot");
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE;
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 2;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	@Override
	public int getSheetSize() {
		return 32;
	}

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + getPuzzle().getName() + "_shoot");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

	////////// RENDER ////////////

	private Font font = new FontTask().createNewFont("DigitalNumbers-Regular.ttf", 100f);

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX() - getWidth() / 2, getY() - getHeight() / 2, 2 * getWidth(), 2 * getHeight(),
				null);

		drawCount(font, g);
	}

	private void drawCount(Font font, Graphics g) {

		int rect[] = new int[] { getX() - 130, getY() - 8, getWidth(), getHeight() };
		Color yellow = new Color(255, 180, 0);
		
		new TextDecoration().drawOutlinesString(g, font, String.valueOf(count) + "X", yellow, Color.BLACK, DIRECTION.RIGHT, rect);
	}

	////////// MOUSE ////////////

	private MouseController mouseController = new MouseController(this);

	@Override
	public MouseController getMouseController() {
		return mouseController;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (isClickable())
			removeCount();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(getCount() <= 0)
			getPuzzle().closePuzzle(false);
	}

	////////// GAMEPAD ////////////

	private GamepadController gamepadController = new GamepadController(this);

	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.A)
			mousePressed(null);
	}

	@Override
	public void buttonReleased(ButtonEvent e) {

	}

}
