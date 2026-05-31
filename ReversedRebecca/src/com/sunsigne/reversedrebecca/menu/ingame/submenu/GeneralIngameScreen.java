package com.sunsigne.reversedrebecca.menu.ingame.submenu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.sunsigne.reversedrebecca.menu.ingame.MenuIngameScreen;
import com.sunsigne.reversedrebecca.object.buttons.ActionOptionPreview;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenTextSelectable;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.ActionOption;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.ActionOption.ACTION_DESIGN;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.ActionOption.ACTION_HIGHLIGHT;
import com.sunsigne.reversedrebecca.object.hud.InventoryOption;
import com.sunsigne.reversedrebecca.object.hud.InventoryOption.INVENTORY_TYPE;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.font.TextsOption;
import com.sunsigne.reversedrebecca.ressources.font.TextsOption.TEXTS_SIZE;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.ShakeOption;
import com.sunsigne.reversedrebecca.system.ShakeOption.SHAKE_TYPE;
import com.sunsigne.reversedrebecca.system.camera.CameraOption;
import com.sunsigne.reversedrebecca.system.camera.CameraOption.CAMERA_TYPE;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public class GeneralIngameScreen extends MenuIngameSubMenuScreen implements SheetableImage {

	public GeneralIngameScreen() {
		super(ACTION_HIGHLIGHT);
		loadText();

		createLeftArrowButton(DIRECTION.LEFT);
		createRightArrowButton(DIRECTION.RIGHT);
		createResetButton();
	}

	protected static final int y_gap = -60;

	////////// NAME ////////////

	@Override
	public String getName() {
		return "general_xxl";
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuIngameScreen getPreviousMenu() {
		return new OptionsIngameScreen(BACK);
	}

	////////// TEXT ////////////

	private TitleScreenText camera;
	private TitleScreenText cameraType;
	private TitleScreenText[] cameraDetail;

	private TitleScreenText shake;
	private TitleScreenText shakeType;

	private TitleScreenText inventory;
	private TitleScreenText inventoryType;

	private TitleScreenText action;
	private TitleScreenText actionHighlight;
	private TitleScreenText actionDesign;
	private TitleScreenText actionSize;
	private ActionOptionPreview actionPreview;

	private static final int gap = -278;

	private void loadText() {
		String text = null;
		int x = 325 + 416 - 269;
		int y = 323 + y_gap;

		// camera
		camera = new TitleScreenText(translate("Camera"), x - gap, y);
		LAYER.MENU.addObject(camera);

		// static / dynamic
		String typeName = CameraOption.getType().getName();
		text = translate("Camera" + typeName);
		cameraType = new TitleScreenTextSelectable(translate("Camera" + typeName), x - gap, y + 104);
		LAYER.MENU.addObject(cameraType);

		cameraDetail = new TitleScreenText[2];

		// the camera follows the player ...
		text = translate("Camera" + "Detail");
		cameraDetail[0] = new TitleScreenText(text, x - gap, y + 185);
		cameraDetail[0].setFontSize(18f);
		LAYER.MENU.addObject(cameraDetail[0]);

		// ... to the nearest pixel / fluidly
		text = translate(typeName + "Detail");
		cameraDetail[1] = new TitleScreenText(text, x - gap, y + 219);
		cameraDetail[1].setFontSize(18f);
		LAYER.MENU.addObject(cameraDetail[1]);

		// shake
		shake = new TitleScreenText(translate("Shake"), x - 3 * gap, y);
		LAYER.MENU.addObject(shake);

		// on / off
		typeName = ShakeOption.getType().getName();
		text = translate("Shake" + typeName);
		shakeType = new TitleScreenTextSelectable(translate("Shake" + typeName), x - 3 * gap, y + 104);
		LAYER.MENU.addObject(shakeType);

		// inventory
		inventory = new TitleScreenText(translate("Inventory"), x - 3 * gap, y + 208);
		LAYER.MENU.addObject(inventory);

		// immersive / visible
		typeName = InventoryOption.getType().getName();
		text = translate("Inventory" + typeName);
		inventoryType = new TitleScreenTextSelectable(translate("Inventory" + typeName), x - 3 * gap, y + 312);
		LAYER.MENU.addObject(inventoryType);

		// action
		action = new TitleScreenText(translate("Action"), x + gap, y);
		LAYER.MENU.addObject(action);

		// highlight
		String highlightName = ActionOption.getHighlight().getName();
		text = translate("Action" + highlightName);
		actionHighlight = new TitleScreenTextSelectable(translate("Action" + highlightName), x + gap, y + 104);
		LAYER.MENU.addObject(actionHighlight);

		// color / number
		String designName = ActionOption.getDesign().getName();
		text = translate("Action" + designName);
		actionDesign = new TitleScreenTextSelectable(translate("Action" + designName), x + gap, y + 208);
		LAYER.MENU.addObject(actionDesign);

		// small / medium / large
		String sizeName = TextsOption.getType().getName();
		text = translate("Action" + sizeName);
		actionSize = new TitleScreenTextSelectable(translate("Action" + sizeName), x + gap, y + 312);
		LAYER.MENU.addObject(actionSize);

		// preview of the action
		actionPreview = new ActionOptionPreview(x + gap + 225, y + 403 + 10);
		LAYER.MENU.addObject(actionPreview);

		TitleScreenText resetDetail;

		// your progress will be ...
		text = translate("ResetDetail" + "1");
		resetDetail = new TitleScreenText(text, x - gap, y + 362 + 79);
		resetDetail.setFontSize(18f);
		LAYER.MENU.addObject(resetDetail);

		// ... permanently lost
		text = translate("ResetDetail" + "2");
		resetDetail = new TitleScreenText(text, x - gap, y + 362 + 113);
		resetDetail.setFontSize(18f);
		LAYER.MENU.addObject(resetDetail);

	}

	////////// BUTTONS ////////////

	private ButtonObject resetButton;

	private void createArrowButton(String text, DIRECTION direction, int x, int y, GenericListener onPress) {
		ButtonObject button = new TitleScreenButton(text, 710 - 269 + x, 439 + y + y_gap, 60, 60, onPress, null);
		((TitleScreenButton) button).setFontSize(40f);
		arrow_buttons.put(direction, button);
		LAYER.MENU.addObject(button);
	}

	private void createLeftArrowButton(DIRECTION direction) {
		GenericListener onPress = null;

		onPress = () -> choosePreviousCameraType();
		createArrowButton("<", direction, 0 - gap, 0, onPress);
		onPress = () -> choosePreviousShakeType();
		createArrowButton("<", direction, 0 - 3 * gap, 0, onPress);
		onPress = () -> choosePreviousInventoryType();
		createArrowButton("<", direction, 0 - 3 * gap, 208, onPress);

		onPress = () -> choosePreviousActionHighlight();
		createArrowButton("<", direction, 0 + gap, 0, onPress);
		onPress = () -> choosePreviousActionDesign();
		createArrowButton("<", direction, 0 + gap, 104, onPress);
		onPress = () -> choosePreviousActionSize();
		createArrowButton("<", direction, 0 + gap, 208, onPress);
	}

	private void createRightArrowButton(DIRECTION direction) {
		GenericListener onPress = null;

		onPress = () -> chooseNextCameraType();
		createArrowButton(">", direction, 420 - gap, 0, onPress);
		onPress = () -> chooseNextShakeType();
		createArrowButton(">", direction, 420 - 3 * gap, 0, onPress);
		onPress = () -> chooseNextInventoryType();
		createArrowButton(">", direction, 420 - 3 * gap, 208, onPress);

		onPress = () -> chooseNextActionHighlight();
		createArrowButton(">", direction, 420 + gap, 0, onPress);
		onPress = () -> chooseNextActionDesign();
		createArrowButton(">", direction, 420 + gap, 104, onPress);
		onPress = () -> chooseNextActionSize();
		createArrowButton(">", direction, 420 + gap, 208, onPress);
	}

	private void createResetButton() {
		LAYER.MENU.getHandler().removeObject(resetButton);

		GenericListener onPress = () -> createConfirmButton();
		resetButton = new TitleScreenButton(translate("Reset"), 741 - 269 - gap, 371 + 312 + y_gap, 415, 80, onPress,
				null) {

			@Override
			public String getSound() {
				return "button_validate";
			}
		};

		LAYER.MENU.addObject(resetButton);
		buttons.put(RESET, resetButton);
	}

	private void createConfirmButton() {
		LAYER.MENU.getHandler().removeObject(resetButton);

		GenericListener onPress = () -> new ResetIngameScreen();
		resetButton = new TitleScreenButton(translate("Confirm"), 741 - 269 - gap, 371 + 312 + y_gap, 415, 80, onPress,
				null) {

			@Override
			public String getSound() {
				return "button_validate";
			}
		};

		LAYER.MENU.addObject(resetButton);
		buttons.put(RESET, resetButton);
	}

	////////// BUTTON ACTION ////////////

	private void choosePreviousCameraType() {
		CAMERA_TYPE camera_type = CameraOption.getType().getPrevious();
		new CameraOption().registerType(camera_type);
		refresh();
	}

	private void chooseNextCameraType() {
		CAMERA_TYPE camera_type = CameraOption.getType().getNext();
		new CameraOption().registerType(camera_type);
		refresh();
	}

	private void choosePreviousShakeType() {
		SHAKE_TYPE shake_type = ShakeOption.getType().getPrevious();
		new ShakeOption().registerType(shake_type);
		new ShakeOption().forceShake();
		refresh();
	}

	private void chooseNextShakeType() {
		SHAKE_TYPE shake_type = ShakeOption.getType().getNext();
		new ShakeOption().registerType(shake_type);
		new ShakeOption().forceShake();
		refresh();
	}

	private void choosePreviousInventoryType() {
		INVENTORY_TYPE inventory_type = InventoryOption.getType().getPrevious();
		new InventoryOption().registerType(inventory_type);
		refresh();
	}

	private void chooseNextInventoryType() {
		INVENTORY_TYPE inventory_type = InventoryOption.getType().getNext();
		new InventoryOption().registerType(inventory_type);
		refresh();
	}

	private void choosePreviousActionHighlight() {
		ACTION_HIGHLIGHT action_highlight = ActionOption.getHighlight().getPrevious();
		new ActionOption().registerHighlight(action_highlight);
		refresh();
	}

	private void chooseNextActionHighlight() {
		ACTION_HIGHLIGHT action_highlight = ActionOption.getHighlight().getNext();
		new ActionOption().registerHighlight(action_highlight);
		refresh();
	}

	private void choosePreviousActionDesign() {
		ACTION_DESIGN action_design = ActionOption.getDesign().getPrevious();
		new ActionOption().registerDesign(action_design);
		refresh();
	}

	private void chooseNextActionDesign() {
		ACTION_DESIGN action_design = ActionOption.getDesign().getNext();
		new ActionOption().registerDesign(action_design);
		refresh();
	}

	private void choosePreviousActionSize() {
		TEXTS_SIZE texts_size = TextsOption.getType().getPrevious();
		new TextsOption().registerType(texts_size);
		refresh();
	}

	private void chooseNextActionSize() {
		TEXTS_SIZE texts_size = TextsOption.getType().getNext();
		new TextsOption().registerType(texts_size);
		refresh();
	}

	private void refresh() {
		String typeName = CameraOption.getType().getName();
		cameraType.setText(translate("Camera" + typeName));
		cameraDetail[1].setText(translate(typeName + "Detail"));

		typeName = ShakeOption.getType().getName();
		shakeType.setText(translate("Shake" + typeName));
		typeName = InventoryOption.getType().getName();
		inventoryType.setText(translate("Inventory" + typeName));

		String highlightName = ActionOption.getHighlight().getName();
		actionHighlight.setText(translate("Action" + highlightName));
		String designName = ActionOption.getDesign().getName();
		actionDesign.setText(translate("Action" + designName));
		String sizeName = TextsOption.getType().getName();
		actionSize.setText(translate("Action" + sizeName));

		createResetButton();
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 2;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	@Override
	public int getSheetWidth() {
		return 3 * 16;
	}
	
	private BufferedImage gamepad_instruction_image;

	protected BufferedImage get_gamepad_instruction_image() {
		if (gamepad_instruction_image == null)
			gamepad_instruction_image = new ImageTask().loadImage("textures/menu/" + getName() + "_gamepad_instruction",
					true);
		return gamepad_instruction_image;
	}

	private BufferedImage inventory_image;

	protected BufferedImage get_inventory_image() {
		if (inventory_image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/hud/inventory");
			inventory_image = getSheetSubImage(sheet);
		}

		return inventory_image;
	}
	
	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		super.render(g);
				
		if(InventoryOption.getType() == INVENTORY_TYPE.VISIBLE)
			g.drawImage(get_inventory_image(), 890 - gap + 158, 725 + y_gap, 3*128, 128, null);
				
		if (ControllerManager.getInstance().isUsingGamepad() == false)
			return;

		g.drawImage(get_gamepad_instruction_image(), 890 + gap, 313 + y_gap, 120, 120, null);
		g.drawImage(get_gamepad_instruction_image(), 890 - gap, 313 + y_gap, 120, 120, null);
	}

	////////// PRESET MOUSE POS ////////////

	private HashMap<DIRECTION, ButtonObject> arrow_buttons = new HashMap<>();

	public static final PresetMousePos ACTION_HIGHLIGHT = new PresetMousePos(925 - 269 + gap, 460 + y_gap);
	public static final PresetMousePos ACTION_DESIGN = new PresetMousePos(925 - 269 + gap, 570 + y_gap);
	public static final PresetMousePos ACTION_SIZE = new PresetMousePos(925 - 269 + gap, 670 + y_gap);
	public static final PresetMousePos CAMERA = new PresetMousePos(925 - 269 - gap, 460 + y_gap);
	public static final PresetMousePos RESET = new PresetMousePos(925 - 269 - gap, 720 + y_gap);
	public static final PresetMousePos SHAKE = new PresetMousePos(925 - 269 - 3 * gap, 460 + y_gap);
	public static final PresetMousePos INVENTORY = new PresetMousePos(925 - 269 - 3 * gap, 670 + y_gap);

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;

		if (isPresetNull())
			setPreset(ACTION_HIGHLIGHT);
		else if (e.getKey() == ButtonEvent.B) {
			setPreset(BACK, false);
			buttons.get(BACK).mousePressed(null);
		}

		else if (getPreset() == ACTION_HIGHLIGHT)
			actionHighlightPressed(e);
		else if (getPreset() == ACTION_DESIGN)
			actionDesignPressed(e);
		else if (getPreset() == ACTION_SIZE)
			actionSizePressed(e);
		else if (getPreset() == CAMERA)
			cameraPressed(e);
		else if (getPreset() == SHAKE)
			shakePressed(e);
		else if (getPreset() == INVENTORY)
			inventoryPressed(e);
		else if (getPreset() == RESET)
			resetPressed(e);
		else if (getPreset() == BACK)
			backPressed(e);

	}

	private void actionHighlightPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT) {
			var sound = arrow_buttons.get(DIRECTION.LEFT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			choosePreviousActionHighlight();
		}

		else if (e.getKey() == ButtonEvent.RIGHT) {
			var sound = arrow_buttons.get(DIRECTION.RIGHT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			chooseNextActionHighlight();
		}

		else if (e.getKey() == ButtonEvent.UP) {
			setPreset(CAMERA);
		}

		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(ACTION_DESIGN);
	}

	private void actionDesignPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT) {
			var sound = arrow_buttons.get(DIRECTION.LEFT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			choosePreviousActionDesign();
		}

		else if (e.getKey() == ButtonEvent.RIGHT) {
			var sound = arrow_buttons.get(DIRECTION.RIGHT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			chooseNextActionDesign();
		}

		else if (e.getKey() == ButtonEvent.UP) {
			setPreset(ACTION_HIGHLIGHT);
		}

		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(ACTION_SIZE);
	}

	private void actionSizePressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT) {
			var sound = arrow_buttons.get(DIRECTION.LEFT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			choosePreviousActionSize();
		}

		else if (e.getKey() == ButtonEvent.RIGHT) {
			var sound = arrow_buttons.get(DIRECTION.RIGHT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			chooseNextActionSize();
		}

		else if (e.getKey() == ButtonEvent.UP) {
			setPreset(ACTION_DESIGN);
		}

		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);
	}

	private void cameraPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT) {
			var sound = arrow_buttons.get(DIRECTION.LEFT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			choosePreviousCameraType();
		}

		else if (e.getKey() == ButtonEvent.RIGHT) {
			var sound = arrow_buttons.get(DIRECTION.RIGHT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			chooseNextCameraType();
		}

		else if (e.getKey() == ButtonEvent.UP) {
			setPreset(SHAKE);
		}

		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(RESET);
	}

	private void shakePressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT) {
			var sound = arrow_buttons.get(DIRECTION.LEFT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			choosePreviousShakeType();
		}

		else if (e.getKey() == ButtonEvent.RIGHT) {
			var sound = arrow_buttons.get(DIRECTION.RIGHT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			chooseNextShakeType();
		}

		else if (e.getKey() == ButtonEvent.UP) {
			setPreset(ACTION_HIGHLIGHT);
		}

		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(INVENTORY);
	}

	private void inventoryPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT) {
			var sound = arrow_buttons.get(DIRECTION.LEFT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			choosePreviousInventoryType();
		}

		else if (e.getKey() == ButtonEvent.RIGHT) {
			var sound = arrow_buttons.get(DIRECTION.RIGHT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			chooseNextInventoryType();
		}

		else if (e.getKey() == ButtonEvent.UP) {
			setPreset(SHAKE);
		}

		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);
	}

	private void resetPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP) {
			setPreset(CAMERA);
		}

		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);

		else if (e.getKey() == ButtonEvent.A)
			buttons.get(RESET).mousePressed(null);
	}

	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(ACTION_SIZE);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}

}
