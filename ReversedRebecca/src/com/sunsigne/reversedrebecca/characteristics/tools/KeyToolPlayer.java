package com.sunsigne.reversedrebecca.characteristics.tools;

public class KeyToolPlayer extends ToolPlayer {

	public KeyToolPlayer() {

	}

	////////// TOOL ////////////

	private static ToolPlayer instance = new KeyToolPlayer();

	@Override
	protected ToolPlayer getInstance() {
		return instance;
	}

	////////// NAME ////////////
	
	@Override
	public int getNum() {
		return 1;
	}
	
	@Override
	public String getName() {
		return "key";
	}

}
