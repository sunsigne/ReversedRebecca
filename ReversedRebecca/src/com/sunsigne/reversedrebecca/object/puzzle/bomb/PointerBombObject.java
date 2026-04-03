package com.sunsigne.reversedrebecca.object.puzzle.bomb;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.MouseSpammableGamepadObject;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.SpammableGamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;

public class PointerBombObject extends PuzzleObject
		implements SheetableImage, MouseUserEvent, MouseSpammableGamepadObject {

	public PointerBombObject(Puzzle puzzle, boolean critical) {
		super(puzzle, critical, 0, 0, Size.S, Size.S);
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "PUZZLE : POINTER";
		var pos = getRow(Size.S + getX() / 2) + "-" + getCol(Size.S + getY() / 2);
		return clazz + " : " + pos + " / ";
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		int[] pos = new MousePos().get();

		int mouseX = pos[0] - getWidth() / 2;
		int mouseY = pos[1] - getHeight() / 2;

		followMouse(mouseX, mouseY);
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 1;
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

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX() - getWidth() / 2, getY() - getHeight() / 2, 2 * getWidth(), 2 * getHeight(),
				null);
	}
	////////// MOUSE ////////////

	private MouseController mouseController = new MouseController(this);

	@Override
	public MouseController getMouseController() {
		return mouseController;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (isClickable() == false)
			return;

		createHole();
		createParticules(3);
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	private void createHole() {
		int[] pos = new MousePos().get();

		int mouseX = pos[0] - getWidth();
		int mouseY = pos[1] - getHeight();

		HoleBombObject hole = new HoleBombObject(getPuzzle(), isCritical(), mouseX, mouseY);
		LAYER.PUZZLE.getHandler().getList().add(1, hole);
	}

	private void createParticules(int num_of_particles) {
		int[] pos = new MousePos().get();

		int mouseX = pos[0] - getWidth();
		int mouseY = pos[1] - getHeight();

		for (int index = 0; index < num_of_particles; index++) {
			ParticleBombObject particle = new ParticleBombObject(getPuzzle(), isCritical(), mouseX, mouseY);
			LAYER.PUZZLE.getHandler().addObject(particle);
		}
	}

	////////// SPAMMABLE ////////////

	private SpammableGamepadEvent[] spammable;

	@Override
	public SpammableGamepadEvent[] getSpammables() {
		if (spammable != null)
			return spammable;

		spammable = new SpammableGamepadEvent[4];
		createSpammable();
		return spammable;
	}

	@Override
	public void setSpammable(int index, SpammableGamepadEvent spammable) {
		this.spammable[index] = spammable;
	}

	////////// GAMEPAD ////////////

	private GamepadController gamepadController = new GamepadController(this);

	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}

}
