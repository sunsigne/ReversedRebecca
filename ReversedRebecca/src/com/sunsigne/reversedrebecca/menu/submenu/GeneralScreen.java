package com.sunsigne.reversedrebecca.menu.submenu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.ActionOptionPreview;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenTextSelectable;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.ActionOption;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.ActionOption.ACTION_DESIGN;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.ActionOption.ACTION_HIGHLIGHT;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.font.TextsOption;
import com.sunsigne.reversedrebecca.ressources.font.TextsOption.TEXTS_SIZE;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.camera.CameraOption;
import com.sunsigne.reversedrebecca.system.camera.CameraOption.CAMERA_TYPE;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public class GeneralScreen extends SubMenuScreen {

	public GeneralScreen() {
		super(ACTION);
		loadText();

		createLeftArrowButton(DIRECTION.LEFT);
		createRightArrowButton(DIRECTION.RIGHT);
		createResetButton();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "general_xl";
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new OptionsScreen(BACK);
	}

	////////// TEXT ////////////

	private TitleScreenText camera;
	private TitleScreenText cameraType;
	private TitleScreenText[] cameraDetail;

	private TitleScreenText action;
	private TitleScreenText actionHighlight;
	private TitleScreenText actionDesign;
	private TitleScreenText actionSize;
	private ActionOptionPreview actionPreview;

	private static final int gap = -278;

	private void loadText() {
		String text = null;
		int x = 325 + 416;
		int y = 323;

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

	private void createArrowButton(String text, DIRECTION direction, int x, int y, GenericListener onPress) {
		ButtonObject button = new TitleScreenButton(text, 710 + x, 439 + y, 60, 60, onPress, null);
		((TitleScreenButton) button).setFontSize(40f);
		arrow_buttons.put(direction, button);
		LAYER.MENU.addObject(button);
	}

	private void createLeftArrowButton(DIRECTION direction) {
		GenericListener onPress = null;

		onPress = () -> choosePreviousCameraType();
		createArrowButton("<", direction, 0 - gap, 0, onPress);

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

		onPress = () -> chooseNextActionHighlight();
		createArrowButton(">", direction, 420 + gap, 0, onPress);
		onPress = () -> chooseNextActionDesign();
		createArrowButton(">", direction, 420 + gap, 104, onPress);
		onPress = () -> chooseNextActionSize();
		createArrowButton(">", direction, 420 + gap, 208, onPress);
	}

	private void createResetButton() {
		GenericListener onPress = () -> new ResetScreen();
		ButtonObject button = new TitleScreenButton(translate("Reset"), 741 - gap, 371 + 312, 415, 80, onPress, null) {
			
			@Override
			public String getSound() {
				return "button_validate";
			}
		};
		
		LAYER.MENU.addObject(button);
		//buttons.put(RESET, button);
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

		String highlightName = ActionOption.getHighlight().getName();
		actionHighlight.setText(translate("Action" + highlightName));
		String designName = ActionOption.getDesign().getName();
		actionDesign.setText(translate("Action" + designName));
		String sizeName = TextsOption.getType().getName();
		actionSize.setText(translate("Action" + sizeName));
	}

	////////// TEXTURE ////////////

	private BufferedImage gamepad_instruction_image;

	protected BufferedImage get_gamepad_instruction_image() {
		if (gamepad_instruction_image == null)
			gamepad_instruction_image = new ImageTask().loadImage("textures/menu/" + getName() + "_gamepad_instruction",
					true);
		return gamepad_instruction_image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		super.render(g);
		if (ControllerManager.getInstance().isUsingGamepad())
			g.drawImage(get_gamepad_instruction_image(), 890, 575, 120, 120, null);
	}

	////////// PRESET MOUSE POS ////////////

	private HashMap<DIRECTION, ButtonObject> arrow_buttons = new HashMap<>();

	public static final PresetMousePos CAMERA = new PresetMousePos(925 - gap, 700);
	public static final PresetMousePos ACTION = new PresetMousePos(925 + gap, 700);

	////////// GAMEPAD ////////////

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;
		
		if (isPresetNull())
			setPreset(ACTION);
		else if (e.getKey() == ButtonEvent.B) {
			setPreset(BACK, false);
			buttons.get(BACK).mousePressed(null);
		}

		else if (getPreset() == CAMERA)
			cameraPressed(e);
		else if (getPreset() == ACTION)
			actionPressed(e);
		else if (getPreset() == BACK)
			backPressed(e);
			
	}

	private void actionPressed(ButtonEvent e) {
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
			setPreset(CAMERA);
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
			setPreset(ACTION);
		}

		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);
	}

	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(ACTION);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}

}
