package com.sunsigne.reversedrebecca.characteristics.tools;

public class CowboyToolPlayer extends ToolPlayer {

	public CowboyToolPlayer() {

	}

	////////// TOOL ////////////

	private static ToolPlayer instance = new CowboyToolPlayer();

	@Override
	protected ToolPlayer getInstance() {
		return instance;
	}

	////////// NAME ////////////

	@Override
	public int getNum() {
		return -1;
	}

	@Override
	public String getName() {
		return "cowboy";
	}

	////////// DIFFICULTY ////////////

	///// max /////

	@Override
	protected String getDefaultMaxDifficulty() {
		return LVL.RED.getName().toUpperCase();
	}

	///// start /////

	@Override
	protected String getDefaultStartDifficulty() {
		return LVL.RED.getName().toUpperCase();
	}

}
