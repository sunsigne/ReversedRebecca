package com.sunsigne.reversedrebecca.menu.submenu;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

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
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public class TutorialScreen extends SubMenuScreen {

	public TutorialScreen(GenericListener startWorld) {
		super(PLAY);
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
		int y = 503 - 25;

		GenericListener onPress = () -> startTutorial(startWorld);
		ButtonObject button = new TitleScreenButton(translate("YesButton"), x - gap, y + 259, 160, 80, onPress, null) {

			@Override
			public String getSound() {
				return "button_validate";
			}
		};

		buttons.put(PLAY, button);
		LAYER.MENU.addObject(button);
	}

	private void createNoButton(GenericListener startWorld) {
		int x = 325 + 416 + 128;
		int y = 503 - 25;

		ButtonObject button = new TitleScreenButton(translate("NoButton"), x + gap, y + 259, 160, 80, startWorld,
				null) {

			@Override
			public String getSound() {
				return "button_validate";
			}
		};

		buttons.put(PLAY, button);
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

		int[] up_rect = new int[] { 890, 565, 120, 120 };
		int[] down_rect = new int[] { 890, 565 + 30, 120, 120 };

		g.drawImage(get_dave_image(), 890, 712, 120, 120, null);
		new TextDecoration().drawOutlinesString(g, font, tutorialDetail[0], DIRECTION.NULL, up_rect);
		new TextDecoration().drawOutlinesString(g, font, tutorialDetail[1], DIRECTION.NULL, down_rect);
	}

	////////// PRESET MOUSE POS ////////////

	private HashMap<DIRECTION, ButtonObject> arrow_buttons = new HashMap<>();

	public static final PresetMousePos DIFFICULTY = new PresetMousePos(925, 600);
	public static final PresetMousePos PLAY = new PresetMousePos(925, 800);

	////////// GAMEPAD ////////////

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;

		if (isPresetNull())
			setPreset(DIFFICULTY);
		else if (e.getKey() == ButtonEvent.B) {
			setPreset(BACK, false);
			buttons.get(BACK).mousePressed(null);
		}

		else if (getPreset() == DIFFICULTY)
			difficultyPressed(e);
		else if (getPreset() == PLAY)
			playPressed(e);
		else if (getPreset() == BACK)
			backPressed(e);
	}

	private void difficultyPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT) {
			var sound = arrow_buttons.get(DIRECTION.LEFT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			// choosePreviousGameDifficulty();
		}

		else if (e.getKey() == ButtonEvent.RIGHT) {
			var sound = arrow_buttons.get(DIRECTION.RIGHT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			// chooseNextGameDifficulty();
		}

		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(PLAY);
	}

	private void playPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(DIFFICULTY);
		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(PLAY).mousePressed(null);
	}

	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(PLAY);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}

}
