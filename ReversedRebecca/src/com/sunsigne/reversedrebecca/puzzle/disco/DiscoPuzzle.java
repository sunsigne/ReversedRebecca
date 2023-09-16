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
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;

public abstract class DiscoPuzzle extends Puzzle {

	public DiscoPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		super(toolPlayer, actionOnWinning);
		new GameCursor().setCursor(null);
		
		new SoundTask().playMusic("dance_floor", false, true);
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

	public abstract DiscoDancerObject getDiscoDancer();

	protected void createDiscoDancer(int delayBeforeLitinTicks) {
		DiscoDancerObject dancer = getDiscoDancer();
		dancer.setX(getCol(2));
		dancer.setY(getRow(4));
		dancer.lit(delayBeforeLitinTicks);

		LAYER.PUZZLE.addObject(dancer);
	}

	protected void createDiscoBall() {
		LAYER.PUZZLE.addObject(new DiscoBallObject(this));
	}

	private DiscoPlayerArrowObject[] player_arrows;

	public DiscoPlayerArrowObject getPlayerArrow(DIRECTION facing) {
		return player_arrows[facing.getNum()];
	}

	protected void createPlayerArrows() {
		DIRECTION facing;
		player_arrows = new DiscoPlayerArrowObject[4];

		facing = DIRECTION.LEFT;
		player_arrows[facing.getNum()] = new DiscoPlayerArrowObject(this, facing, getCol(7) + Size.XS + Size.XS);
		facing = DIRECTION.RIGHT;
		player_arrows[facing.getNum()] = new DiscoPlayerArrowObject(this, facing, getCol(10) + Size.XS + Size.L);
		facing = DIRECTION.UP;
		player_arrows[facing.getNum()] = new DiscoPlayerArrowObject(this, facing, getCol(8) + Size.XS + Size.S);
		facing = DIRECTION.DOWN;
		player_arrows[facing.getNum()] = new DiscoPlayerArrowObject(this, facing, getCol(9) + Size.XS + Size.M);

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

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color purple = new Color(40, 20, 70, 240);
		new TransluantLayer().drawPuzzle(g, purple);
	}

}
