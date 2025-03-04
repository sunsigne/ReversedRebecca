package com.sunsigne.reversedrebecca.puzzle;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraShaker;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor.CURSOR_TYPE;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public abstract class Puzzle implements Updatable, TickFree, SheetableImage {

	public Puzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		loadToolData(toolPlayer);
		this.actionOnWinning = actionOnWinning;

		if (ControllerManager.getInstance().isUsingGamepad())
			new PresetMousePos(Window.WIDHT / 2, Window.HEIGHT / 2).moveMouse();
	}

	////////// USEFULL ////////////

	public int getCol(int col) {
		return 2 * Size.XS + col * Size.L;
	}

	public int getRow(int row) {
		return Size.XS + row * Size.L;
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// FACTORY ////////////

	public abstract PuzzleFactory getFactory();

	////////// PUZZLE ////////////

	public abstract void createPuzzle();

	////////// TOOL ////////////

	protected boolean isCritical;

	private void loadToolData(ToolPlayer toolPlayer) {

		isCritical = false;
		if (toolPlayer != null)
			isCritical = new RandomGenerator().getBoolean(toolPlayer.getCriticalChance());
	}

	////////// OPEN ////////////

	public void openPuzzle() {
		World world = World.get();
		if (world != null)
			world.freeze(true);

		// added as first element to render behind objects
		LAYER.PUZZLE.getHandler().getList().add(0, this);
		Handler.updateHandlerMap(LAYER.PUZZLE.getHandler(), this);
		
		createWallBorder();
		createPuzzle();

		new SoundTask().playSound(SOUNDTYPE.SOUND, getFactory().getOpeningSound());
	}
	
	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE;
	}

	////////// TEXTURE ////////////
	
	@Override
	public int getSheetSize() {
		return 2*16;
	}
	
	@Override
	public int getSheetRowCriterion() {
		return 1;
	}
	
	protected BufferedImage getWallTexture() {
		BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "wall");
		return getSheetSubImage(sheet);
	}

	protected void createWallBorder() {

		Handler handler = LAYER.PUZZLE.getHandler();
		BufferedImage image = getWallTexture();

		for (int col = 0; col < 14; col++) {
			handler.addObject(new WallPuzzle(image, getCol(col), getRow(0)));
			handler.addObject(new WallPuzzle(image, getCol(col), getRow(7)));
		}
		for (int row = 0; row < 8; row++) {
			handler.addObject(new WallPuzzle(image, getCol(0), getRow(row)));
			handler.addObject(new WallPuzzle(image, getCol(13), getRow(row)));
		}
	}

	////////// CLOSE ////////////

	private GenericListener actionOnWinning;

	public void closePuzzle(boolean isPuzzleWon) {
		World world = World.get();
		if (world != null)
			world.freeze(false);

		LAYER.PUZZLE.getHandler().clear();
		new GameCursor().setCursor(CURSOR_TYPE.NORMAL);
		new PlayerFinder().roundToTilePlayer();

		if (isPuzzleWon) {
			new CameraShaker().shaking(getFactory().getVictoryShake());
			new SoundTask().playSound(SOUNDTYPE.SOUND, getFactory().getVictorySound());
			actionOnWinning.doAction();
		} else
			new SoundTask().playSound(SOUNDTYPE.SOUND, "fail");
	}

}
