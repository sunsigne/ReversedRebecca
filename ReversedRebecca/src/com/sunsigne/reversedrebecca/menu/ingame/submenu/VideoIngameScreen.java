package com.sunsigne.reversedrebecca.menu.ingame.submenu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.sunsigne.reversedrebecca.menu.ingame.MenuIngameScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TextsSizePreview;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenTextSelectable;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
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

public class VideoIngameScreen extends MenuIngameSubMenuScreen {

	public VideoIngameScreen() {
		super(TEXTS);
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
	protected MenuIngameScreen getPreviousMenu() {
		// return new OptionsIngameScreen(BACK);
		return new ResumeScreen();
	}

	////////// TEXT ////////////

	private TitleScreenText camera;
	private TitleScreenText cameraType;
	private TitleScreenText[] cameraDetail;

	private TitleScreenText texts;
	private TitleScreenText textsSize;
	private TextsSizePreview textsSizePreview;

	private static final int gap = -278;

	private void loadText() {
		String text = null;
		int x = 325 + 416;
		int y = 503 + y_gap;

		// camera
		camera = new TitleScreenText(translate("Camera"), x - gap, y + 51);
		LAYER.MENU.addObject(camera);

		// static / dynamic
		String typeName = CameraOption.getType().getName();
		text = translate("Camera" + typeName);
		cameraType = new TitleScreenTextSelectable(translate("Camera" + typeName), x - gap, y + 155);
		LAYER.MENU.addObject(cameraType);

		cameraDetail = new TitleScreenText[2];

		// the camera follows the player ...
		text = translate("Camera" + "Detail");
		cameraDetail[0] = new TitleScreenText(text, x - gap, y + 245);
		cameraDetail[0].setFontSize(18f);
		LAYER.MENU.addObject(cameraDetail[0]);

		// ... to the nearest pixel / fluidly
		text = translate(typeName + "Detail");
		cameraDetail[1] = new TitleScreenText(text, x - gap, y + 280);
		cameraDetail[1].setFontSize(18f);
		LAYER.MENU.addObject(cameraDetail[1]);

		// texts
		texts = new TitleScreenText(translate("Texts"), x + gap, y + 51);
		LAYER.MENU.addObject(texts);

		// small / medium / large
		String sizeName = TextsOption.getType().getName();
		text = translate("Texts" + sizeName);
		textsSize = new TitleScreenTextSelectable(translate("Texts" + sizeName), x + gap, y + 155);
		LAYER.MENU.addObject(textsSize);

		// preview of the size
		textsSizePreview = new TextsSizePreview(x + gap + 45, y + 245 + 10);
		LAYER.MENU.addObject(textsSizePreview);
	}

	////////// BUTTONS ////////////

	private void createArrowButton(String text, DIRECTION direction, int x, GenericListener onPress) {
		ButtonObject button = new TitleScreenButton(text, 710 + x, 670 + y_gap, 60, 60, onPress, null);
		((TitleScreenButton) button).setFontSize(40f);
		arrow_buttons.put(direction, button);
		LAYER.MENU.addObject(button);
	}

	private void createLeftArrowButton(DIRECTION direction) {
		GenericListener onPress = null;

		onPress = () -> choosePreviousCameraType();
		createArrowButton("<", direction, 0 - gap, onPress);

		onPress = () -> choosePreviousTextsSize();
		createArrowButton("<", direction, 0 + gap, onPress);
	}

	private void createRightArrowButton(DIRECTION direction) {
		GenericListener onPress = null;

		onPress = () -> chooseNextCameraType();
		createArrowButton(">", direction, 420 - gap, onPress);

		onPress = () -> chooseNextTextsSize();
		createArrowButton(">", direction, 420 + gap, onPress);
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

	private void choosePreviousTextsSize() {
		TEXTS_SIZE texts_size = TextsOption.getType().getPrevious();
		new TextsOption().registerType(texts_size);
		refresh();
	}

	private void chooseNextTextsSize() {
		TEXTS_SIZE texts_size = TextsOption.getType().getNext();
		new TextsOption().registerType(texts_size);
		refresh();
	}

	private void refresh() {
		String typeName = CameraOption.getType().getName();
		cameraType.setText(translate("Camera" + typeName));
		cameraDetail[1].setText(translate(typeName + "Detail"));

		String sizeName = TextsOption.getType().getName();
		textsSize.setText(translate("Texts" + sizeName));
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
			g.drawImage(get_gamepad_instruction_image(), 890, 575 + y_gap, 120, 120, null);
	}

	////////// PRESET MOUSE POS ////////////

	private HashMap<DIRECTION, ButtonObject> arrow_buttons = new HashMap<>();

	public static final PresetMousePos CAMERA = new PresetMousePos(925 - gap, 700 + y_gap);
	public static final PresetMousePos TEXTS = new PresetMousePos(925 + gap, 700 + y_gap);

	////////// GAMEPAD ////////////

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;

		if (isPresetNull())
			setPreset(TEXTS);
		else if (e.getKey() == ButtonEvent.B) {
			setPreset(BACK, false);
			buttons.get(BACK).mousePressed(null);
		}

		else if (getPreset() == CAMERA)
			cameraPressed(e);
		else if (getPreset() == TEXTS)
			textsPressed(e);
		else if (getPreset() == BACK)
			backPressed(e);
	}

	private void textsPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT) {
			var sound = arrow_buttons.get(DIRECTION.LEFT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			choosePreviousTextsSize();
		}

		else if (e.getKey() == ButtonEvent.RIGHT) {
			var sound = arrow_buttons.get(DIRECTION.RIGHT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			chooseNextTextsSize();
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
			setPreset(TEXTS);
		}

		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);
	}

	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(TEXTS);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}

}
