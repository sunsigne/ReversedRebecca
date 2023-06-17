package com.sunsigne.reversedrebecca.menu.submenu;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.KeyToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolList;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.pattern.render.RectDecoration.RECTSIZE;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public class TutorialScreen extends SubMenuScreen {

	public TutorialScreen(GenericListener startWorld) {
		super(NO);
		getBackButton().removeObject();
		loadText();

		createYesButton(startWorld);
		createNoButton(startWorld);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "tutorial";
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new TitleScreen(TitleScreen.PLAY);
	}

	////////// TEXT ////////////

	private Font font;
	private String[] tutorialDetail;

	private void loadText() {
		tutorialDetail = new String[2];
		tutorialDetail[0] = translate("tutorial" + "Detail" + "1");
		tutorialDetail[1] = translate("tutorial" + "Detail" + "2");

		float size = 22f / (float) Math.sqrt(Window.SCALE_X);
		font = new FontTask().createNewFont("square_sans_serif_7.ttf", size * 1.3f);
	}

	////////// BUTTONS ////////////

	private static final int gap = 183;

	private void createYesButton(GenericListener startWorld) {
		int x = 325 + 416 + 128;
		int y = 503 + 25;

		GenericListener onPress = () -> startTutorial(startWorld);
		ButtonObject button = new TitleScreenButton(translate("YesButton"), x - gap, y + 259, 160, 80, onPress, null) {

			@Override
			public String getSound() {
				return "button_validate";
			}
		};

		buttons.put(YES, button);
		button.setRectsize(RECTSIZE.CUSTOM_TUTORIAL_BUTTON);
		LAYER.MENU.addObject(button);
	}

	private void createNoButton(GenericListener startWorld) {
		int x = 325 + 416 + 128;
		int y = 503 + 25;

		ButtonObject button = new TitleScreenButton(translate("NoButton"), x + gap, y + 259, 160, 80, startWorld,
				null) {

			@Override
			public String getSound() {
				return "button_validate";
			}
		};

		buttons.put(NO, button);
		button.setRectsize(RECTSIZE.CUSTOM_TUTORIAL_BUTTON);
		LAYER.MENU.addObject(button);
	}

	////////// BUTTON ACTION ////////////

	private void startTutorial(GenericListener startWorld) {
		Save save = new Save();
		save.registerNextLevel(save.getLevel(true), FilePath.TUTORIAL);

		ToolPlayer key = ToolList.getList().getObject(new KeyToolPlayer());
		key.setMaxDifficulty(LVL.GREEN);
		save.updateCharacteristics();

		startWorld.doAction();
	}

	////////// TEXTURE ////////////

	private String getCharacter() {
		return "dave";
	}

	private BufferedImage dave_image;

	protected BufferedImage get_dave_image() {
		if (dave_image == null)
			dave_image = new ImageTask().loadImage("textures/characters/" + getCharacter() + "/" + "standing_down");
		return dave_image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		super.render(g);

		if (font == null)
			return;

		int[] up_rect = new int[] { 890, 615, 120, 120 };
		int[] down_rect = new int[] { 890, 615 + 30, 120, 120 };

		g.drawImage(get_dave_image(), 890, 762, 120, 120, null);
		new TextDecoration().drawOutlinesString(g, font, tutorialDetail[0], DIRECTION.NULL, up_rect);
		new TextDecoration().drawOutlinesString(g, font, tutorialDetail[1], DIRECTION.NULL, down_rect);
	}

	////////// PRESET MOUSE POS ////////////

	public static final PresetMousePos YES = new PresetMousePos(325 + 416 + 128 - gap + 80, 503 + 25 + 259 + 40);
	public static final PresetMousePos NO = new PresetMousePos(325 + 416 + 128 + gap + 80, 503 + 25 + 259 + 40);

	////////// GAMEPAD ////////////

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;

		if (isPresetNull())
			setPreset(NO);

		else if (getPreset() == YES)
			yesPressed(e);
		else if (getPreset() == NO)
			noPressed(e);
	}

	private void yesPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.RIGHT)
			setPreset(NO);

		else if (e.getKey() == ButtonEvent.A)
			buttons.get(YES).mousePressed(null);
	}

	private void noPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT)
			setPreset(YES);

		else if (e.getKey() == ButtonEvent.A)
			buttons.get(NO).mousePressed(null);
	}

}
