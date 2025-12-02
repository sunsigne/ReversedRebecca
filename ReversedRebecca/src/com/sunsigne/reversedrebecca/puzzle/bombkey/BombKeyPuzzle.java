package com.sunsigne.reversedrebecca.puzzle.bombkey;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.BigBombObject;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.BombObject;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.BulletBombObject;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.DuplicatingBombObject;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.PointerBombObject;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.BombKeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.cowboy.CowboyTargetObject;
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

public abstract class BombKeyPuzzle extends Puzzle {

	public BombKeyPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning, GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
		new GameCursor().setCursor(CURSOR_TYPE.POINTER);

		LAYER.PUZZLE.addObject(new PointerBombObject(this, isCritical));
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

	private BombObject[] bomblock = new BombObject[getBombLockAmount()];

	public abstract BombObject getBombLock(Puzzle puzzle, boolean critical, int x, int y);

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
		bombkey.setX(getCol(4) + Size.S);
		bombkey.setY(getCol(1));
		LAYER.PUZZLE.addObject(bombkey);
	}
	
	protected void createBombLocks() {
		
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		// prevent puzzle to close before bomb creation
		if (bomblock[0] == null)
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
		return 1;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color red = new Color(50, 10, 10, 240);
		new TransluantLayer().drawPuzzle(g, red);
	}

}
