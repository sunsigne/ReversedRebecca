package com.sunsigne.reversedrebecca.object.puzzle.dig;

import com.sunsigne.reversedrebecca.object.animation.DigAnimationObject;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.dig.DigPuzzle;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;

public class ParticleDigAnimation extends DigAnimationObject {

	public static void generate(DigPuzzle puzzle, int x, int y) {
		ParticleDigAnimation particles = new ParticleDigAnimation(puzzle, x, y);

		GenericListener listener = () -> {
			if (LAYER.PUZZLE.getHandler().getList().isEmpty() == false)
				LAYER.PUZZLE.addObject(particles);
		};

		new GameTimer(1, true, listener);
	}

	private ParticleDigAnimation(DigPuzzle puzzle, int x, int y) {
		super(x + Size.XS / 8, y + Size.XS / 8);
		this.puzzle = puzzle;

	}

	private DigPuzzle puzzle;

	////////// SIZE ////////////

	@Override
	public int getWidth() {
		return puzzle.getSize();
	}

	@Override
	public int getHeight() {
		return puzzle.getSize();
	}

	////////// TICK ////////////

	@Override
	public FRAME_RATE getFrameRate() {
		return FRAME_RATE.SWIFT;
	}

}
