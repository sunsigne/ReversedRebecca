package com.sunsigne.reversedrebecca.object.puzzle.hack;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class ProcessorTrash extends ProcessorEatable {

	public ProcessorTrash(Puzzle puzzle) {
		super(puzzle, new Translatable().getTranslatedText("ProcessorTrash", FilePath.PUZZLE));
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
		removeObject();
		getComputer().removeObject(this);
	}

	////////// TEXTURE ////////////

	private BufferedImage empty_img;
	private BufferedImage full_img;

	private void loadImages() {
		BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "hack_processor");
		empty_img = getSheetSubImage(sheet, 4);
		full_img = getSheetSubImage(sheet, 3);
	}

	@Override
	public BufferedImage getImage() {
		if (isEmpty)
			return empty_img;

		else
			return full_img;
	}

}
