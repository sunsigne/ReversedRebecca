package com.sunsigne.reversedrebecca.characteristics.tools;

public class MicrochipToolPlayer extends ToolPlayer {

	public MicrochipToolPlayer() {
		
	}

	////////// TOOL ////////////

	private static ToolPlayer instance = new MicrochipToolPlayer();

	@Override
	protected ToolPlayer getInstance() {
		return instance;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "microchip";
	}

}
