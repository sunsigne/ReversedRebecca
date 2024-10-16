package com.sunsigne.reversedrebecca.object.puzzler;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.animation.SuperAnimationObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.piranha.condition.global.WonPuzzleCondition;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.Key;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public abstract class OpenPuzzleAction extends Action {

	public OpenPuzzleAction(PuzzlerObject puzzlerObject) {
		super(puzzlerObject, null, null, null, null, 0);

		setName(new Translatable().getTranslatedText(getName(), FilePath.PUZZLER));
		setToolPlayer(getToolPlayer());
		setListener(() -> {
			Puzzle puzzle = getPuzzle(puzzlerObject.getDevDifficulty(), puzzlerObject.getDifficulty(), getToolPlayer(),
					actionOnWinning(puzzlerObject));
			if (puzzle != null)
				puzzle.openPuzzle();
		});
		setKeyEvent(getKey(), getKeyEvent());
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// TOOL ////////////

	public abstract ToolPlayer getToolPlayer();

	////////// PUZZLE ////////////

	public abstract Puzzle getPuzzle(DEV_LVL devDifficulty, LVL difficulty, ToolPlayer toolPlayer,
			GenericListener actionOnWinning);

	public abstract PuzzlerObject getNullObject(PuzzlerObject puzzlerObject, int x, int y);

	public abstract SuperAnimationObject getAnimationObject(PuzzlerObject puzzlerObject, int x, int y);
	
	protected GenericListener actionOnWinning(PuzzlerObject puzzlerObject) {

		GenericListener actionOnWinning = () -> {
			PuzzlerObject nullObject = getNullObject(puzzlerObject, puzzlerObject.getX(), puzzlerObject.getY());
			Handler handler = puzzlerObject.getHandler();

			if (handler != null) {
				if (nullObject != null)
					handler.addObject(nullObject);
				handler.removeObject(puzzlerObject);
				Game.getInstance().forceLoop();
			}

			SuperAnimationObject animation = getAnimationObject(puzzlerObject, puzzlerObject.getX(), puzzlerObject.getY());
			LAYER.WORLD_TEXT.addObject(animation);

			new WonPuzzleCondition().registerValue(puzzlerObject);
		};
		return actionOnWinning;
	}

	////////// KEYBOARD ////////////

	public abstract Key getKey();

	public abstract int getKeyEvent();

}
