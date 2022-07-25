package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolList;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

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

		if (getState())
			return;

		world = null;
		ToolList.getList().getList().forEach(tempTool -> setToolToDifficulty(tempTool, LVL.NULL));
	}

	private void setToolToDifficulty(ToolPlayer tool, LVL difficulty) {
		if (difficulty != LVL.NULL)
			tool.setMaxDifficulty(difficulty);
		tool.setDifficulty(difficulty);
	}

	private World world;

	@Override
	public void tick(Updatable object) {

		// doesn't update if desactivate
		if (getState() == false)
			return;

		// doesn't update if no world
		if (World.get() == null)
			return;

		// doesn't update if already did in current world
		if (World.get() == world)
			return;

		world = World.get();
		ToolList.getList().getList().forEach(tempTool -> setToolToDifficulty(tempTool, LVL.RED));
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
