package com.sunsigne.reversedrebecca.menu.submenu.bonus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.menu.submenu.BonusScreen;
import com.sunsigne.reversedrebecca.menu.submenu.SubMenuScreen;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.menu.DrawingButtonObject;
import com.sunsigne.reversedrebecca.object.menu.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.menu.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public class DrawingsScreen extends SubMenuScreen {

	public DrawingsScreen() {
		this(BACK, 0);
	}

	public DrawingsScreen(PresetMousePos defaultPreset, int listStart) {
		super(defaultPreset);
		this.listStart = listStart;

		loadText();
		createDrawings();
		createUpArrowButton(DIRECTION.UP);
		createDownArrowButton(DIRECTION.DOWN);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "drawings_xll";
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new BonusScreen(BACK);
	}

	////////// DRAWINGS ////////////

	private GameList<DrawingButtonObject> list;
	protected int listStart;

	private int getListEnd() {
		return 10;
	}

	////////// TEXT ////////////

	private Font font;
	private String text;

	private void loadText() {
		font = new FontTask().createNewFont("startedbyamouse.ttf", 85f);
		text = new Translatable().getTranslatedText("meli", FilePath.DRAWING);
	}

	private void createDrawings() {
		list = new GameList<>(LISTTYPE.ARRAY);

		if (listStart == 0) {
			createDrawingsButton("head_sock", ROW_1);
			createDrawingsButton("head_sock_color", ROW_2);
			createDrawingsButton("lockpicking", ROW_3);
			createDrawingsButton("sadistic_nerd", ROW_4);
			createDrawingsButton("alicia_hug", ROW_5);
			createDrawingsButton("dave_chair", ROW_6, -15, 50);
			createDrawingsButton("el_coffee", ROW_7, 60, -55);
			createDrawingsButton("el_donut", ROW_8, 60, -55);
			createDrawingsButton("armed_duo", ROW_9, -15, 40);
		}

		if (listStart == step) {
			createDrawingsButton("jojo_cop", ROW_1, 40, -40);
			createDrawingsButton("jojo_cop_color", ROW_2, 40, -40);
			createDrawingsButton("touchy_girls", ROW_3, 60, -55);
			createDrawingsButton("yy_pose", ROW_4);
			createDrawingsButton("checkmate", ROW_5, 30, -55);
			createDrawingsButton("android", ROW_6, 60, -55);
			createDrawingsButton("android_color", ROW_7, 60, -55);
			createDrawingsButton("cocktail", ROW_8, -40, 20);
			createDrawingsButton("hangover", ROW_9);
		}
	}

	////////// BUTTONS ////////////

	private int row;

	private void createDrawingsButton(String text, PresetMousePos preset) {
		createDrawingsButton(text, preset, 0, 0);
	}

	private void createDrawingsButton(String text, PresetMousePos preset, int x0, int y0) {
		row++;

		DrawingButtonObject button = new DrawingButtonObject(text, 250, 136 + row * 72, x0, y0);
		LAYER.MENU.addObject(button);
		buttons.put(preset, button);
		list.addObject(button);
	}

	private void createArrowButton(String text, DIRECTION direction, int y, GenericListener onPress) {
		ButtonObject button = new TitleScreenButton(text, 430, 500 + y, 60, 60, onPress, null);
		((TitleScreenButton) button).setFontSize(40f);
		arrow_buttons.put(direction, button);
		LAYER.MENU.addObject(button);
	}

	private void createUpArrowButton(DIRECTION direction) {
		if (listStart == 0)
			return;

		GenericListener onPress = () -> showPreviousDrawings();
		createArrowButton("†", direction, -355, onPress);
	}

	private void createDownArrowButton(DIRECTION direction) {
		if (listStart >= getListEnd())
			return;

		GenericListener onPress = () -> showNextDrawings();
		// this charactere got modified to looks like a down arrow
		createArrowButton("‡", direction, 350, onPress);
	}

	////////// BUTTON ACTION ////////////

	private int step = 10;

	private void showNextDrawings() {
		new DrawingsScreen(ROW_1, listStart + step);
	}

	private void showPreviousDrawings() {
		new DrawingsScreen(ROW_9, listStart - step);
	}

	////////// TICK ////////////

	private boolean thereIsTheCat;
	private DrawingButtonObject lastSelectedButton;

	@Override
	public void tick() {
		for (var tempButton : list.getList()) {
			if (tempButton.isSelected() == false)
				continue;

			thereIsTheCat = tempButton.isLocked();

			if (tempButton != lastSelectedButton) {
				lastSelectedButton = tempButton;

				if (ControllerManager.getInstance().isUsingGamepad() == false)
					new SoundTask().playSound(SOUNDTYPE.SOUND, "button");
			}

			return;
		}

		thereIsTheCat = true;
		lastSelectedButton = null;
	}

	////////// TEXTURE ////////////

	private BufferedImage catImage;

	private BufferedImage getCatImage() {
		if (catImage == null)
			catImage = new ImageTask().loadImage("bonus/drawings/" + "melichat");
		return catImage;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		super.render(g);

		var img = getCatImage();

		if (thereIsTheCat == false)
			return;

		int w = img.getWidth();
		int h = img.getHeight();
		g.drawImage(img, 820, 170, w, h, null);

		if (lastSelectedButton == null)
			drawText(g);
	}

	private void drawText(Graphics g) {
		if (text.contains("@") == false) {
			drawLine(g, text, 0);
			return;
		}

		String[] content = text.split("@");
		for (int index = 0; index < content.length; index++) {
			drawLine(g, content[index], 120 * index);
		}
	}

	private void drawLine(Graphics g, String text, int h) {
		int[] rect = new int[] { 735, 290, 800, h };
		new TextDecoration().drawCenteredString(g, font, text, Color.BLACK, DIRECTION.NULL, rect);
	}

	@Override
	protected void drawTitle(Graphics g) {

	}

	@Override
	protected void drawVersion(Graphics g) {

	}

	////////// PRESET MOUSE POS ////////////

	private HashMap<DIRECTION, ButtonObject> arrow_buttons = new HashMap<>();

	private static final int PRESET_GAP = 72;
	public static final PresetMousePos ROW_1 = new PresetMousePos(450, 250);
	public static final PresetMousePos ROW_2 = new PresetMousePos(450, 250 + PRESET_GAP);
	public static final PresetMousePos ROW_3 = new PresetMousePos(450, 250 + 2 * PRESET_GAP);
	public static final PresetMousePos ROW_4 = new PresetMousePos(450, 250 + 3 * PRESET_GAP);
	public static final PresetMousePos ROW_5 = new PresetMousePos(450, 250 + 4 * PRESET_GAP);
	public static final PresetMousePos ROW_6 = new PresetMousePos(450, 250 + 5 * PRESET_GAP);
	public static final PresetMousePos ROW_7 = new PresetMousePos(450, 250 + 6 * PRESET_GAP);
	public static final PresetMousePos ROW_8 = new PresetMousePos(450, 250 + 7 * PRESET_GAP);
	public static final PresetMousePos ROW_9 = new PresetMousePos(450, 250 + 8 * PRESET_GAP);

	////////// GAMEPAD ////////////

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;

		if (isPresetNull())
			setPreset(BACK);
		else if (e.getKey() == ButtonEvent.B) {
			setPreset(BACK, false);
			buttons.get(BACK).mousePressed(null);
		}

		else if (getPreset() == ROW_1) {
			firstRowPressed(e);
		} else if (getPreset() == ROW_2) {
			if (e.getKey() == ButtonEvent.UP)
				setPreset(ROW_1);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(ROW_3);
		} else if (getPreset() == ROW_3) {
			if (e.getKey() == ButtonEvent.UP)
				setPreset(ROW_2);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(ROW_4);
		} else if (getPreset() == ROW_4) {
			if (e.getKey() == ButtonEvent.UP)
				setPreset(ROW_3);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(ROW_5);
		} else if (getPreset() == ROW_5) {
			if (e.getKey() == ButtonEvent.UP)
				setPreset(ROW_4);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(ROW_6);
		} else if (getPreset() == ROW_6) {
			if (e.getKey() == ButtonEvent.UP)
				setPreset(ROW_5);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(ROW_7);
		} else if (getPreset() == ROW_7) {
			if (e.getKey() == ButtonEvent.UP)
				setPreset(ROW_6);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(ROW_8);
		} else if (getPreset() == ROW_8) {
			if (e.getKey() == ButtonEvent.UP)
				setPreset(ROW_7);
			if (e.getKey() == ButtonEvent.DOWN)
				setPreset(ROW_9);
		} else if (getPreset() == ROW_9) {
			lastRowPressed(e);
		} else if (getPreset() == BACK)
			backPressed(e);
	}

	private void firstRowPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP) {
			if (listStart > 0) {
				showPreviousDrawings();
				new SoundTask().playSound(SOUNDTYPE.SOUND, "gamepad");
			}
		} else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(ROW_2);
	}

	private void lastRowPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(ROW_8);
		else if (e.getKey() == ButtonEvent.DOWN) {
			if (listStart >= getListEnd())
				setPreset(BACK);
			else {
				showNextDrawings();
				new SoundTask().playSound(SOUNDTYPE.SOUND, "gamepad");
			}
		}
	}

	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
		else if (e.getKey() == ButtonEvent.UP) {
			if (listStart == 0)
				setPreset(ROW_1);
			else
				setPreset(ROW_9);
		}
	}

}
