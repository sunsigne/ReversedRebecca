package com.sunsigne.reversedrebecca.menu.submenu;

import java.util.HashMap;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenTextDynamic;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.camera.CameraOption;
import com.sunsigne.reversedrebecca.system.camera.CameraOption.CAMERA_TYPE;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public class VideoScreen extends SubMenuScreen {

	public VideoScreen(PresetMousePos defaultPreset) {
		super(defaultPreset);
		loadText();

		createLeftArrowButton(DIRECTION.LEFT);
		createRightArrowButton(DIRECTION.RIGHT);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "video";
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

	private void loadText() {
		String text = null;
		int x = 325 + 416;
		int y = 503;

		// camera
		camera = new TitleScreenText(translate("Camera"), x, y + 51);
		LAYER.MENU.addObject(camera);

		// static / dynamic
		String typeName = CameraOption.getType().getName();
		text = translate("Camera" + typeName);
		cameraType = new TitleScreenTextDynamic(translate("Camera" + typeName), x, y + 155);
		LAYER.MENU.addObject(cameraType);

		cameraDetail = new TitleScreenText[2];

		// the camera follows the player ...
		text = translate("Camera" + "Detail");
		cameraDetail[0] = new TitleScreenText(text, x, y + 245);
		cameraDetail[0].setFontSize(18f);
		LAYER.MENU.addObject(cameraDetail[0]);

		// ... to the nearest pixel / fluidly
		text = translate(typeName + "Detail");
		cameraDetail[1] = new TitleScreenText(text, x, y + 280);
		cameraDetail[1].setFontSize(18f);
		LAYER.MENU.addObject(cameraDetail[1]);
	}

	////////// BUTTONS ////////////

	private void createArrowButton(String text, DIRECTION direction, int x, GenericListener onPress) {
		ButtonObject button = new TitleScreenButton(text, 710 + x, 670, 60, 60, onPress, null);
		((TitleScreenButton) button).setFontSize(40f);
		arrow_buttons.put(direction, button);
		LAYER.MENU.addObject(button);
	}

	private void createLeftArrowButton(DIRECTION direction) {
		GenericListener onPress = () -> choosePreviousCameraType();
		createArrowButton("<", direction, 0, onPress);
	}

	private void createRightArrowButton(DIRECTION direction) {
		GenericListener onPress = () -> chooseNextCameraType();
		createArrowButton(">", direction, 420, onPress);
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

	private void refresh() {
		String typeName = CameraOption.getType().getName();
		cameraType.setText(translate("Camera" + typeName));
		cameraDetail[1].setText(translate(typeName + "Detail"));
	}

	////////// PRESET MOUSE POS ////////////
	
	private HashMap<DIRECTION, ButtonObject> arrow_buttons = new HashMap<>();
	
	public static final PresetMousePos CAMERA = new PresetMousePos(925, 700);
	
	////////// GAMEPAD ////////////
	
	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;
	
		if (isPresetNull())
			setPreset(CAMERA);
		else if (getPreset() == CAMERA)
			cameraPressed(e);
		else if (getPreset() == BACK)
			backPressed(e);
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
		
		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);
	}

	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(CAMERA);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}

	
}
