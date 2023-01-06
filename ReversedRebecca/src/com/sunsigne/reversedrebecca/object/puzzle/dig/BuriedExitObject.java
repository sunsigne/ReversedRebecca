package com.sunsigne.reversedrebecca.object.puzzle.dig;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DIG_STATE;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public class BuriedExitObject extends BuriedObject {

	public BuriedExitObject(Puzzle puzzle, int w, int h) {
		super(puzzle, w, h);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "EXIT";
	}

	////////// TEXTURE ////////////

	@Override
	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/puzzle/" + getPuzzle().getName() + "_" + getName().toLowerCase()
					+ "_" + state.getState());
		return image;
	}

	////////// MOUSE ////////////

	private Cycloid<Integer> state = new Cycloid<>(1, 2, 3, 4);

	@Override
	public void mousePressed(MouseEvent e) {
		if (isSelected() == false)
			return;

		if (getPuzzle().getState() != DIG_STATE.DIG && isCritical() == false) {
			new SoundTask().playSound(SOUNDTYPE.SOUND, "dig_fail");
			return;
		}

		new SoundTask().playSound(SOUNDTYPE.SOUND, "dig");

		if (state.getState() == 3) {
			setClickable(false);
			if (getPuzzle().stillContainsExit(this) == false)
				getPuzzle().closePuzzle(true);
		}

		state.cycle();
		image = null;
	}

}
