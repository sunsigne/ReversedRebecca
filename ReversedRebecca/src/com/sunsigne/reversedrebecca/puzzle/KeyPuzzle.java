package com.sunsigne.reversedrebecca.puzzle;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;

public class KeyPuzzle extends Puzzle {

	public KeyPuzzle(LVL difficulty, GenericListener actionOnWinning) {
		super(difficulty, actionOnWinning);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "key";
	}
	
	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color green = new Color(15, 45, 10, 240);
		g.setColor(green);
		g.fillRect(Size.L, Size.L, Window.WIDHT - 2 * Size.L, Window.HEIGHT - 2 * Size.L);
	}

}
