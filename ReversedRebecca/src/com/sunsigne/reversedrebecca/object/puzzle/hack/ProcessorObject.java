package com.sunsigne.reversedrebecca.object.puzzle.hack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.HackComputer;
import com.sunsigne.reversedrebecca.puzzle.hack.HackPuzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.ressources.sound.VolumeSound;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;

public abstract class ProcessorObject extends PuzzleObject implements MouseUserEvent {

	protected static String file = "puzzle.csv";
	
	public ProcessorObject(Puzzle puzzle, String text) {
		super(puzzle, puzzle.getCol(1) + Size.XS + Size.XS / 2, puzzle.getRow(1) + Size.XS + Size.XS / 2, 5 * Size.XS,
				5 * Size.XS);
		this.text = text;
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// PROCESSOR ////////////

	private boolean locked;

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	////////// VIRUS ACTION ////////////

	public HackComputer getComputer() {
		return ((HackPuzzle) getPuzzle()).getComputer();
	}

	public VirusObject getVirus() {
		return getComputer().getVirus();
	}

	public abstract void doVirusAction();

	public abstract String getVirusActionSound();

	protected void playSound(String path) {
		if (getComputer().hasAudio()) {
			new SoundTask().play(SOUNDTYPE.SOUND, path);
			return;
		}

		double volume = VolumeSound.getVolume() / 6;
		new SoundTask().play(SOUNDTYPE.SOUND, volume, path);
	}

	////////// TICK ////////////

	private boolean flag;

	@Override
	public void tick() {
		if (flag)
			return;

		if (getComputer().hasKeyboard())
			return;

		RandomGenerator rad = new RandomGenerator();
		text = rad.getRandomLetters(rad.getIntBetween(5, 8), "?#$%!@");
		flag = true;
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/puzzle/" + getPuzzle().getName() + "_" + getName());
		return image;
	}

	////////// RENDER ////////////

	private String text;

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
		drawText(g);
	}

	private void drawText(Graphics g) {
		Font font = new Font(Font.DIALOG_INPUT, 2, 45);
		int[] rect = { getRect()[0], getRect()[1] + Size.XS + Size.XS / 2, getRect()[2], getRect()[3] };
		new TextDecoration().drawCenteredString(g, font, text, Color.white, DIRECTION.DOWN, rect);
	}

	////////// MOUSE ////////////

	private MouseController mouseController = new MouseController(this);

	@Override
	public MouseController getMouseController() {
		return mouseController;
	}

	@Override
	public boolean mouseOver(int[] mousePos, int[] rect) {
		VirusObject virus = getVirus();
		int[] modified_rect = rect;

		if (virus.isReversed()) {
			// it's just maths, this match the pos of the reversed virus with Processor pos
			int reversed_x = virus.xmax + virus.xmin - rect[0] - rect[2] / 2;
			int reversed_y = virus.ymax + virus.ymin - rect[1] - rect[3] / 2;
			modified_rect = new int[] { reversed_x, reversed_y, rect[2], rect[3] };
		}

		return MouseUserEvent.super.mouseOver(mousePos, modified_rect);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (isSelected() == false)
			return;

		if (isLocked()) {
			playSound("fail");
			return;
		}

		doVirusAction();
		playSound(getVirusActionSound());
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
