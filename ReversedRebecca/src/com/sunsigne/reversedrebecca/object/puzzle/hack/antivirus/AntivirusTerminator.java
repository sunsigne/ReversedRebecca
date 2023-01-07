package com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public class AntivirusTerminator extends AntivirusObject {

	public AntivirusTerminator(Puzzle puzzle) {
		super(puzzle, new Translatable().getTranslatedText("Antivirus" + "Terminator", FilePath.PUZZLE));
		loadImages();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return super.getName() + "_" + "terminator";
	}

	////////// ANTIVIRUS ////////////

	private boolean ready_to_kill;

	@Override
	public void antivirusAction() {
		if (ready_to_kill == false) {
			ready_to_kill = true;
			return;
		}

		terminate();
	}

	private void terminate() {
		if (isCritical() == false)
			getPuzzle().closePuzzle(false);

		new SoundTask().playSound(SOUNDTYPE.SOUND, "laser_shoot");
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

	private boolean flag;

	@Override
	public void mousePressed(MouseEvent e) {
		if (isClickable() == false)
			return;

		if (getVirus().isDisguised() || isCritical()) {
			super.mousePressed(e);
			return;
		}

		// to threaten as soon as entering the Folder
		if (flag == false)
			threaten();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (isClickable() == false)
			return;

		if (getVirus().isDisguised())
			return;

		// just in case the game "missed" the threat
		if (flag == false)
			threaten();

		antivirusAction();
	}

	private void threaten() {
		flag = true;
		playSound("laser_aiming");
	}

}
