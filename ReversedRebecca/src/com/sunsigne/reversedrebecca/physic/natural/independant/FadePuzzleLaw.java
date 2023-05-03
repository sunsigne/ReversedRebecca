package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class FadePuzzleLaw extends IndependantLaw {

	////////// INDEPENDANT LAW ////////////

	private static IndependantLaw independantLaw = new FadePuzzleLaw();

	@Override
	public IndependantLaw getIndependantLaw() {
		return independantLaw;
	}

	////////// TICK ////////////

	private Updatable registeredPuzzle;
	private Updatable puzzle;
	private boolean update;

	private final float SPEED = 0.12f;
	private float alpha;

	@Override
	public void tick(Updatable object) {
		updatePuzzle(object);

		if (object instanceof Puzzle == false)
			return;

		update = false;
		puzzle = object;

		if (puzzle != registeredPuzzle)
			openPuzzle();

		else
			runPuzzle();
	}

	private void updatePuzzle(Updatable object) {
		if (object instanceof Player == false)
			return;

		if (update == false) {
			update = true;
			return;
		}

		else
			alpha = 0;
	}

	private void openPuzzle() {
		registeredPuzzle = puzzle;
		alpha = 0;
	}

	private void runPuzzle() {
		if (alpha < 1f)
			alpha = alpha + SPEED;
		if (alpha > 1f)
			alpha = 1f;
	}

	////////// RENDER ////////////

	private boolean isPuzzleObject(Updatable object) {
		if (object instanceof Puzzle)
			return true;
		if (object instanceof PuzzleObject)
			return true;
		if (object instanceof WallPuzzle)
			return true;

		return false;
	}

	private boolean flag;

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {
		if (isPuzzleObject(object) == false)
			return;

		flag = true;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {
		if (flag == false)
			return;

		flag = false;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

}
