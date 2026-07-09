package com.sunsigne.reversedrebecca.puzzle;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.CritToken;
import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.physic.debug.SureCriticalMode;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraShaker;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraShaker.SHAKE;
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

	public Puzzle(ToolPlayer toolPlayer, GenericListenerBoolean actionOnWinning, GenericListener actionOnLosing) {
		criticalChance = calculingCriticalChances(toolPlayer);
		if (criticalChance >= 99) {
			isCritical = true;
			setStaticNoCritCount(0);
		}

		this.actionOnWinning = actionOnWinning;
		this.actionOnLosing = actionOnLosing;

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

	protected abstract int getStaticNoCritCount();

	protected abstract void setStaticNoCritCount(int noCritCount);

	public abstract boolean hasCritToken();

	private int criticalChance;

	private int calculingCriticalChances(ToolPlayer toolPlayer) {
		if (SureCriticalMode.debugMode.getDebugMode().getState())
			return 100;
		if (isCritical)
			return 100;
		if (toolPlayer == null)
			return 0;
		if (toolPlayer.getCriticalChance() >= 100)
			return 100;

		return (1 + getStaticNoCritCount()) * toolPlayer.getCriticalChance();
	}

	private void createCriticalToken() {
		if (hasCritToken() == false)
			return;

		if (criticalChance >= 99)
			criticalChance = 100;
		if (criticalChance < 0)
			criticalChance = 0;

		CritToken token = new CritToken(this, criticalChance);
		LAYER.PUZZLE.addObject(token);
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
		createCriticalToken();
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
		return 2 * 16;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	protected BufferedImage getWallTexture() {
		return getWallTexture(false);
	}

	protected BufferedImage getWallTexture(boolean critToken) {
		String crit = critToken && hasCritToken() ? "_crit" : "";
		BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "wall" + crit);
		return getSheetSubImage(sheet);
	}

	protected void createWallBorder() {

		Handler handler = LAYER.PUZZLE.getHandler();
		BufferedImage image = getWallTexture();

		for (int col = 0; col < 14; col++) {
			handler.addObject(new WallPuzzle(image, getCol(col), getRow(0)));
			handler.addObject(new WallPuzzle(col != 13 ? image : getWallTexture(true), getCol(col), getRow(7)));
		}
		for (int row = 1; row < 7; row++) {
			handler.addObject(new WallPuzzle(image, getCol(0), getRow(row)));
			handler.addObject(new WallPuzzle(image, getCol(13), getRow(row)));
		}
	}

	////////// CLOSE ////////////

	private GenericListenerBoolean actionOnWinning;
	private GenericListener actionOnLosing;

	public void closePuzzle(boolean isPuzzleWon) {
		World world = World.get();
		if (world != null)
			world.freeze(false);

		LAYER.PUZZLE.getHandler().clear();
		new GameCursor().setCursor(CURSOR_TYPE.NORMAL);
		new PlayerFinder().roundToTilePlayer();

		if (isCritical == false)
			setStaticNoCritCount(getStaticNoCritCount() + 1);
		else
			new CameraShaker().shaking(SHAKE.LITTLE);

		if (isPuzzleWon) {
			new CameraShaker().shaking(getFactory().getVictoryShake());
			new SoundTask().playSound(SOUNDTYPE.SOUND, getFactory().getVictorySound());
			actionOnWinning.doAction(isCritical);
		} else {
			new SoundTask().playSound(SOUNDTYPE.SOUND, "fail");
			actionOnLosing.doAction();
		}
	}

}
