package com.sunsigne.reversedrebecca.puzzle.key;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.KillPuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.object.puzzle.key.MovingWallPuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.key.KeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.lock.LockObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.world.World;

public abstract class KeyPuzzle extends Puzzle {

	public KeyPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		super(toolPlayer, actionOnWinning);
		new GameCursor().setCursor(null);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "key";
	}

	////////// FACTORY ////////////

	@Override
	public PuzzleFactory getFactory() {
		return new KeyPuzzleFactory();
	}

	////////// PUZZLE ////////////

	public abstract LockObject getLock();

	public abstract KeyObject getKey();

	protected void createLock() {
		PuzzleObject lock = getLock();
		lock.setX(getCol(1));
		lock.setY(getRow(4));

		LAYER.PUZZLE.addObject(lock);
	}

	protected void createKey() {
		PuzzleObject key = getKey();
		key.setX(getCol(12));
		key.setY(getRow(new RandomGenerator().getIntBetween(1, 6)));

		LAYER.PUZZLE.addObject(key);
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 1;
	}

	@Override
	protected BufferedImage getWallTexture() {
		BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "wall");
		int row = isCritical ? 2 : 1;
		return getSheetSubImage(sheet, getSheetColCriterion(), row, getSheetWidth(), getSheetHeight());

	}

	protected void createRandomWalls(int numOfWalls, boolean moving) {
		if (numOfWalls <= 0)
			return;

		RandomGenerator rad = new RandomGenerator();
		Handler handler = LAYER.PUZZLE.getHandler();
		BufferedImage img = getWallTexture();
		int safeRow = getRow(rad.getIntBetween(1, 6));

		int count = 0;

		while (count < numOfWalls) {
			count++;

			int radCol = getCol(rad.getIntBetween(2, 11));
			int radRow;
			do {
				radRow = getRow(rad.getIntBetween(1, 6));
				if (isTutorial())
					radRow = rad.getBoolean() ? getRow(1) : getRow(6);
			} while (radRow == safeRow);

			if (moving)
				handler.addObject(new MovingWallPuzzleObject(this, img, radCol, radRow));

			else {
				handler.addObject(new WallPuzzle(img, radCol, radRow));
				handler.addObject(new KillPuzzleObject(this, radCol, radRow));
			}
		}
	}

	////////// OPEN ////////////

	@Override
	protected void createWallBorder() {
		super.createWallBorder();
		createDeathWall();
	}

	private void createDeathWall() {
		for (int row = 1; row <= 6; row++) {
			// Almost invisible in VisibleHitboxMode because added before Walls
			LAYER.PUZZLE.addObject(new KillPuzzleObject(this, getCol(0), getRow(row)));
		}
	}

	////////// CLOSE ////////////

	@Override
	public void closePuzzle(boolean isPuzzleWon) {
		super.closePuzzle(isPuzzleWon);

		if (isPuzzleWon && mouseDisplayed == false)
			mouseDisplayed = true;
	}

	////////// RENDER ////////////

	private static boolean mouseDisplayed;

	@Override
	public void render(Graphics g) {
		Color green = new Color(15, 45, 10, 240);
		new TransluantLayer().drawPuzzle(g, green);

		if (isTutorial() == false || mouseDisplayed)
			return;

		BufferedImage image = new ImageTask().loadImage("textures/puzzle/" + "mouse_tuto");
		int size = 150;

		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		g2d.drawImage(image, getCol(4) + Size.XS, getRow(3) - Size.XS / 2, 5 * size, 2 * size, null);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

	private boolean isTutorial() {
		World world = World.get();

		if (world == null)
			return false;

		if (world.getMapName().equalsIgnoreCase(FilePath.TUTORIAL))
			return true;

		if (world.getMapName().equalsIgnoreCase(FilePath.LVL000))
			return true;

		return false;
	}

}
