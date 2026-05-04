package com.sunsigne.reversedrebecca.puzzle.yy.intelligence;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.object.puzzle.yy.strenght.StrenghtPlayerObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public abstract class YYIntelligencePuzzle extends Puzzle {

	public YYIntelligencePuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning,
			GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
		new GameCursor().setCursor(null);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "yy_intelligence";
	}

	////////// FACTORY ////////////

	@Override
	public PuzzleFactory getFactory() {
		return new YYIntelligencePuzzleFactory();
	}

	////////// PUZZLE ////////////

	public abstract StrenghtPlayerObject getPlayer();

	protected void createPlayer() {
		/*
		 * StrenghtPlayerObject player = getPlayer(); player.setX(getCol(10));
		 * player.setY(getRow(4) + 3 * Size.XS);
		 * 
		 * LAYER.PUZZLE.addObject(player);
		 */
	}

	////////// TICK ////////////

	private int WIN_CONDIITON_TIME = 14 * Game.SEC;
	private int time;
	private int loop = 54;

	@Override
	public void tick() {
		if (new PlayerFinder().getPlayer().isDead())
			return;

		time++;

		WIN_CONDIITON_TIME--;
		if (WIN_CONDIITON_TIME <= 0)
			closePuzzle(true);

		if (time == loop / 2) {
			new SoundTask().playSound(SOUNDTYPE.SOUND, "chest");
		}

		if (time == loop) {
			time = 0;
		}
	}

	////////// TEXTURE ////////////

	private boolean wallSwap;

	@Override
	public int getSheetColCriterion() {
		wallSwap = !wallSwap;
		return wallSwap ? 2 : 3;
	}

	@Override
	public int getSheetRowCriterion() {
		return 3;
	}

	@Override
	protected void createWallBorder() {
		Handler handler = LAYER.PUZZLE.getHandler();

		// must be called manually (i.e outside of a loop)
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(0), getRow(0)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(1), getRow(0)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(2), getRow(0)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(3), getRow(0)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(4), getRow(0)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(5), getRow(0)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(6), getRow(0)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(7), getRow(0)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(8), getRow(0)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(9), getRow(0)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(10), getRow(0)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(11), getRow(0)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(12), getRow(0)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(13), getRow(0)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(13), getRow(1)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(13), getRow(2)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(13), getRow(3)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(13), getRow(4)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(13), getRow(5)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(13), getRow(6)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(13), getRow(7)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(12), getRow(7)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(11), getRow(7)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(10), getRow(7)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(9), getRow(7)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(8), getRow(7)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(7), getRow(7)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(6), getRow(7)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(5), getRow(7)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(4), getRow(7)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(3), getRow(7)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(2), getRow(7)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(1), getRow(7)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(0), getRow(7)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(0), getRow(6)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(0), getRow(5)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(0), getRow(4)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(0), getRow(3)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(0), getRow(2)));
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(0), getRow(1)));
	}

	private BufferedImage chessImage;

	private BufferedImage getChessImage() {
		if (chessImage == null)
			chessImage = new ImageTask().loadImage("textures/puzzle/" + "chess");
		return chessImage;
	}

	private BufferedImage playerImage;

	private BufferedImage getPlayerImage() {
		if (playerImage == null) {
			Player player = new PlayerFinder().getPlayer();
			String name = player != null ? player.getTextureName() : "rebecca";
			BufferedImage sheet = new ImageTask().loadImage("textures/characters/" + name);
			playerImage = getSheetSubImage(sheet, 1, 1, getSheetWidth(), getSheetHeight());
		}

		return playerImage;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color white = new Color(150, 150, 150, 240);
		new TransluantLayer().drawPuzzle(g, white);

		g.drawImage(getChessImage(), getCol(4), getRow(1), 8 * Size.M, 8 * Size.M, null);
		g.drawImage(getPlayerImage(), getCol(10), getRow(4) + 3 * Size.XS, 2 * Size.L, 2 * Size.L, null);
	}

}
