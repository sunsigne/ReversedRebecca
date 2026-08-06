package com.sunsigne.reversedrebecca.object.puzzle.disco;

import com.sunsigne.reversedrebecca.object.characteristics.Velocity;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public interface DiscoSwitchableSide extends Velocity {

	default int getSpeed() {
		return 4;
	}

	default void switchSide() {
		int factor = getX() < Window.WIDHT / 2 ? 1 : -1;
		setVelX(factor * getSpeed());
		new GameTimer(4 * Game.SEC, true, () -> setVelX(0));
	}

}
