package com.sunsigne.reversedrebecca.object.puzzle.dig;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.DIG_STATE;
import com.sunsigne.reversedrebecca.puzzle.dig.DigPuzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public class BuriedExitObject extends BuriedObject {

	public BuriedExitObject(Puzzle puzzle, int size) {
		super(puzzle, size);
	}

	////////// NAME ////////////

	@Override
	protected String getName() {
		return "EXIT";
	}

	////////// DIG ////////////

	private Cycloid<Integer> state = new Cycloid<>(1, 2, 3);
	
	////////// TEXTURE ////////////

	@Override
	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask()
					.loadImage("textures/puzzle/" + getPuzzle().getName() + "_" + getName().toLowerCase() + "_" + state.getState());
		return image;
	}

	////////// MOUSE ////////////

	@Override
	public void mousePressed(MouseEvent e) {
		if (isSelected() == false)
			return;

		DigPuzzle puzzle = (DigPuzzle) getPuzzle();
		if (puzzle.getState() != DIG_STATE.DIG)
			return;
		
		new SoundTask().playSound(SOUNDTYPE.SOUND, "dig");

		if (state.getState() == 3)
			getPuzzle().closePuzzle(true);

		state.cycle();
		image = null;
	}

}
