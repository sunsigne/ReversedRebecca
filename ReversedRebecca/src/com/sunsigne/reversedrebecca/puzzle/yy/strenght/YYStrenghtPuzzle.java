package com.sunsigne.reversedrebecca.puzzle.yy.strenght;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.yy.strenght.StrenghtLauncherObject;
import com.sunsigne.reversedrebecca.object.puzzle.yy.strenght.StrenghtPlayerObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;

public abstract class YYStrenghtPuzzle extends Puzzle {

	public YYStrenghtPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning, GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
		new GameCursor().setCursor(null);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "yy_strenght";
	}

	////////// FACTORY ////////////

	@Override
	public PuzzleFactory getFactory() {
		return new YYStrenghtPuzzleFactory();
	}

	////////// PUZZLE ////////////

	public abstract int getSpeed();

	public abstract StrenghtPlayerObject getPlayer();

	public abstract StrenghtLauncherObject getLauncher();

	protected void createPlayer() {
		StrenghtPlayerObject player = getPlayer();
		player.setX(getCol(10));
		player.setY(getRow(4) + Size.S);

		LAYER.PUZZLE.addObject(player);
	}

	protected void createLauncher() {
		StrenghtLauncherObject launcher = getLauncher();
		launcher.setX(getCol(2) - Size.S);
		launcher.setY(getRow(3) + Size.S);

		LAYER.PUZZLE.addObject(launcher);
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 1;
	}

	@Override
	public int getSheetRowCriterion() {
		return 3;
	}

	@Override
	protected BufferedImage getWallTexture() {
		BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "wall");
		return getSheetSubImage(sheet, getSheetColCriterion(), getSheetRowCriterion(), getSheetWidth(),
				getSheetHeight());

	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color green = new Color(85, 100, 75, 240);
		new TransluantLayer().drawPuzzle(g, green);
	}

}
