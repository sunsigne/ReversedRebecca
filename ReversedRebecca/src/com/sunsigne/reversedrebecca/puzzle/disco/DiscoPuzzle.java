package com.sunsigne.reversedrebecca.puzzle.disco;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.object.puzzle.disco.DiscoArrowObject;
import com.sunsigne.reversedrebecca.object.puzzle.disco.DiscoArrowWallObject;
import com.sunsigne.reversedrebecca.object.puzzle.disco.DiscoAutoArrowObject;
import com.sunsigne.reversedrebecca.object.puzzle.disco.DiscoBallObject;
import com.sunsigne.reversedrebecca.object.puzzle.disco.DiscoDancerObject;
import com.sunsigne.reversedrebecca.object.puzzle.disco.DiscoPlayerArrowObject;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.system.DifficultyOption;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public abstract class DiscoPuzzle extends Puzzle {

	public DiscoPuzzle(ToolPlayer toolPlayer, GenericListenerBoolean actionOnWinning, GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
		new GameCursor().setCursor(null);

		new SoundTask().playMusic("'til_the_end_of_the_night", false, true);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "disco";
	}

	////////// FACTORY ////////////

	@Override
	public PuzzleFactory getFactory() {
		return new DiscoPuzzleFactory();
	}

	////////// PUZZLE ////////////

	public abstract int getTimer();

	public abstract DiscoDancerObject getDiscoDancer();

	private DiscoDancerObject dancer;
	private DiscoDancerObject secondDancer;

	protected void createDiscoDancer(DIRECTION position) {
		if (dancer != null)
			secondDancer = dancer;
		dancer = getDiscoDancer();
		int col = position == DIRECTION.LEFT ? 0 : 7;
		int gap = position == DIRECTION.LEFT ? 0 : Size.S;
		dancer.setX(getCol(col + 2) + gap);
		dancer.setY(getRow(4));

		LAYER.PUZZLE.addObject(dancer);
	}

	public void updateDiscoDancerFacing(DIRECTION facing) {
		if (dancer != null && dancer.isAutoplay())
			dancer.setFacing(facing);
	}

	public void litDiscoDancer(int delayBeforeLitinTicks) {
		if (dancer != null)
			dancer.lit(delayBeforeLitinTicks);
		if (secondDancer != null)
			secondDancer.lit(delayBeforeLitinTicks);
	}

	public void switchSide(int element, int delayBeforeSwitchSideinTicks) {
		new GameTimer(delayBeforeSwitchSideinTicks, true, () -> switchSide(element));
	}

	private void switchSide(int element) {
		if (element == 1 || element == 3) {
			dancer.switchSide();
			if (secondDancer != null)
				secondDancer.switchSide();
		}

		if (element == 2 || element == 3) {
			ball.switchSide();
			if (secondBall != null)
				secondBall.switchSide();
		}
	}

	private DiscoBallObject ball;
	private DiscoBallObject secondBall;

	protected void createDiscoBall(DIRECTION position) {
		if (ball != null)
			secondBall = ball;
		ball = new DiscoBallObject(this, position);
		LAYER.PUZZLE.addObject(ball);
	}

	private DiscoPlayerArrowObject[] player_arrows;

	public DiscoPlayerArrowObject getPlayerArrow(DIRECTION facing) {
		return player_arrows[facing.getNum()];
	}

	protected void createPlayerArrows(DIRECTION position) {
		DIRECTION facing;
		player_arrows = new DiscoPlayerArrowObject[4];

		int col = 4;
		int gap = 0;

		switch (position) {
		case LEFT:
			col = 1;
			gap = Size.S;
			break;
		case RIGHT:
			col = 7;
			break;
		default:
			break;
		}

		facing = DIRECTION.LEFT;
		player_arrows[facing.getNum()] = new DiscoPlayerArrowObject(this, facing,
				getCol(col) + gap + Size.XS + Size.XS);
		facing = DIRECTION.RIGHT;
		player_arrows[facing.getNum()] = new DiscoPlayerArrowObject(this, facing,
				getCol(col + 3) + gap + Size.XS + Size.L);
		facing = DIRECTION.UP;
		player_arrows[facing.getNum()] = new DiscoPlayerArrowObject(this, facing,
				getCol(col + 1) + gap + Size.XS + Size.S);
		facing = DIRECTION.DOWN;
		player_arrows[facing.getNum()] = new DiscoPlayerArrowObject(this, facing,
				getCol(col + 2) + gap + Size.XS + Size.M);

		LAYER.PUZZLE.addObject(player_arrows[0]);
		LAYER.PUZZLE.addObject(player_arrows[1]);
		LAYER.PUZZLE.addObject(player_arrows[2]);
		LAYER.PUZZLE.addObject(player_arrows[3]);
	}

	private GameList<DiscoArrowObject> arrow_list = new GameList<>(LISTTYPE.ARRAY);

	protected void createArrow(DIRECTION facing, int y) {
		createArrow(facing, y, false);
	}

	protected void createArrow(DIRECTION facing, int y, boolean autoplay) {
		int x = getPlayerArrow(facing).getX();
		DiscoArrowObject arrow = getArrow(this, facing, x, y, autoplay);
		arrow_list.addObject(arrow);
		LAYER.PUZZLE.addObject(arrow);
	}

	private DiscoArrowObject getArrow(Puzzle puzzle, DIRECTION facing, int x, int y, boolean autoplay) {
		switch (DifficultyOption.getDifficulty()) {
		case EASY:
			autoplay = true;
			break;
		case NORMAL:
			break;
		case HARD:
			autoplay = false;
			break;
		}

		if (autoplay)
			return new DiscoAutoArrowObject(puzzle, facing, x, y);
		else
			return new DiscoArrowObject(puzzle, facing, x, y);
	}

	public void setArrowSpeed(int speed) {
		for (DiscoArrowObject tempArrow : arrow_list.getList())
			tempArrow.setVelY(-speed);
	}

	public void setArrowSpeed(int speed, boolean arrow_wall) {
		if (arrow_wall == false) {
			setArrowSpeed(speed);
			return;
		}

		for (DiscoArrowWallObject tempArrow : arrow_wall_list.getList())
			tempArrow.setSpeed(speed);
	}

	protected void activateRadArrowWall() {
		if (arrow_wall_list.getList().isEmpty())
			return;

		var arrow = new RandomGenerator().getElementFromList(arrow_wall_list);
		arrow_wall_list.removeObject(arrow);
		arrow.activate(player_arrows);
	}

	////////// TOOL ////////////

	private static int noCritCount;

	@Override
	protected int getStaticNoCritCount() {
		return noCritCount;
	}

	@Override
	protected void setStaticNoCritCount(int noCritCount) {
		DiscoPuzzle.noCritCount = noCritCount;
	}

	@Override
	public boolean hasCritToken() {
		return false;
	}

	////////// TICK ////////////

	private int time;
	private int arrow_wall_delay;

	protected void setArrowWallDelay(int arrow_wall_delay) {
		this.arrow_wall_delay = arrow_wall_delay;
	}

	@Override
	public void tick() {
		time++;
		if (time >= getTimer())
			closePuzzle(true);

		if (arrow_wall_delay != 0 && time % arrow_wall_delay == 0)
			activateRadArrowWall();
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 5;
	}

	private DIRECTION getRandomFacing() {
		var rad = new RandomGenerator();

		if (rad.getBoolean())
			return rad.getBoolean() ? DIRECTION.LEFT : DIRECTION.RIGHT;
		else
			return rad.getBoolean() ? DIRECTION.UP : DIRECTION.DOWN;
	}

	private GameList<DiscoArrowWallObject> arrow_wall_list = new GameList<>(LISTTYPE.ARRAY);

	@Override
	protected void createWallBorder() {

		Handler handler = LAYER.PUZZLE.getHandler();
		BufferedImage image = getWallTexture();
		DiscoArrowWallObject arrow;

		for (int col = 0; col < 14; col++) {
			handler.addObject(new WallPuzzle(image, getCol(col), getRow(0)));
			arrow = new DiscoArrowWallObject(this, getRandomFacing(), getCol(col), getRow(0));
			handler.addObject(arrow);
			arrow_wall_list.addObject(arrow);

			handler.addObject(new WallPuzzle(col != 13 ? image : getWallTexture(true), getCol(col), getRow(7)));
			arrow = new DiscoArrowWallObject(this, getRandomFacing(), getCol(col), getRow(7));
			handler.addObject(arrow);
			arrow_wall_list.addObject(arrow);

		}
		for (int row = 1; row < 7; row++) {
			handler.addObject(new WallPuzzle(image, getCol(0), getRow(row)));
			arrow = new DiscoArrowWallObject(this, getRandomFacing(), getCol(0), getRow(row));
			handler.addObject(arrow);
			arrow_wall_list.addObject(arrow);
			handler.addObject(new WallPuzzle(image, getCol(13), getRow(row)));
			arrow = new DiscoArrowWallObject(this, getRandomFacing(), getCol(13), getRow(row));
			handler.addObject(arrow);
			arrow_wall_list.addObject(arrow);
		}
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color purple = new Color(40, 20, 70, 240);
		new TransluantLayer().drawPuzzle(g, purple);
	}

}
