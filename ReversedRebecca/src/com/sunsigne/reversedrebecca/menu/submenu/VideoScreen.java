package com.sunsigne.reversedrebecca.menu.submenu;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.camera.CameraOption;
import com.sunsigne.reversedrebecca.system.camera.CameraOption.CAMERA_TYPE;

public class VideoScreen extends SubMenuScreen {

	public VideoScreen() {
		super();
		loadText();

		createLeftArrowButton();
		createRightArrowButton();
	}

	////////// NAME ////////////

	public String getName() {
		return "video";
	}

	////////// SUB MENU ////////////

	protected MenuScreen getPreviousMenu() {
		return new OptionsScreen();
	}

	////////// TEXT ////////////

	private TitleScreenText camera;
	private TitleScreenText cameraType;
	private TitleScreenText [] cameraDetail;
	
	private void loadText() {
		String text = null;
		int x = 325 + 416;
		int y = 503;
		
		// camera
		text = new Translatable().getTranslatedText("Camera", file);
		camera = new TitleScreenText(text, x, y + 51);
		LAYER.MENU.addObject(camera);
		
		// static / dynamic ...
		String typeName = CameraOption.getType().getName();
		text = new Translatable().getTranslatedText("Camera" + typeName, file);
		cameraType = new TitleScreenText(text, x, y + 155);
		LAYER.MENU.addObject(cameraType);
		
		cameraDetail = new TitleScreenText [2];
		
		// the camera follows the player ...
		text = new Translatable().getTranslatedText("Camera" + "Detail", file);
		cameraDetail[0] = new TitleScreenText(text, x, y + 245);
		cameraDetail[0].setFontSize(18f);
		LAYER.MENU.addObject(cameraDetail[0]);
		
		// ... to the nearest pixel / fluidly ...
		text = new Translatable().getTranslatedText(typeName + "Detail", file);
		cameraDetail[1] = new TitleScreenText(text, x, y + 280);
		cameraDetail[1].setFontSize(18f);
		LAYER.MENU.addObject(cameraDetail[1]);
	}

	////////// BUTTONS ////////////

	private void createArrowButton(String text, int x, GenericListener onPress) {
		ButtonObject button = new TitleScreenButton(text, 710 + x, 670, 60, 60, onPress, null);
		((TitleScreenButton) button).setFontSize(40f);
		LAYER.MENU.addObject(button);
	}

	private void createLeftArrowButton() {
		GenericListener onPress = () -> choosePreviousCameraType();
		createArrowButton("<", 0, onPress);
	}

	private void createRightArrowButton() {
		GenericListener onPress = () -> chooseNextCameraType();
		createArrowButton(">", 420, onPress);
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
		String text = null;
		
		text = new Translatable().getTranslatedText("Camera" + typeName, file);
		cameraType.setText(text);
		
		text = new Translatable().getTranslatedText(typeName + "Detail", file);
		cameraDetail[1].setText(text);
	}

}
