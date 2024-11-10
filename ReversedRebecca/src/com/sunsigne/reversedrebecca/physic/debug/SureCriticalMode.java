package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolList;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class SureCriticalMode extends DebugMode {

	////////// DEBUG MODE ////////////

	private static DebugMode debugMode = new SureCriticalMode();

	@Override
	public DebugMode getDebugMode() {
		return debugMode;
	}

	////////// NAME ////////////

	@Override
	public int getNum() {
		return 7;
	}

	@Override
	public String getName() {
		return "debugmode_sure_critical";
	}

	////////// TICK ////////////

	@Override
	public void cycle() {
		super.cycle();

		if (getState())
			return;

		world = null;
		ToolList.getList().getList().forEach(tempTool -> setToolToCriticalChance(tempTool, 0));
	}

	private void setToolToCriticalChance(ToolPlayer tool, int chance) {
		tool.setCriticalChance(chance);
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
			if (world.getFrame() != 1)
				return;

		world = World.get();
		ToolList.getList().getList().forEach(tempTool -> setToolToCriticalChance(tempTool, 100));
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
