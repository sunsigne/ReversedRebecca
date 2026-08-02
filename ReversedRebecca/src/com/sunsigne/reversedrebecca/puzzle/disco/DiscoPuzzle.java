package com.sunsigne.reversedrebecca.puzzle.disco;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.disco.DiscoArrowObject;
import com.sunsigne.reversedrebecca.object.puzzle.disco.DiscoBallObject;
import com.sunsigne.reversedrebecca.object.puzzle.disco.DiscoDancerObject;
import com.sunsigne.reversedrebecca.object.puzzle.disco.DiscoPlayerArrowObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;

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

	protected void createDiscoDancer(DIRECTION position, int delayBeforeLitinTicks) {
		DiscoDancerObject dancer = getDiscoDancer();
		int col = position == DIRECTION.LEFT ? 0 : 7;
		int gap = position == DIRECTION.LEFT ? 0 : Size.S;
		dancer.setX(getCol(col + 2) + gap);
		dancer.setY(getRow(4));
		dancer.lit(delayBeforeLitinTicks);

		LAYER.PUZZLE.addObject(dancer);
	}

	protected void createDiscoBall(DIRECTION position) {
		LAYER.PUZZLE.addObject(new DiscoBallObject(this, position));
	}

	private DiscoPlayerArrowObject[] player_arrows;

	public DiscoPlayerArrowObject getPlayerArrow(DIRECTION facing) {
		return player_arrows[facing.getNum()];
	}

	protected void createPlayerArrows(DIRECTION position) {
		DIRECTION facing;
		player_arrows = new DiscoPlayerArrowObject[4];
		int col = position == DIRECTION.LEFT ? 1 : 7;
		int gap = position == DIRECTION.LEFT ? Size.S : 0;
		
		facing = DIRECTION.LEFT;
		player_arrows[facing.getNum()] = new DiscoPlayerArrowObject(this, facing, getCol(col) + gap + Size.XS + Size.XS);
		facing = DIRECTION.RIGHT;
		player_arrows[facing.getNum()] = new DiscoPlayerArrowObject(this, facing, getCol(col + 3) + gap + Size.XS + Size.L);
		facing = DIRECTION.UP;
		player_arrows[facing.getNum()] = new DiscoPlayerArrowObject(this, facing, getCol(col + 1) + gap + Size.XS + Size.S);
		facing = DIRECTION.DOWN;
		player_arrows[facing.getNum()] = new DiscoPlayerArrowObject(this, facing, getCol(col + 2) + gap + Size.XS + Size.M);

		LAYER.PUZZLE.addObject(player_arrows[0]);
		LAYER.PUZZLE.addObject(player_arrows[1]);
		LAYER.PUZZLE.addObject(player_arrows[2]);
		LAYER.PUZZLE.addObject(player_arrows[3]);
	}

	private GameList<DiscoArrowObject> arrow_list = new GameList<>(LISTTYPE.ARRAY);

	protected void createArrow(DIRECTION facing, int y) {
		int x = getPlayerArrow(facing).getX();
		DiscoArrowObject arrow = new DiscoArrowObject(this, facing, x, y);
		arrow_list.addObject(arrow);
		LAYER.PUZZLE.addObject(arrow);
	}

	public void setArrowSpeed(int speed) {
		for (DiscoArrowObject tempArrow : arrow_list.getList())
			tempArrow.setVelY(-speed);
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

	@Override
	public void tick() {
		time++;
		if (time >= getTimer())
			closePuzzle(true);
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 5;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color purple = new Color(40, 20, 70, 240);
		new TransluantLayer().drawPuzzle(g, purple);
	}

}
