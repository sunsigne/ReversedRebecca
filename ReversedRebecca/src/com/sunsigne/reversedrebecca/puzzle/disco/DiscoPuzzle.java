package com.sunsigne.reversedrebecca.puzzle.disco;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.disco.DiscoArrowObject;
import com.sunsigne.reversedrebecca.object.puzzle.disco.DiscoPlayerArrowObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;

public abstract class DiscoPuzzle extends Puzzle {

	public DiscoPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		super(toolPlayer, actionOnWinning);
		// new GameCursor().setCursor(null);
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

	private DiscoPlayerArrowObject[] player_arrows;

	public DiscoPlayerArrowObject getPlayerArrow(DIRECTION facing) {
		return player_arrows[facing.getNum()];
	}

	protected void createPlayerArrows() {
		player_arrows = new DiscoPlayerArrowObject[4];

		player_arrows[DIRECTION.LEFT.getNum()] = new DiscoPlayerArrowObject(this, DIRECTION.LEFT, getCol(7));
		player_arrows[DIRECTION.RIGHT.getNum()] = new DiscoPlayerArrowObject(this, DIRECTION.RIGHT, getCol(10));
		player_arrows[DIRECTION.UP.getNum()] = new DiscoPlayerArrowObject(this, DIRECTION.UP, getCol(8));
		player_arrows[DIRECTION.DOWN.getNum()] = new DiscoPlayerArrowObject(this, DIRECTION.DOWN, getCol(9));

		LAYER.PUZZLE.addObject(player_arrows[0]);
		LAYER.PUZZLE.addObject(player_arrows[1]);
		LAYER.PUZZLE.addObject(player_arrows[2]);
		LAYER.PUZZLE.addObject(player_arrows[3]);
	}

	private GameList<DiscoArrowObject> arrow_list = new GameList<>(LISTTYPE.ARRAY);

	protected void createArrow(DIRECTION facing, int row) {
		int x = getPlayerArrow(facing).getX();
		DiscoArrowObject arrow = new DiscoArrowObject(this, facing, x, getRow(row));
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
