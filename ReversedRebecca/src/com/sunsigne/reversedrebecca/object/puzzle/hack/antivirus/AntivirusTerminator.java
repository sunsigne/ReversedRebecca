package com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public class AntivirusTerminator extends AntivirusObject {

	public AntivirusTerminator(Puzzle puzzle) {
		super(puzzle, "Terminator");
		loadImages();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "antivirus_terminator";
	}

	////////// ANTIVIRUS ////////////

	private boolean prepare_to_kill;

	@Override
	public void antivirusAction() {
		if (prepare_to_kill == false) {
			threaten();
			return;
		}

		terminate();
	}

	private void threaten() {
		prepare_to_kill = true;
		playSound("sound/laser_aiming");
	}

	private void terminate() {
		getPuzzle().closePuzzle(false);
		new SoundTask().play(SOUNDTYPE.SOUND, "sound/laser_shoot");
	}

	@Override
	public void destroyAntivirus() {

	}

	////////// TEXTURE ////////////

	private BufferedImage on_img;
	private BufferedImage off_img;

	private void loadImages() {
		on_img = new ImageTask().loadImage("textures/puzzle/" + getPuzzle().getName() + "_" + getName() + "_" + "on");
		off_img = new ImageTask().loadImage("textures/puzzle/" + getPuzzle().getName() + "_" + getName() + "_" + "off");
	}

	@Override
	public BufferedImage getImage() {
		if (getVirus().isDisguised())
			return off_img;

		else
			return on_img;
	}

	////////// MOUSE ////////////

	@Override
	public void mousePressed(MouseEvent e) {
		if (getVirus().isDisguised())
			super.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (getVirus().isDisguised())
			return;

		if (isClickable())
			antivirusAction();
	}

}
