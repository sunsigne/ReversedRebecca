package com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;

public class AntivirusLocker extends AntivirusObject {

	// WARNING, don't forget to add this Antivirus in the same folder than the target !
	public AntivirusLocker(Puzzle puzzle, ProcessorObject... processors) {
		super(puzzle, "Locker");

		// chose ONE target between all processors
		int size = processors.length;
		int num = new RandomGenerator().getIntBetween(0, size - 1);
		for (int index = 0; index < size; index++) {
			this.target = processors[num];
		}

		antivirusAction();
	}

	////////// ANTIVIRUS ////////////

	private ProcessorObject target;

	public ProcessorObject getTarget() {
		return target;
	}
	
	@Override
	public void antivirusAction() {
		target.setLocked(true);
	}

	@Override
	public void destroyAntivirus() {
		target.setLocked(false);
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getSubImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/puzzle/" + getPuzzle().getName() + "_" + "key");
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		super.render(g);
		int size = Size.XS;
		g.drawImage(getSubImage(), target.getX() + size, target.getY() + size, getWidth() - 2 * size,
				getHeight() - 2 * size, null);
	}

}
