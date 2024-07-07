package com.sunsigne.reversedrebecca.characteristics.tools;

public class ShovelToolPlayer extends ToolPlayer {

	public ShovelToolPlayer() {
		
	}

	////////// TOOL ////////////

	private static ToolPlayer instance = new ShovelToolPlayer();

	@Override
	protected ToolPlayer getInstance() {
		return instance;
	}

	////////// NAME ////////////
	
	@Override
	public int getNum() {
		return 4;
	}
	
	@Override
	public String getName() {
		return "shovel";
	}

}
