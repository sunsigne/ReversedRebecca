package com.sunsigne.reversedrebecca.object.puzzle.dig;

import com.sunsigne.reversedrebecca.object.animation.DigAnimationObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DIG_STATE;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.dig.DigPuzzle;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class ParticleDigAnimation extends DigAnimationObject {

	public static void generate(DigPuzzle puzzle, int x, int y, DIG_STATE state) {
		ParticleDigAnimation particles = new ParticleDigAnimation(puzzle, x, y) {
			@Override
			public String getName() {
				return super.getName() + "_" + state.getName();
			}
		};

		GenericListener listener = () -> {
			if (LAYER.PUZZLE.getHandler().getList().isEmpty() == false)
				LAYER.PUZZLE.addObject(particles);
		};

		new GameTimer(1, true, listener);
	}

	protected ParticleDigAnimation(DigPuzzle puzzle, int x, int y) {
		super(x + puzzle.getSize() / 8, y + puzzle.getSize() / 6);
		this.puzzle = puzzle;
	}

	private DigPuzzle puzzle;

	////////// NAME ////////////

	@Override
	public String getName() {
		return "particles";
	}

	////////// SIZE ////////////

	@Override
	public int getWidth() {
		return 3 * puzzle.getSize() / 4;
	}

	@Override
	public int getHeight() {
		return 3 * puzzle.getSize() / 4;
	}

	////////// TICK ////////////

	@Override
	public FRAME_RATE getFrameRate() {
		return FRAME_RATE.SWIFT;
	}

}
