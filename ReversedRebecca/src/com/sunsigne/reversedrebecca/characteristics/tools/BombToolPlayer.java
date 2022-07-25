package com.sunsigne.reversedrebecca.characteristics.tools;

public class BombToolPlayer extends ToolPlayer {

	public BombToolPlayer() {

	}

	////////// TOOL ////////////

	private static ToolPlayer instance = new BombToolPlayer();

	@Override
	protected ToolPlayer getInstance() {
		return instance;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "bomb";
	}

}
