package com.sunsigne.reversedrebecca.object.puzzler.hole;

import com.sunsigne.reversedrebecca.characteristics.tools.ShovelToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.hole.downward.HoleObject;
import com.sunsigne.reversedrebecca.object.puzzler.hole.downward.NullHoleObject;
import com.sunsigne.reversedrebecca.object.puzzler.hole.upward.HoleUpwardObject;
import com.sunsigne.reversedrebecca.object.puzzler.hole.upward.NullHoleUpwardObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.piranha.condition.global.WonPuzzleCondition;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.DigPuzzleFactory;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class DigAction extends OpenPuzzleAction {

	public DigAction(PuzzlerObject puzzlerObject) {
		super(puzzlerObject);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "HoleDig";
	}

	////////// TOOL ////////////

	@Override
	public ToolPlayer getToolPlayer() {
		return new ShovelToolPlayer();
	}

	////////// PUZZLE ////////////

	@Override
	public Puzzle getPuzzle(DEV_LVL devDifficulty, LVL difficulty, ToolPlayer toolPlayer,
			GenericListener actionOnWinning) {
		return new DigPuzzleFactory().createPuzzle(devDifficulty, difficulty, toolPlayer, actionOnWinning);
	}

	@Override
	public PuzzlerObject getNullObject(PuzzlerObject puzzlerObject, int x, int y) {
		DIRECTION facing = ((HoleObject) puzzlerObject).getFacing();

		if (puzzlerObject instanceof HoleUpwardObject)
			return new NullHoleUpwardObject(facing, x, y);
		else
			return new NullHoleObject(facing, x, y);
	}

	@Override
	protected GenericListener actionOnWinning(PuzzlerObject puzzlerObject) {

		GenericListener actionOnWinning = () -> {
			PuzzlerObject nullObject = getNullObject(puzzlerObject, puzzlerObject.getX(), puzzlerObject.getY());
			Handler handler = puzzlerObject.getHandler();

			handler.addObject(nullObject);
			handler.removeObject(puzzlerObject);
			triggerExit(handler, puzzlerObject);

			new WonPuzzleCondition().registerValue(puzzlerObject);
		};
		return actionOnWinning;
	}

	private void triggerExit(Handler handler, PuzzlerObject puzzlerObject) {
		Handler exit_handler = ((HoleObject) puzzlerObject).getExitLayer(handler).getHandler();
		GameList<GameObject> list = Handler.getObjectsAtPos(exit_handler, puzzlerObject.getX(), puzzlerObject.getY(),
				puzzlerObject.getSize(), true);

		if (list.getList().isEmpty())
			return;

		for (GameObject tempObject : list.getList()) {
			if (tempObject instanceof HoleObject == false)
				continue;

			if (tempObject instanceof NullHoleObject || tempObject instanceof NullHoleUpwardObject)
				continue;

			HoleObject hole = (HoleObject) tempObject;
			Action action = hole.getTripleAction().getAction(0);
			DigAction digAction = (DigAction) action;
			GenericListener listener = digAction.actionOnWinning(hole);
			listener.doAction();
		}
	}

	////////// KEYBOARD ////////////

	@Override
	public int getKeyEvent() {
		return ActionOneKey.getKey();
	}

}
