package com.sunsigne.reversedrebecca.menu.submenu;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.camera.CameraOption;
import com.sunsigne.reversedrebecca.system.camera.CameraOption.CAMERA_TYPE;

public class VideoScreen extends SubMenuScreen {

	public VideoScreen() {
		super();
		createFakeCameraButton();
		createFakeCameraTypeButton();
		createFakeCameraDetailButton();

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

	////////// BUTTONS ////////////

	///// fake buttons /////
	
	private void createFakeButton(String text, int y) {
		ButtonObject button = new TitleScreenButton(text, 325 + 416, 503 + y, 415, 80, null, null);
		button.destroyControls();
		LAYER.MENU.addObject(button);
	}

	private void createFakeCameraButton() {
		String text = new Translatable().getTranslatedText("CameraButton", file);
		createFakeButton(text, 51);
	}

	private void createFakeCameraTypeButton() {
		String typeName = CameraOption.getType().getName();
		String text = new Translatable().getTranslatedText("Camera" + typeName, file);
		createFakeButton(text, 155);
	}

	private void createFakeCameraDetailButton() {
		String typeName = CameraOption.getType().getName();

		String[] text = new String[2];
		text[0] = new Translatable().getTranslatedText("Camera" + "Detail", file);
		text[1] = new Translatable().getTranslatedText(typeName + "Detail", file);

		ButtonObject[] button = new ButtonObject[2];
		button[0] = new TitleScreenButton(text[0], 325 + 416, 753, 415, 80, null, null);
		button[1] = new TitleScreenButton(text[1], 325 + 416, 788, 415, 80, null, null);

		for (int index = 0; index < button.length; index++) {
			button[index].destroyControls();
			((TitleScreenButton) button[index]).setFontSize(18f);
			LAYER.MENU.addObject(button[index]);
		}
	}

	///// real buttons /////
	
	private void createArrowButton(String text, int x, GenericListener onPress) {
		ButtonObject button = new TitleScreenButton(text, 710 + x, 670, 60, 60, onPress, null);
		((TitleScreenButton) button).setFontSize(40f);
		LAYER.MENU.addObject(button);
	}

	private void createLeftArrowButton() {
		CAMERA_TYPE camera_type = CameraOption.getType().getPrevious();
		GenericListener onPress = () -> chooseCameraType(camera_type);
		createArrowButton("<", 0, onPress);
	}

	private void createRightArrowButton() {
		CAMERA_TYPE camera_type = CameraOption.getType().getNext();
		GenericListener onPress = () -> chooseCameraType(camera_type);
		createArrowButton(">", 420, onPress);
	}

	////////// BUTTON ACTION ////////////

	private void chooseCameraType(CAMERA_TYPE camera_type) {
		new CameraOption().registerType(camera_type);
		refresh();
	}

	private void refresh() {
		LAYER.MENU.getHandler().clear();
		new VideoScreen();
	}

}
