package com.sunsigne.reversedrebecca.puzzle.bombkey;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.PointerKeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.bombs.BombKeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.locks.BombLockObject;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.locks.LittleBombLockObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.list.ListCloner;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor.CURSOR_TYPE;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public abstract class BombKeyPuzzle extends Puzzle {

	public BombKeyPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning, GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
		new GameCursor().setCursor(CURSOR_TYPE.POINTER);

		LAYER.PUZZLE.addObject(getKeyPointer());
	}

	private PointerKeyObject getKeyPointer() {
		PointerKeyObject pointer = new PointerKeyObject(this, isCritical);

		boolean little = getBombLock(this, isCritical, 0, 0) instanceof LittleBombLockObject;
		if (little == false)
			return pointer;

		int size = pointer.getSize() * Size.M / Size.L;
		return new PointerKeyObject(this, isCritical, size, size);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "bombkey";
	}

	////////// FACTORY ////////////

	@Override
	public PuzzleFactory getFactory() {
		return new BombKeyPuzzleFactory();
	}

	////////// PUZZLE ////////////

	private BombLockObject[] bomblock = new BombLockObject[getBombLockAmount()];

	public abstract BombKeyObject getBombKey(Puzzle puzzle, boolean critical);

	public abstract BombLockObject getBombLock(Puzzle puzzle, boolean critical, int x, int y);

	public abstract int getBombLockAmount(); // 3, 4 or 6

	private BombKeyObject bombKey;

	protected void createBombKey() {
		int x = bombKey == null ? getCol(5) : getCol(7);
		if (bombKey != null)
			bombKey.setX(getCol(3));

		bombKey = getBombKey(this, isCritical);
		bombKey.setX(x);
		bombKey.setY(getCol(1) + Size.M);
		LAYER.PUZZLE.addObject(bombKey);
	}

	protected void createBombLocks(int num) {
		BombLockObject tempBombLock = getBombLock(this, isCritical, 0, 0);
		boolean little = tempBombLock instanceof LittleBombLockObject;

		int col = 3 * Size.M / 8 + bombKey.getX();
		int row = (7 * Size.M / 16 + bombKey.getY() + getRow(1) / 2) - (little ? 10 : 0);

		for (int index = 0; index < num; index++) {

			int radCol = 0;
			int radRow = 0;
			int infinite = 0;

			do {
				radCol = col + (little ? 3 : 1) * getCol(new RandomGenerator().getIntBetween(0, little ? 2 : 1))
						/ (little ? 4 : 1);
				radRow = row + (little ? 3 : 1) * getRow(new RandomGenerator().getIntBetween(0, little ? 2 : 1))
						/ (little ? 4 : 1);
				tempBombLock = getBombLock(this, isCritical, radCol, radRow);
				infinite++;

				// verify if a lock is already present at this pos
			} while (infinite < 100 && Handler.getObjectsAtPos(LAYER.PUZZLE.getHandler(), tempBombLock.getX(),
					tempBombLock.getY(), tempBombLock.getSize(), true).getList().isEmpty() == false);

			bomblock[index] = tempBombLock;
			LAYER.PUZZLE.addObject(bomblock[index]);
		}
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		// prevent puzzle to close before lock creation
		if (bomblock[0] == null)
			return;

		var list = new ListCloner().deepCloneByClass(LAYER.PUZZLE.getHandler(), BombLockObject.class);

		for (BombLockObject lock : list.getList())
			if (lock.isOpened() == false)
				return;

		// happens when all lock are opened
		closePuzzle(true);

	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 1;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color green = new Color(15, 45, 10, 240);
		new TransluantLayer().drawPuzzle(g, green);
	}

}
