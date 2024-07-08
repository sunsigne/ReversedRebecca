package com.sunsigne.reversedrebecca.puzzle.dig;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.object.puzzle.dig.BuriedExitObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.BuriedNullObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.BuriedObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DigMouseObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DirtObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.obstacle.BuriedObstacleObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.obstacle.BushObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.obstacle.LogObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.obstacle.RockObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DIG_STATE;
import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DigAxeToolObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DigCriticalToolObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DigHandToolObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DigPickaxeToolObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DigShovelToolObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DigSwordToolObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DigToolObject;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionThreeKey;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePreseting;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public abstract class DigPuzzle extends Puzzle implements GamepadEvent, MousePreseting {

	public DigPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		super(toolPlayer, actionOnWinning);

		new GameCursor().setCursor(null);
		LAYER.PUZZLE.addObject(new DigMouseObject(this, getSize() / 2, getSize() / 2));
		loadGamepadSetup();
	}

	////////// USEFULL ////////////

	public static int getCol(float col) {
		return (int) (2 * Size.XS + col * Size.L);
	}

	public static int getRow(float row) {
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

		case SLASH:
			return new DigSwordToolObject(this, x_pos_in_menu, y_pos_in_menu, getSize(), getSize(), selectable);

		case CRITICAL:
			return new DigCriticalToolObject(this, x_pos_in_menu, y_pos_in_menu, getSize(), getSize(), selectable);

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

	protected void createBush(int amount) {
		for (int index = 0; index < amount; index++) {
			DirtObject dirt = new RandomGenerator().getElementFromList(dirt_list);

			BushObject bush = new BushObject(this, getSize(), getSize());
			bush.setX(dirt.getX());
			bush.setY(dirt.getY());

			dirt.setBuriedObject(bush, getSize(), getSize());
		}
	}

	protected void createTool(int col, int row, DIG_STATE dig_state) {
		DigToolObject tool = getTool(dig_state, getCol(col), getRow(row), true);
		tool.setX(getCol(col));
		tool.setY(getRow(row));
		tool_list.addObject(tool);
		LAYER.PUZZLE.addObject(tool);
	}

	private HashMap<DIG_STATE, DIG_STATE> tool_map = new HashMap<>();
	public GameList<DigToolObject> tool_list = new GameList<>(LISTTYPE.ARRAY);

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

			if (obstacle.getBuriedObject() instanceof DigToolObject)
				continue;

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
		if (tool_map.size() >= 2)
			return true;

		if (tool_map.containsKey(obstacle_state))
			return tool_map.get(obstacle_state) == tool_state;
		else
			return false;
	}

	private GameList<BuriedExitObject> exit_list = new GameList<>(LISTTYPE.ARRAY);

	protected void createExit() {
		BuriedExitObject exit = new BuriedExitObject(this, getSize(), getSize());
		DirtObject dirt;

		do {
			dirt = new RandomGenerator().getElementFromList(dirt_list);
		} while (dirt.getBuriedObject() instanceof DigToolObject || dirt.getBuriedObject() instanceof BuriedExitObject);

		BuriedObject buried = dirt.getBuriedObject();

		if (buried instanceof BuriedObstacleObject) {
			BuriedObstacleObject obstacle = (BuriedObstacleObject) buried;

			if (obstacle.getBuriedObject() instanceof DigToolObject
					|| obstacle.getBuriedObject() instanceof BuriedExitObject) {
				createExit();
				return;
			}
			obstacle.setBuriedObject(exit, getSize(), getSize());
			exit_list.addObject(exit);
		} else {
			dirt.setBuriedObject(exit, getSize(), getSize());
			exit_list.addObject(exit);
		}

	}

	public boolean stillContainsExit(BuriedExitObject exitToRemove) {
		exit_list.removeObject(exitToRemove);
		return exit_list.getList().isEmpty() == false;
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 4;
	}
		
	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color brown = new Color(50, 30, 15, 240);
		new TransluantLayer().drawPuzzle(g, brown);

		if (ControllerManager.getInstance().isUsingGamepad())
			g.drawImage(ActionThreeKey.getGamepadButton(), 635, 860, Size.S, Size.S, null);
	}

	////////// PRESET MOUSE POS ////////////

	private static final float S_GAP = 1.5f;

	public static final PresetMousePos S_LEFT_UP = new PresetMousePos(getCol(7f + S_GAP), getRow(1f + S_GAP));
	public static final PresetMousePos S_RIGHT_UP = new PresetMousePos(getCol(10f + S_GAP), getRow(1f + S_GAP));
	public static final PresetMousePos S_LEFT_DOWN = new PresetMousePos(getCol(7f + S_GAP), getRow(4f + S_GAP));
	public static final PresetMousePos S_RIGHT_DOWN = new PresetMousePos(getCol(10f + S_GAP), getRow(4f + S_GAP));

	private static final float M_GAP = 1f;

	public static final PresetMousePos M_LEFT_UP = new PresetMousePos(getCol(7f + M_GAP), getRow(1f + M_GAP));
	public static final PresetMousePos M_MIDDLE_UP = new PresetMousePos(getCol(9f + M_GAP), getRow(1f + M_GAP));
	public static final PresetMousePos M_RIGHT_UP = new PresetMousePos(getCol(11f + M_GAP), getRow(1f + M_GAP));
	public static final PresetMousePos M_LEFT_MIDDLE = new PresetMousePos(getCol(7f + M_GAP), getRow(3f + M_GAP));
	public static final PresetMousePos M_MIDDLE_MIDDLE = new PresetMousePos(getCol(9f + M_GAP), getRow(3f + M_GAP));
	public static final PresetMousePos M_RIGHT_MIDDLE = new PresetMousePos(getCol(11f + M_GAP), getRow(3f + M_GAP));
	public static final PresetMousePos M_LEFT_DOWN = new PresetMousePos(getCol(7f + M_GAP), getRow(5f + M_GAP));
	public static final PresetMousePos M_MIDDLE_DOWN = new PresetMousePos(getCol(9f + M_GAP), getRow(5f + M_GAP));
	public static final PresetMousePos M_RIGHT_DOWN = new PresetMousePos(getCol(11f + M_GAP), getRow(5f + M_GAP));

	private static final float L_GAP = 0.75f;

	public static final PresetMousePos L_LEFTMAX_UPMAX = new PresetMousePos(getCol(7f + L_GAP), getRow(1f + L_GAP));
	public static final PresetMousePos L_LEFT_UPMAX = new PresetMousePos(getCol(8.5f + L_GAP), getRow(1f + L_GAP));
	public static final PresetMousePos L_RIGHT_UPMAX = new PresetMousePos(getCol(10f + L_GAP), getRow(1f + L_GAP));
	public static final PresetMousePos L_RIGHTMAX_UPMAX = new PresetMousePos(getCol(11.5f + L_GAP), getRow(1f + L_GAP));
	public static final PresetMousePos L_LEFTMAX_UP = new PresetMousePos(getCol(7f + L_GAP), getRow(2.5f + L_GAP));
	public static final PresetMousePos L_LEFT_UP = new PresetMousePos(getCol(8.5f + L_GAP), getRow(2.5f + L_GAP));
	public static final PresetMousePos L_RIGHT_UP = new PresetMousePos(getCol(10f + L_GAP), getRow(2.5f + L_GAP));
	public static final PresetMousePos L_RIGHTMAX_UP = new PresetMousePos(getCol(11.5f + L_GAP), getRow(2.5f + L_GAP));
	public static final PresetMousePos L_LEFTMAX_DOWN = new PresetMousePos(getCol(7f + L_GAP), getRow(4f + L_GAP));
	public static final PresetMousePos L_LEFT_DOWN = new PresetMousePos(getCol(8.5f + L_GAP), getRow(4f + L_GAP));
	public static final PresetMousePos L_RIGHT_DOWN = new PresetMousePos(getCol(10f + L_GAP), getRow(4f + L_GAP));
	public static final PresetMousePos L_RIGHTMAX_DOWN = new PresetMousePos(getCol(11.5f + L_GAP), getRow(4f + L_GAP));
	public static final PresetMousePos L_LEFTMAX_DOWNMAX = new PresetMousePos(getCol(7f + L_GAP), getRow(5.5f + L_GAP));
	public static final PresetMousePos L_LEFT_DOWNMAX = new PresetMousePos(getCol(8.5f + L_GAP), getRow(5.5f + L_GAP));
	public static final PresetMousePos L_RIGHT_DOWNMAX = new PresetMousePos(getCol(10f + L_GAP), getRow(5.5f + L_GAP));
	public static final PresetMousePos L_RIGHTMAX_DOWNMAX = new PresetMousePos(getCol(11.5f + L_GAP),
			getRow(5.5f + L_GAP));

	private PresetMousePos preset;

	@Override
	public PresetMousePos getPreset() {
		return preset;
	}

	@Override
	public void setPreset(PresetMousePos preset) {
		this.setPreset(preset, true);
	}

	public void setPreset(PresetMousePos preset, boolean sound) {
		this.preset = preset;
		preset.moveMouse();

		if (isPresetNull() == false && sound)
			new SoundTask().playSound(SOUNDTYPE.SOUND, "gamepad");
	}

	protected void loadGamepadSetup() {
		if (ControllerManager.getInstance().isUsingGamepad())
			setPreset(getDefaultPreset(), false);
	}

	////////// GAMEPAD ////////////

	private GamepadController gamepadController = new GamepadController(this);

	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}

	private boolean pressingButton;

	protected boolean pressingButton() {
		if (pressingButton)
			return true;

		pressingButton = true;
		new GameTimer(3, true, () -> pressingButton = false);
		return false;
	}

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;

		if (isPresetNull())
			setPreset(getDefaultPreset());

		else if (e.getKey() == ButtonEvent.B)
			getNextTool();
		else if (getSize() == 3 * Size.L)
			smallGrid(e);
		else if (getSize() == 2 * Size.L)
			mediumGrid(e);
		else if (getSize() == 2 * Size.M)
			largeGrid(e);
	}

	@Override
	public void buttonReleased(ButtonEvent e) {

	}

	private void getNextTool() {
		int size = tool_list.getList().size();
		boolean flag = false;

		for (int index = 0; index < size; index++) {
			DigToolObject tempTool = tool_list.getList().get(index);

			if (flag) {
				if (equippingTool(tempTool))
					return;
			}

			if (tempTool.getState() == getState())
				flag = true;
		}

		if (equippingTool(tool_list.getList().get(0)))
			return;
	}

	private boolean equippingTool(DigToolObject tool) {
		if (tool.isSelectable() && LAYER.PUZZLE.getHandler().containsObject(tool)) {
			new SoundTask().playSound(SOUNDTYPE.SOUND, "select_tool");
			setState(tool.getState());
			return true;
		}

		return false;
	}

	private void smallGrid(ButtonEvent e) {

		if (getPreset() == S_LEFT_UP) {
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(S_RIGHT_UP);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(S_LEFT_DOWN);
		}

		else if (getPreset() == S_RIGHT_UP) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(S_LEFT_UP);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(S_RIGHT_DOWN);
		}

		else if (getPreset() == S_LEFT_DOWN) {
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(S_RIGHT_DOWN);
			if (e.getKey() == ButtonEvent.UP)
				setPreset(S_LEFT_UP);
		}

		else if (getPreset() == S_RIGHT_DOWN) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(S_LEFT_DOWN);
			if (e.getKey() == ButtonEvent.UP)
				setPreset(S_RIGHT_UP);
		}
	}

	private void mediumGrid(ButtonEvent e) {
		if (getPreset() == M_LEFT_UP) {
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(M_MIDDLE_UP);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(M_LEFT_MIDDLE);
		}

		else if (getPreset() == M_MIDDLE_UP) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(M_LEFT_UP);
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(M_RIGHT_UP);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(M_MIDDLE_MIDDLE);
		}

		else if (getPreset() == M_RIGHT_UP) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(M_MIDDLE_UP);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(M_RIGHT_MIDDLE);
		}

		else if (getPreset() == M_LEFT_MIDDLE) {
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(M_MIDDLE_MIDDLE);
			if (e.getKey() == ButtonEvent.UP)
				setPreset(M_LEFT_UP);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(M_LEFT_DOWN);
		}

		else if (getPreset() == M_MIDDLE_MIDDLE) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(M_LEFT_MIDDLE);
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(M_RIGHT_MIDDLE);
			if (e.getKey() == ButtonEvent.UP)
				setPreset(M_MIDDLE_UP);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(M_MIDDLE_DOWN);
		}

		else if (getPreset() == M_RIGHT_MIDDLE) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(M_MIDDLE_MIDDLE);
			if (e.getKey() == ButtonEvent.UP)
				setPreset(M_RIGHT_UP);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(M_RIGHT_DOWN);
		}

		else if (getPreset() == M_LEFT_DOWN) {
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(M_MIDDLE_DOWN);
			if (e.getKey() == ButtonEvent.UP)
				setPreset(M_LEFT_MIDDLE);
		}

		else if (getPreset() == M_MIDDLE_DOWN) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(M_LEFT_DOWN);
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(M_RIGHT_DOWN);
			if (e.getKey() == ButtonEvent.UP)
				setPreset(M_MIDDLE_MIDDLE);
		}

		else if (getPreset() == M_RIGHT_DOWN) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(M_MIDDLE_DOWN);
			if (e.getKey() == ButtonEvent.UP)
				setPreset(M_RIGHT_MIDDLE);
		}
	}

	private void largeGrid(ButtonEvent e) {
		if (getPreset() == L_LEFTMAX_UPMAX) {
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(L_LEFT_UPMAX);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(L_LEFTMAX_UP);
		}

		else if (getPreset() == L_LEFT_UPMAX) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(L_LEFTMAX_UPMAX);
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(L_RIGHT_UPMAX);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(L_LEFT_UP);
		}

		else if (getPreset() == L_RIGHT_UPMAX) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(L_LEFT_UPMAX);
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(L_RIGHTMAX_UPMAX);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(L_RIGHT_UP);
		}

		else if (getPreset() == L_RIGHTMAX_UPMAX) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(L_RIGHT_UPMAX);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(L_RIGHTMAX_UP);
		}

		else if (getPreset() == L_LEFTMAX_UP) {
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(L_LEFT_UP);
			if (e.getKey() == ButtonEvent.UP)
				setPreset(L_LEFTMAX_UPMAX);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(L_LEFTMAX_DOWN);
		}

		else if (getPreset() == L_LEFT_UP) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(L_LEFTMAX_UP);
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(L_RIGHT_UP);
			if (e.getKey() == ButtonEvent.UP)
				setPreset(L_LEFT_UPMAX);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(L_LEFT_DOWN);
		}

		else if (getPreset() == L_RIGHT_UP) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(L_LEFT_UP);
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(L_RIGHTMAX_UP);
			if (e.getKey() == ButtonEvent.UP)
				setPreset(L_RIGHT_UPMAX);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(L_RIGHT_DOWN);
		}

		else if (getPreset() == L_RIGHTMAX_UP) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(L_RIGHT_UP);
			if (e.getKey() == ButtonEvent.UP)
				setPreset(L_RIGHTMAX_UPMAX);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(L_RIGHTMAX_DOWN);
		}

		else if (getPreset() == L_LEFTMAX_DOWN) {
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(L_LEFT_DOWN);
			if (e.getKey() == ButtonEvent.UP)
				setPreset(L_LEFTMAX_UP);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(L_LEFTMAX_DOWNMAX);
		}

		else if (getPreset() == L_LEFT_DOWN) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(L_LEFTMAX_DOWN);
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(L_RIGHT_DOWN);
			if (e.getKey() == ButtonEvent.UP)
				setPreset(L_LEFT_UP);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(L_LEFT_DOWNMAX);
		}

		else if (getPreset() == L_RIGHT_DOWN) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(L_LEFT_DOWN);
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(L_RIGHTMAX_DOWN);
			if (e.getKey() == ButtonEvent.UP)
				setPreset(L_RIGHT_UP);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(L_RIGHT_DOWNMAX);
		}

		else if (getPreset() == L_RIGHTMAX_DOWN) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(L_RIGHT_DOWN);
			if (e.getKey() == ButtonEvent.UP)
				setPreset(L_RIGHTMAX_UP);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(L_RIGHTMAX_DOWNMAX);
		}

		else if (getPreset() == L_LEFTMAX_DOWNMAX) {
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(L_LEFT_DOWNMAX);
			if (e.getKey() == ButtonEvent.UP)
				setPreset(L_LEFTMAX_DOWN);
		}

		else if (getPreset() == L_LEFT_DOWNMAX) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(L_LEFTMAX_DOWNMAX);
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(L_RIGHT_DOWNMAX);
			if (e.getKey() == ButtonEvent.UP)
				setPreset(L_LEFT_DOWN);
		}

		else if (getPreset() == L_RIGHT_DOWNMAX) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(L_LEFT_DOWNMAX);
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(L_RIGHTMAX_DOWNMAX);
			if (e.getKey() == ButtonEvent.UP)
				setPreset(L_RIGHT_DOWN);
		}

		else if (getPreset() == L_RIGHTMAX_DOWNMAX) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(L_RIGHT_DOWNMAX);
			if (e.getKey() == ButtonEvent.UP)
				setPreset(L_RIGHTMAX_DOWN);
		}
	}

}
