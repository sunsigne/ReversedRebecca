package com.sunsigne.reversedrebecca.characteristics.tools;

public class AlcoholToolPlayer extends ToolPlayer {

	public AlcoholToolPlayer() {

	}

	////////// TOOL ////////////

	private static ToolPlayer instance = new AlcoholToolPlayer();

	@Override
	protected ToolPlayer getInstance() {
		return instance;
	}

	////////// NAME ////////////

	@Override
	public int getNum() {
		return 5;
	}

	@Override
	public String getName() {
		return "alcohol";
	}

	////////// DIFFICULTY ////////////

	///// max /////

	@Override
	protected String getDefaultMaxDifficulty() {
		return LVL.YELLOW.getName().toUpperCase();
	}

	///// current /////

	@Override
	public void setDifficulty(LVL difficulty) {
		setDifficulty(difficulty, true);
	}

}
