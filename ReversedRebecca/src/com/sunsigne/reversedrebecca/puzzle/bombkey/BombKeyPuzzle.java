package com.sunsigne.reversedrebecca.puzzle.bombkey;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.PointerBombObject;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.bombs.BigBombObject;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.bombs.BombObject;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.BombKeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.PointerKeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.locks.BombLockObject;
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

public abstract class BombKeyPuzzle extends Puzzle {

	public BombKeyPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning, GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
		new GameCursor().setCursor(CURSOR_TYPE.POINTER);

		LAYER.PUZZLE.addObject(new PointerKeyObject(this, isCritical));
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

	public abstract BombLockObject getBombLock(Puzzle puzzle, boolean critical, int x, int y);

	public abstract int getBombLockAmount(); // 3, 4 or 6

	private int getColGap() {
		switch (getBombLockAmount()) {
		case 3:
			return Size.S;
		case 4:
			return 0;
		case 6:
			return -Size.L - Size.S / 4;
		}

		return 0;
	}

	protected void createBombKey() {
		BombKeyObject bombkey = new BombKeyObject(this, isCritical, 0, 0);
		bombkey.setX(getCol(5) );
		bombkey.setY(getCol(1) + Size.M);
		LAYER.PUZZLE.addObject(bombkey);
	}

	protected void createBombLocks() {
		for (int index = 0; index < getBombLockAmount(); index++) {
			int radCol = Size.M + getCol(4 + new RandomGenerator().getIntBetween(1, 2));
			int radRow = Size.S / 3 + getRow(2 + new RandomGenerator().getIntBetween(1, 2));

			bomblock[index] = getBombLock(this, isCritical, radCol, radRow);
			LAYER.PUZZLE.addObject(bomblock[index]);
		}
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		/*
		// prevent puzzle to close before bomb creation
		if (bomblock[0] == null)
			return;

		var list = new ListCloner().deepCloneByClass(LAYER.PUZZLE.getHandler(), BombObject.class);

		for (BombObject bomb : list.getList())
			if (bomb.hasExploded() == false)
				return;

		// happens when all bombs has exploded
		closePuzzle(true);
		*/
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
