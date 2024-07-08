package com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorFolder;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.Size;

public class AntivirusLocker extends AntivirusObject {

	public AntivirusLocker(Puzzle puzzle, ProcessorFolder folder) {
		super(puzzle, new Translatable().getTranslatedText("Antivirus" + "Locker", FilePath.PUZZLE));

		do {
			// chose ONE target between all processors of the folder
			int num = new RandomGenerator().getIntBetween(0, folder.getProcessors().length - 1);
			this.target = folder.getProcessors()[num];
			// that is not already locked, or deleted
		} while (target.isLocked() || getComputer().containsObject(target) == false);
		// (as lockers can lock other lockers, this loop can't be infinite)

		antivirusAction();
	}

	////////// ANTIVIRUS ////////////

	private ProcessorObject target;

	public ProcessorObject getTarget() {
		return target;
	}

	@Override
	public void antivirusAction() {
		if (isCritical() == false)
			target.setLocker(this);
	}

	@Override
	public void destroyAntivirus() {
		target.setLocker(null);
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	@Override
	public int getSheetColCriterion() {
		return 1;
	}

	public BufferedImage getSubImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "hack_addon");
			image = getSheetSubImage(sheet, 1, 1, 48, 48);
		}
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
