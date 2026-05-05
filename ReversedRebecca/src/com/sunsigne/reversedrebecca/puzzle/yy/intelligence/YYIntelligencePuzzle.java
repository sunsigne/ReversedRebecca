package com.sunsigne.reversedrebecca.puzzle.yy.intelligence;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.object.puzzle.yy.intelligence.IntelligenceChessBoardObject;
import com.sunsigne.reversedrebecca.object.puzzle.yy.intelligence.IntelligenceLauncherObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
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

	private IntelligenceChessBoardObject chessBoard;

	protected void createLauncher() {
		IntelligenceLauncherObject launcher = new IntelligenceLauncherObject(this);
		launcher.setX(getCol(2) - Size.S);
		launcher.setY(getRow(2) + Size.S);

		LAYER.PUZZLE.addObject(launcher);
	}

	protected void createChessBoard() {
		chessBoard = new IntelligenceChessBoardObject(this);
		chessBoard.setX(getCol(4) + getCol(1) / 2);
		chessBoard.setY(getRow(1));

		LAYER.PUZZLE.addObject(chessBoard);
		chessBoard.resetPieces();
	}

	////////// TICK ////////////

	private int WIN_CONDIITON_TIME = 15 * Game.SEC;
	private int time;

	public int getLoopTime() {
		return 53;
	}

	private int loop = getLoopTime();

	@Override
	public void tick() {
		if (new PlayerFinder().getPlayer().isDead())
			return;

		time++;

		WIN_CONDIITON_TIME--;
		if (WIN_CONDIITON_TIME <= 0)
			closePuzzle(false);

		if (time == loop) {
			chessBoard.play();
			time = 0;
		}
	}

	////////// TEXTURE ////////////

	private boolean wallSwap;

	@Override
	public int getSheetColCriterion() {
		return wallSwap ? 2 : 3;
	}

	@Override
	public int getSheetRowCriterion() {
		return 3;
	}

	@Override
	protected void createWallBorder() {
		Handler handler = LAYER.PUZZLE.getHandler();

		for (int col = 0; col < 14; col++)
			createWall(handler, col, 0);

		for (int row = 1; row < 7; row++)
			createWall(handler, 13, row);

		for (int col = 13; col > -1; col--)
			createWall(handler, col, 7);

		for (int row = 6; row > 0; row--)
			createWall(handler, 0, row);
	}

	private void createWall(Handler handler, int col, int row) {
		wallSwap = !wallSwap;
		handler.addObject(new WallPuzzle(getWallTexture(), getCol(col), getRow(row)));
	}

	private BufferedImage playerImage;

	private BufferedImage getPlayerImage() {
		if (playerImage == null) {
			Player player = new PlayerFinder().getPlayer();
			String name = player != null ? player.getTextureName() : "rebecca";
			BufferedImage sheet = new ImageTask().loadImage("textures/characters/" + name + "/world");
			playerImage = getSheetSubImage(sheet, 1, 1, 16, 16);
		}

		return playerImage;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color white = new Color(150, 150, 150, 240);
		new TransluantLayer().drawPuzzle(g, white);

		if (WIN_CONDIITON_TIME > Game.SEC / 2)
			g.drawImage(getPlayerImage(), (getCol(10) + getCol(1) / 2), getRow(3), 2 * Size.L, 2 * Size.L, null);
	}

}
