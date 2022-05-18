package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.Graphics;
import java.util.List;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayerList;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class MultiToolMode extends DebugMode {

	////////// DEBUG MODE ////////////

	private static DebugMode debugMode = new MultiToolMode();

	@Override
	public DebugMode getDebugMode() {
		return debugMode;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "debugmode_multitool";
	}

	////////// TICK ////////////

	@Override
	public void cycle() {
		super.cycle();

		if (getState() == false)
			ToolPlayerList.getList().getList().forEach(tempTool -> setToolToDifficulty(tempTool, LVL.NULL));
	}

	private void setToolToDifficulty(ToolPlayer tool, LVL difficulty) {
		if (difficulty != LVL.NULL)
			tool.setMaxDifficulty(difficulty);
		tool.setDifficulty(difficulty);
	}

	@Override
	public void tick(Updatable object) {
		if (getState() == false)
			return;

		if (ToolPlayerList.getList().getList().isEmpty())
			return;

		List<ToolPlayer> list = ToolPlayerList.getList().getList();

		ToolPlayer tool = list.get(list.size() - 1);

		// last tool is enough, no need to check for all
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

}
