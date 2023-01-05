package com.sunsigne.reversedrebecca.puzzle.dig;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.object.puzzle.dig.BuriedExitObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.BuriedNullObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.BuriedObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DigMouseObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DirtObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.obstacle.BuriedObstacleObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.obstacle.LogObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.obstacle.RockObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DIG_STATE;
import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DigAxeToolObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DigHandToolObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DigPickaxeToolObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DigShovelToolObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DigToolObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public abstract class DigPuzzle extends Puzzle {

	public DigPuzzle(int criticalChance, GenericListener actionOnWinning) {
		super(criticalChance, actionOnWinning);

		new GameCursor().setCursor(null);
		LAYER.PUZZLE.addObject(new DigMouseObject(this, getSize() / 2, getSize() / 2));
	}

	////////// USEFULL ////////////

	public int getCol(float col) {
		return (int) (2 * Size.XS + col * Size.L);
	}

	public int getRow(float row) {
		return (int) (Size.XS + row * Size.L);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "dig";
	}

	public abstract int getSize();

	////////// FACTORY ////////////

	@Override
	public PuzzleFactory getFactory() {
		return new DigPuzzleFactory();
	}

	////////// PUZZLE ////////////

	private DIG_STATE state = DIG_STATE.DIG;

	public DIG_STATE getState() {
		return state;
	}

	public void setState(DIG_STATE state) {
		this.state = state;
	}

	public DigToolObject getTool(DIG_STATE dig_state, int x_pos_in_menu, int y_pos_in_menu, boolean selectable) {
		switch (dig_state) {
		case PICK:
			return new DigPickaxeToolObject(this, x_pos_in_menu, y_pos_in_menu, getSize(), getSize(), selectable);

		case CHOP:
			return new DigAxeToolObject(this, x_pos_in_menu, y_pos_in_menu, getSize(), getSize(), selectable);

		case PUNCH:
			return new DigHandToolObject(this, x_pos_in_menu, y_pos_in_menu, getSize(), getSize(), selectable);

		case DIG:
		default:
			return new DigShovelToolObject(this, x_pos_in_menu, y_pos_in_menu, getSize(), getSize(), selectable);
		}
	}

	@Override
	protected void createWallBorder() {
		super.createWallBorder();

		Handler handler = LAYER.PUZZLE.getHandler();
		BufferedImage image = getWallTexture();

		for (int row = 0; row < 8; row++) {
			handler.addObject(new WallPuzzle(image, getCol(5), getRow(row)));
			handler.addObject(new WallPuzzle(image, getCol(6), getRow(row)));
		}
	}

	private GameList<DirtObject> dirt_list = new GameList<>(LISTTYPE.ARRAY);

	protected void createDirt(int col, int row) {
		createDirt((float) col, (float) row);
	}

	protected void createDirt(float col, float row) {
		DirtObject dirt = new DirtObject(this, getSize(), getSize());
		dirt_list.addObject(dirt);
		dirt.setX(getCol(col));
		dirt.setY(getRow(row));
		LAYER.PUZZLE.addObject(dirt);
	}

	protected void createRock(int amount) {
		for (int index = 0; index < amount; index++) {
			DirtObject dirt = new RandomGenerator().getElementFromList(dirt_list);

			RockObject rock = new RockObject(this, getSize(), getSize());
			rock.setX(dirt.getX());
			rock.setY(dirt.getY());

			dirt.setBuriedObject(rock, getSize(), getSize());
		}
	}

	protected void createLog(int amount) {
		for (int index = 0; index < amount; index++) {
			DirtObject dirt = new RandomGenerator().getElementFromList(dirt_list);

			LogObject log = new LogObject(this, getSize(), getSize());
			log.setX(dirt.getX());
			log.setY(dirt.getY());

			dirt.setBuriedObject(log, getSize(), getSize());
		}
	}

	protected void createTool(int col, int row, DIG_STATE dig_state) {
		DigToolObject tool = getTool(dig_state, getCol(col), getRow(row), true);
		tool.setX(getCol(col));
		tool.setY(getRow(row));
		LAYER.PUZZLE.addObject(tool);
	}

	private HashMap<DIG_STATE, DIG_STATE> tool_map = new HashMap<>();

	protected void createBuriedTool(int col, int row, DIG_STATE dig_state) {
		DigToolObject tool = getTool(dig_state, getCol(col), getRow(row), false);

		DirtObject dirt;

		do {
			dirt = new RandomGenerator().getElementFromList(dirt_list);
			BuriedObject buried = dirt.getBuriedObject();

			if (buried instanceof BuriedObstacleObject == false) {
				if (buried instanceof DigToolObject)
					continue;

				dirt.setBuriedObject(tool, getSize(), getSize());
				return;
			}
			BuriedObstacleObject obstacle = (BuriedObstacleObject) buried;

			if (dig_state != obstacle.getState()) {

				if (isImpossiblePuzzle(tool.getState(), obstacle.getState()) == false) {

					obstacle.setBuriedObject(tool, getSize(), getSize());
					tool_map.put(tool.getState(), obstacle.getState());
					return;
				}
			}

		} while (true);
	}

	protected void createBuriedShovel(int col, int row) {
		DigToolObject tool = getTool(DIG_STATE.DIG, getCol(col), getRow(row), false);

		DirtObject dirt;

		do {
			dirt = new RandomGenerator().getElementFromList(dirt_list);
			BuriedObject buried = dirt.getBuriedObject();

			if (buried instanceof BuriedNullObject)
				break;

			if (buried instanceof BuriedObstacleObject) {
				BuriedObstacleObject obstacle = (BuriedObstacleObject) buried;
				if (obstacle.getBuriedObject() instanceof BuriedNullObject)
					break;
			}

		} while (true);

		dirt.setBuriedObject(tool, getSize(), getSize());
	}

	private boolean isImpossiblePuzzle(DIG_STATE tool_state, DIG_STATE obstacle_state) {
		if (tool_map.containsKey(obstacle_state))
			return tool_map.get(obstacle_state) == tool_state;
		else
			return false;
	}

	protected void createExit() {
		BuriedExitObject exit = new BuriedExitObject(this, getSize(), getSize());
		DirtObject dirt;

		do {
			dirt = new RandomGenerator().getElementFromList(dirt_list);
		} while (dirt.getBuriedObject() instanceof DigToolObject);

		BuriedObject buried = dirt.getBuriedObject();

		if (buried instanceof BuriedObstacleObject) {
			BuriedObstacleObject obstacle = (BuriedObstacleObject) buried;

			if (obstacle.getBuriedObject() instanceof DigToolObject) {
				createExit();
				return;
			}
			obstacle.setBuriedObject(exit, getSize(), getSize());
		} else
			dirt.setBuriedObject(exit, getSize(), getSize());
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color brown = new Color(50, 30, 15, 240);
		new TransluantLayer().drawPuzzle(g, brown);
	}

}
