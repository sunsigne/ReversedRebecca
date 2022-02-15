package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayerList;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class MultiToolMode extends DebugMode {

	////////// PHYSIC LAW ////////////

	private static PhysicLaw physicLaw = new MultiToolMode();

	@Override
	public PhysicLaw getPhysicLaw() {
		return physicLaw;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "debugmode_multitool";
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (!getState())
			return;

		if (ToolPlayerList.getList().getList().isEmpty())
			return;

		ToolPlayer tool = ToolPlayerList.getList().getList().get(0);

		// one tool is enough, no need to check for all
		if (tool.getDifficulty() != LVL.NULL)
			return;

		ToolPlayerList.getList().getList().forEach(tempTool -> setToolToDifficulty(tempTool, LVL.RED));
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

	////////// KEYBOARD ////////////

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == getKeyEvent()) {
			cycle();

			if (getState())
				ToolPlayerList.getList().getList().forEach(tempTool -> setToolToDifficulty(tempTool, LVL.RED));
			else
				ToolPlayerList.getList().getList().forEach(tempTool -> setToolToDifficulty(tempTool, LVL.NULL));

		}
	}

	private void setToolToDifficulty(ToolPlayer tool, LVL difficulty) {

		if (difficulty != LVL.NULL)
			tool.setMaxDifficulty(difficulty);
		tool.setDifficulty(difficulty);
	}

}
