package com.sunsigne.reversedrebecca.object.puzzle.hack;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class ProcessorTrash extends ProcessorEatable {

	public ProcessorTrash(Puzzle puzzle) {
		super(puzzle, "trash");
		loadImages();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "trash";
	}

	////////// VIRUS ACTION ////////////

	private boolean isEmpty;

	@Override
	public void doVirusAction() {
		if (isEmpty == false)
			emptyTrash();

		else
			destroyTrash();
	}

	private void emptyTrash() {
		isEmpty = true;
	}

	private void destroyTrash() {
		getHandler().removeObject(this);
		getComputer().removeObject(this);
	}

	////////// TEXTURE ////////////

	private BufferedImage empty_img;
	private BufferedImage full_img;

	private void loadImages() {
		empty_img = new ImageTask()
				.loadImage("textures/puzzle/" + getPuzzle().getName() + "_" + getName() + "_" + "empty");
		full_img = new ImageTask()
				.loadImage("textures/puzzle/" + getPuzzle().getName() + "_" + getName() + "_" + "filled");
	}

	@Override
	public BufferedImage getImage() {
		if (isEmpty)
			return empty_img;

		else
			return full_img;
	}

}
