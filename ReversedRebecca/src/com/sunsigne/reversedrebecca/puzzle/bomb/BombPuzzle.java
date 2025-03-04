package com.sunsigne.reversedrebecca.puzzle.bomb;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.BigBombObject;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.BombObject;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.BulletBombObject;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.DuplicatingBombObject;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.PointerBombObject;
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
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public abstract class BombPuzzle extends Puzzle {

	public BombPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		super(toolPlayer, actionOnWinning);
		new GameCursor().setCursor(CURSOR_TYPE.POINTER);

		LAYER.PUZZLE.addObject(new PointerBombObject(this, isCritical));
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "bomb";
	}

	////////// FACTORY ////////////

	@Override
	public PuzzleFactory getFactory() {
		return new BombPuzzleFactory();
	}

	////////// PUZZLE ////////////

	private BombObject[] bomb = new BombObject[getBombAmount()];

	public abstract BombObject getBomb(Puzzle puzzle, boolean critical, int x, int y);

	public abstract int getBombAmount(); // 3, 4 or 6

	private int getColGap() {
		switch (getBombAmount()) {
		case 3:
			return Size.S;
		case 4:
			return 0;
		case 6:
			return -Size.L - Size.S / 4;
		}

		return 0;
	}

	protected void createBombs() {
		int radCrit = isCritical ? new RandomGenerator().getIntBetween(0, getBombAmount() - 1) : -1;
		int colGap = getColGap();
		int maxRow = getBomb(this, false, 0, 0) instanceof BigBombObject ? 3 : 4;

		for (int index = 0; index < getBombAmount(); index++) {
			int col = Size.S + colGap * index + getCol(1 + index * 3);
			int radRow = Size.S + getRow(new RandomGenerator().getIntBetween(1, maxRow));

			bomb[index] = getBomb(this, index == radCrit, col, radRow);
			LAYER.PUZZLE.addObject(bomb[index]);
		}
	}

	private int bonusBullet;

	protected void createBullets(int num) {
		BulletBombObject bullets = new BulletBombObject(this, bonusBullet + num, isCritical);
		LAYER.PUZZLE.addObject(bullets);
	}

	protected void setRandomMaxCountBetween(int a, int b) {
		for (int index = 0; index < getBombAmount(); index++) {
			int count = new RandomGenerator().getIntBetween(a, b);
			bonusBullet = bonusBullet + count - 1;
			bomb[index].setMaxCount(count);
			bomb[index].setCount(count);

			if (bomb[index] instanceof DuplicatingBombObject)
				bonusBullet = bonusBullet + ((DuplicatingBombObject) bomb[index]).getBonusBullet();
		}
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		// prevent puzzle to close before bomb creation
		if (bomb[0] == null)
			return;

		var list = new ListCloner().deepClone(LAYER.PUZZLE.getHandler());

		for (Updatable tempUpdatable : list.getList()) {
			if (tempUpdatable instanceof BombObject == false)
				continue;

			BombObject bomb = (BombObject) tempUpdatable;
			if (bomb.hasExploded() == false)
				return;
		}

		// happens when all bombs has exploded
		closePuzzle(true);
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 2;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color red = new Color(50, 10, 10, 240);
		new TransluantLayer().drawPuzzle(g, red);
	}

}
