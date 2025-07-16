package com.sunsigne.reversedrebecca.puzzle.cowboy.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.cowboy.CowboyBadGuy;
import com.sunsigne.reversedrebecca.object.puzzle.cowboy.CowboyCursorObject;
import com.sunsigne.reversedrebecca.object.puzzle.cowboy.CowboyTarget;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.cowboy.CowboyPuzzle;

public class CyanCowboyPuzzle extends CowboyPuzzle {

	public CyanCowboyPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning, GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
	}

	////////// PUZZLE ////////////

	@Override
	public CowboyTarget getTarget() {
		return new CowboyTarget(this, isCritical);
	}

	@Override
	public CowboyBadGuy getBadGuy() {
		return new CowboyBadGuy(this, isCritical);
	}

	@Override
	public CowboyCursorObject getCursor() {
		return new CowboyCursorObject(this, isCritical);
	}

	@Override
	public void createPuzzle() {
		createTarget();
		createBadGuy();
		createCursor();
	}

}
