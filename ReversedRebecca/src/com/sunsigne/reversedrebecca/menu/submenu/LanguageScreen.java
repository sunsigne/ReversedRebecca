package com.sunsigne.reversedrebecca.menu.submenu;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import com.sunsigne.reversedrebecca.menu.LoadingScreen;
import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.pattern.FormattedString;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.ressources.lang.Language;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;
import com.sunsigne.reversedrebecca.world.World;

public class LanguageScreen extends SubMenuScreen {

	public LanguageScreen() {
		super(BACK);
		loadLangList();

		createLangButtons();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		while (lang_list == null)
			loadLangList();

		return "lang_" + new FormattedString().getNumber(lang_list.size());
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new TitleScreen(TitleScreen.FLAG);
	}

	////////// LANGUAGE ////////////

	private ArrayList<String> lang_list;

	private void loadLangList() {
		File file = new File(FilePath.RESSOURCES_PATH + "texts");
		var file_list = new ArrayList<String>(Arrays.asList(file.list()));
		lang_list = new ArrayList<String>();

		file_list.forEach(tempfile -> {
			if (tempfile.contains(".") == false) // check if folder or file
				lang_list.add(tempfile);
		});

		while (lang_list.size() > 12) // max size is 12
			lang_list.remove(11);

		if (lang_list.size() < 2) // less than 2 langs (which is not possible)
			stopApp();
	}

	private void stopApp() {
		new SoundTask().playSound(SOUNDTYPE.ERROR, "error");
		JOptionPane.showMessageDialog(null, "An error has occurred, some languages files are missing");
		new Conductor().stopApp();
	}

	////////// BUTTONS ////////////

	private void createLangButtons() {
		switch (lang_list.size()) {

		case 2:
			createLangButton(0, L12_C2R2, 416, 104);
			createLangButton(1, L12_C2R3, 416, 208);
			break;

		case 3:
			createLangButton(0, L9_C2R1, 416, 51);
			createLangButton(1, L9_C2R2, 416, 155);
			createLangButton(2, L9_C2R3, 416, 259);
			break;

		case 4:
			createButtonRow(0, 1, L8_C1R2, L8_C2R2, 104);
			createButtonRow(2, 3, L8_C1R3, L8_C2R3, 208);
			break;

		case 5:
			createButtonRow(0, 1, L6_C1R1, L6_C2R1, 51);
			createButtonRow(2, 3, L6_C1R2, L6_C1R2, 155);
			createLangButton(4, L9_C2R3, 416, 259);
			break;

		case 6:
			createButtonRow(0, 1, L6_C1R1, L6_C2R1, 51);
			createButtonRow(2, 3, L6_C1R2, L6_C2R2, 155);
			createButtonRow(4, 5, L6_C1R3, L6_C2R3, 259);
			break;

		case 7:
			createButtonRow(0, 1, L8_C1R1, L8_C2R1, 0);
			createButtonRow(2, 3, L8_C1R2, L8_C2R2, 104);
			createButtonRow(4, 5, L8_C1R3, L8_C2R3, 208);
			createLangButton(6, L12_C2R4, 416, 312);
			break;

		case 8:
			createButtonRow(0, 1, L8_C1R1, L8_C2R1, 0);
			createButtonRow(2, 3, L8_C1R2, L8_C2R2, 104);
			createButtonRow(4, 5, L8_C1R3, L8_C2R3, 208);
			createButtonRow(6, 7, L8_C1R4, L8_C2R4, 312);
			break;

		case 9:
			createButtonRow(0, 1, 2, L9_C1R1, L9_C2R1, L9_C3R1, 51);
			createButtonRow(3, 4, 5, L9_C1R2, L9_C2R2, L9_C3R2, 155);
			createButtonRow(6, 7, 8, L9_C1R3, L9_C2R3, L9_C3R3, 259);
			break;

		case 10:
			createButtonRow(0, 1, 2, L12_C1R1, L12_C2R1, L12_C3R1, 0);
			createButtonRow(3, 4, 5, L12_C1R2, L12_C2R2, L12_C3R2, 104);
			createButtonRow(6, 7, 8, L12_C1R3, L12_C2R3, L12_C3R3, 208);
			createLangButton(9, L12_C2R4, 416, 312);
			break;

		case 11:
			createButtonRow(0, 1, 2, L12_C1R1, L12_C2R1, L12_C3R1, 0);
			createButtonRow(3, 4, 5, L12_C1R2, L12_C2R2, L12_C3R2, 104);
			createButtonRow(6, 7, 8, L12_C1R3, L12_C2R3, L12_C3R3, 208);
			createButtonRow(9, 10, L8_C1R4, L8_C2R4, 312);
			break;

		case 12:
			createButtonRow(0, 1, 2, L12_C1R1, L12_C2R1, L12_C3R1, 0);
			createButtonRow(3, 4, 5, L12_C1R2, L12_C2R2, L12_C3R2, 104);
			createButtonRow(6, 7, 8, L12_C1R3, L12_C2R3, L12_C3R3, 208);
			createButtonRow(9, 10, 11, L12_C1R4, L12_C2R4, L12_C3R4, 312);
			break;
		}
	}

	private void createButtonRow(int index1, int index2, PresetMousePos preset1, PresetMousePos preset2, int y) {
		createLangButton(index1, preset1, 206, y);
		createLangButton(index2, preset2, 623, y);
	}

	private void createButtonRow(int index1, int index2, int index3, PresetMousePos preset1, PresetMousePos preset2,
			PresetMousePos preset3, int y) {
		createLangButton(index1, preset1, 0, y);
		createLangButton(index2, preset2, 416, y);
		createLangButton(index3, preset3, 833, y);
	}

	private void createLangButton(int index, PresetMousePos preset, int x, int y) {
		String name = lang_list.get(index);
		GenericListener onPress = () -> chooseLanguage(name);
		String text = new FileTask().read(false, "texts/" + name + "/lang/name" + ".txt");

		ButtonObject button = new TitleScreenButton(text, x + 325, y + 503, 415, 80, onPress, null) {

			@Override
			public String getSound() {
				return "button_validate";
			}
		};

		if (Language.getInstance().getLang().contentEquals(name))
			loadGamepadSetup(preset);

		buttons.put(preset, button);
		LAYER.MENU.getHandler().addObject(button);
	}

	////////// BUTTON ACTION ////////////

	private void chooseLanguage(String language) {
		Language.getInstance().setLang(language);
		if (new Save().getLevel(true).equalsIgnoreCase(new Save().getLevel(false)))
			reloadWorld();
		new TitleScreen(TitleScreen.PLAY);
	}

	private void reloadWorld() {
		LAYER.LOADING.addObject(new LoadingScreen());
		World.get().destroy();
	}

	////////// PRESET MOUSE POS ////////////

	public static final PresetMousePos L6_C1R1 = new PresetMousePos(735, 590);
	public static final PresetMousePos L6_C2R1 = new PresetMousePos(1155, 590);
	public static final PresetMousePos L6_C1R2 = new PresetMousePos(735, 700);
	public static final PresetMousePos L6_C2R2 = new PresetMousePos(1155, 700);
	public static final PresetMousePos L6_C1R3 = new PresetMousePos(735, 805);
	public static final PresetMousePos L6_C2R3 = new PresetMousePos(1155, 805);

	public static final PresetMousePos L8_C1R1 = new PresetMousePos(735, 540);
	public static final PresetMousePos L8_C2R1 = new PresetMousePos(1155, 540);
	public static final PresetMousePos L8_C1R2 = new PresetMousePos(735, 645);
	public static final PresetMousePos L8_C2R2 = new PresetMousePos(1155, 645);
	public static final PresetMousePos L8_C1R3 = new PresetMousePos(735, 750);
	public static final PresetMousePos L8_C2R3 = new PresetMousePos(1155, 750);
	public static final PresetMousePos L8_C1R4 = new PresetMousePos(735, 855);
	public static final PresetMousePos L8_C2R4 = new PresetMousePos(1155, 855);

	public static final PresetMousePos L9_C1R1 = new PresetMousePos(525, 590);
	public static final PresetMousePos L9_C2R1 = new PresetMousePos(945, 590);
	public static final PresetMousePos L9_C3R1 = new PresetMousePos(1365, 590);
	public static final PresetMousePos L9_C1R2 = new PresetMousePos(525, 700);
	public static final PresetMousePos L9_C2R2 = new PresetMousePos(945, 700);
	public static final PresetMousePos L9_C3R2 = new PresetMousePos(1365, 700);
	public static final PresetMousePos L9_C1R3 = new PresetMousePos(525, 805);
	public static final PresetMousePos L9_C2R3 = new PresetMousePos(945, 805);
	public static final PresetMousePos L9_C3R3 = new PresetMousePos(1365, 805);

	public static final PresetMousePos L12_C1R1 = new PresetMousePos(525, 540);
	public static final PresetMousePos L12_C2R1 = new PresetMousePos(945, 540);
	public static final PresetMousePos L12_C3R1 = new PresetMousePos(1365, 540);
	public static final PresetMousePos L12_C1R2 = new PresetMousePos(525, 645);
	public static final PresetMousePos L12_C2R2 = new PresetMousePos(945, 645);
	public static final PresetMousePos L12_C3R2 = new PresetMousePos(1365, 645);
	public static final PresetMousePos L12_C1R3 = new PresetMousePos(525, 750);
	public static final PresetMousePos L12_C2R3 = new PresetMousePos(945, 750);
	public static final PresetMousePos L12_C3R3 = new PresetMousePos(1365, 750);
	public static final PresetMousePos L12_C1R4 = new PresetMousePos(525, 855);
	public static final PresetMousePos L12_C2R4 = new PresetMousePos(945, 855);
	public static final PresetMousePos L12_C3R4 = new PresetMousePos(1365, 855);

	////////// GAMEPAD ////////////

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;

		if (isPresetNull())
			setPreset(getDefaultPreset());
		else if (e.getKey() == ButtonEvent.B) {
			setPreset(BACK, false);
			buttons.get(BACK).mousePressed(null);
		}

		else if (e.getKey() == ButtonEvent.A)
			buttons.get(getPreset()).mousePressed(null);

		else {
			switch (lang_list.size()) {
			case 2:
				buttonPressed02(e);
				break;
			case 3:
				buttonPressed03(e);
				break;
			case 4:
				buttonPressed04(e);
				break;
			case 5:
				buttonPressed05(e);
				break;
			case 6:
				buttonPressed06(e);
				break;
			case 7:
				buttonPressed07(e);
				break;
			case 8:
				buttonPressed08(e);
				break;
			case 9:
				buttonPressed09(e);
				break;
			case 10:
				buttonPressed10(e);
				break;
			case 11:
				buttonPressed11(e);
				break;
			case 12:
				buttonPressed12(e);
				break;
			}
		}
	}

	private void buttonPressed02(ButtonEvent e) {

		if (e.getKey() == ButtonEvent.UP) {
			if (getPreset() == BACK)
				setPreset(L12_C2R3);
			else if (getPreset() == L12_C2R3)
				setPreset(L12_C2R2);
		}

		else if (e.getKey() == ButtonEvent.DOWN) {
			if (getPreset() == L12_C2R2)
				setPreset(L12_C2R3);
			else if (getPreset() == L12_C2R3)
				setPreset(BACK);
		}
	}

	private void buttonPressed03(ButtonEvent e) {

		if (e.getKey() == ButtonEvent.UP) {
			if (getPreset() == BACK)
				setPreset(L9_C2R3);
			else if (getPreset() == L9_C2R3)
				setPreset(L9_C2R2);
			else if (getPreset() == L9_C2R2)
				setPreset(L9_C2R1);
		}

		else if (e.getKey() == ButtonEvent.DOWN) {
			if (getPreset() == L9_C2R1)
				setPreset(L9_C2R2);
			else if (getPreset() == L9_C2R2)
				setPreset(L9_C2R3);
			else if (getPreset() == L9_C2R3)
				setPreset(BACK);
		}
	}

	private void buttonPressed04(ButtonEvent e) {

		if (e.getKey() == ButtonEvent.LEFT) {
			if (getPreset() == L8_C2R2)
				setPreset(L8_C1R2);
			else if (getPreset() == L8_C2R3)
				setPreset(L8_C1R3);
		}

		else if (e.getKey() == ButtonEvent.RIGHT) {
			if (getPreset() == L8_C1R2)
				setPreset(L8_C2R2);
			else if (getPreset() == L8_C1R3)
				setPreset(L8_C2R3);
		}

		else if (e.getKey() == ButtonEvent.UP) {
			if (getPreset() == BACK)
				setPreset(L8_C1R3);
			else if (getPreset() == L8_C1R3)
				setPreset(L8_C1R2);
			else if (getPreset() == L8_C2R3)
				setPreset(L8_C2R2);
		}

		else if (e.getKey() == ButtonEvent.DOWN) {
			if (getPreset() == L8_C1R2)
				setPreset(L8_C1R3);
			else if (getPreset() == L8_C2R2)
				setPreset(L8_C2R3);
			else if (getPreset() == L8_C1R3 || getPreset() == L8_C2R3)
				setPreset(BACK);
		}
	}

	private void buttonPressed05(ButtonEvent e) {

		if (e.getKey() == ButtonEvent.LEFT) {
			if (getPreset() == L6_C2R1)
				setPreset(L6_C1R1);
			else if (getPreset() == L6_C2R2)
				setPreset(L6_C1R2);
		}

		else if (e.getKey() == ButtonEvent.RIGHT) {
			if (getPreset() == L6_C1R1)
				setPreset(L6_C2R1);
			else if (getPreset() == L6_C1R2)
				setPreset(L6_C2R2);
		}

		else if (e.getKey() == ButtonEvent.UP) {
			if (getPreset() == BACK)
				setPreset(L9_C2R3);
			else if (getPreset() == L9_C2R3)
				setPreset(L6_C1R2);
			else if (getPreset() == L6_C1R2)
				setPreset(L6_C1R1);
			else if (getPreset() == L6_C2R2)
				setPreset(L6_C2R1);
		}

		else if (e.getKey() == ButtonEvent.DOWN) {
			if (getPreset() == L6_C1R1)
				setPreset(L6_C1R2);
			else if (getPreset() == L6_C2R1)
				setPreset(L6_C2R2);
			else if (getPreset() == L6_C1R2 || getPreset() == L6_C2R2)
				setPreset(L9_C2R3);
			else if (getPreset() == L9_C2R3)
				setPreset(BACK);
		}
	}

	private void buttonPressed06(ButtonEvent e) {

		if (e.getKey() == ButtonEvent.LEFT) {
			if (getPreset() == L6_C2R1)
				setPreset(L6_C1R1);
			else if (getPreset() == L6_C2R2)
				setPreset(L6_C1R2);
			else if (getPreset() == L6_C2R3)
				setPreset(L6_C1R3);
		}

		else if (e.getKey() == ButtonEvent.RIGHT) {
			if (getPreset() == L6_C1R1)
				setPreset(L6_C2R1);
			else if (getPreset() == L6_C1R2)
				setPreset(L6_C2R2);
			else if (getPreset() == L6_C1R3)
				setPreset(L6_C2R3);
		}

		else if (e.getKey() == ButtonEvent.UP) {
			if (getPreset() == BACK)
				setPreset(L6_C1R3);
			else if (getPreset() == L6_C1R3)
				setPreset(L6_C1R2);
			else if (getPreset() == L6_C1R2)
				setPreset(L6_C1R1);
			else if (getPreset() == L6_C2R3)
				setPreset(L6_C2R2);
			else if (getPreset() == L6_C2R2)
				setPreset(L6_C2R1);
		}

		else if (e.getKey() == ButtonEvent.DOWN) {
			if (getPreset() == L6_C1R1)
				setPreset(L6_C1R2);
			else if (getPreset() == L6_C1R2)
				setPreset(L6_C1R3);
			else if (getPreset() == L6_C2R1)
				setPreset(L6_C2R2);
			else if (getPreset() == L6_C2R2)
				setPreset(L6_C2R3);
			else if (getPreset() == L6_C1R3 || getPreset() == L6_C2R3)
				setPreset(BACK);
		}
	}

	private void buttonPressed07(ButtonEvent e) {

		if (e.getKey() == ButtonEvent.LEFT) {
			if (getPreset() == L8_C2R1)
				setPreset(L8_C1R1);
			else if (getPreset() == L8_C2R2)
				setPreset(L8_C1R2);
			else if (getPreset() == L8_C2R3)
				setPreset(L8_C1R3);
		}

		else if (e.getKey() == ButtonEvent.RIGHT) {
			if (getPreset() == L8_C1R1)
				setPreset(L8_C2R1);
			else if (getPreset() == L8_C1R2)
				setPreset(L8_C2R2);
			else if (getPreset() == L8_C1R3)
				setPreset(L8_C2R3);
		}

		else if (e.getKey() == ButtonEvent.UP) {
			if (getPreset() == BACK)
				setPreset(L12_C2R4);
			else if (getPreset() == L12_C2R4)
				setPreset(L8_C1R3);
			else if (getPreset() == L8_C1R3)
				setPreset(L8_C1R2);
			else if (getPreset() == L8_C1R2)
				setPreset(L8_C1R1);
			else if (getPreset() == L8_C2R3)
				setPreset(L8_C2R2);
			else if (getPreset() == L8_C2R2)
				setPreset(L8_C2R1);
		}

		else if (e.getKey() == ButtonEvent.DOWN) {
			if (getPreset() == L8_C1R1)
				setPreset(L8_C1R2);
			else if (getPreset() == L8_C1R2)
				setPreset(L8_C1R3);
			else if (getPreset() == L8_C2R1)
				setPreset(L8_C2R2);
			else if (getPreset() == L8_C2R2)
				setPreset(L8_C2R3);
			else if (getPreset() == L8_C1R3 || getPreset() == L8_C2R3)
				setPreset(L12_C2R4);
			else if (getPreset() == L12_C2R4)
				setPreset(BACK);
		}
	}

	private void buttonPressed08(ButtonEvent e) {

		if (e.getKey() == ButtonEvent.LEFT) {
			if (getPreset() == L8_C2R1)
				setPreset(L8_C1R1);
			else if (getPreset() == L8_C2R2)
				setPreset(L8_C1R2);
			else if (getPreset() == L8_C2R3)
				setPreset(L8_C1R3);
			else if (getPreset() == L8_C2R4)
				setPreset(L8_C1R4);
		}

		else if (e.getKey() == ButtonEvent.RIGHT) {
			if (getPreset() == L8_C1R1)
				setPreset(L8_C2R1);
			else if (getPreset() == L8_C1R2)
				setPreset(L8_C2R2);
			else if (getPreset() == L8_C1R3)
				setPreset(L8_C2R3);
			else if (getPreset() == L8_C1R4)
				setPreset(L8_C2R4);
		}

		else if (e.getKey() == ButtonEvent.UP) {
			if (getPreset() == BACK)
				setPreset(L8_C1R4);
			else if (getPreset() == L8_C1R4)
				setPreset(L8_C1R3);
			else if (getPreset() == L8_C1R3)
				setPreset(L8_C1R2);
			else if (getPreset() == L8_C1R2)
				setPreset(L8_C1R1);
			else if (getPreset() == L8_C2R4)
				setPreset(L8_C2R3);
			else if (getPreset() == L8_C2R3)
				setPreset(L8_C2R2);
			else if (getPreset() == L8_C2R2)
				setPreset(L8_C2R1);
		}

		else if (e.getKey() == ButtonEvent.DOWN) {
			if (getPreset() == L8_C1R1)
				setPreset(L8_C1R2);
			else if (getPreset() == L8_C1R2)
				setPreset(L8_C1R3);
			else if (getPreset() == L8_C1R3)
				setPreset(L8_C1R4);
			else if (getPreset() == L8_C2R1)
				setPreset(L8_C2R2);
			else if (getPreset() == L8_C2R2)
				setPreset(L8_C2R3);
			else if (getPreset() == L8_C2R3)
				setPreset(L8_C2R4);
			else if (getPreset() == L8_C1R4 || getPreset() == L8_C2R4)
				setPreset(BACK);
		}
	}

	private void buttonPressed09(ButtonEvent e) {

		if (e.getKey() == ButtonEvent.LEFT) {
			if (getPreset() == L9_C3R1)
				setPreset(L9_C2R1);
			else if (getPreset() == L9_C2R1)
				setPreset(L9_C1R1);
			else if (getPreset() == L9_C3R2)
				setPreset(L9_C2R2);
			else if (getPreset() == L9_C2R2)
				setPreset(L9_C1R2);
			else if (getPreset() == L9_C3R3)
				setPreset(L9_C2R3);
			else if (getPreset() == L9_C2R3)
				setPreset(L9_C1R3);
		}

		else if (e.getKey() == ButtonEvent.RIGHT) {
			if (getPreset() == L9_C1R1)
				setPreset(L9_C2R1);
			else if (getPreset() == L9_C2R1)
				setPreset(L9_C3R1);
			else if (getPreset() == L9_C1R2)
				setPreset(L9_C2R2);
			else if (getPreset() == L9_C2R2)
				setPreset(L9_C3R2);
			else if (getPreset() == L9_C1R3)
				setPreset(L9_C2R3);
			else if (getPreset() == L9_C2R3)
				setPreset(L9_C3R3);
		}

		else if (e.getKey() == ButtonEvent.UP) {
			if (getPreset() == BACK)
				setPreset(L9_C2R3);
			else if (getPreset() == L9_C1R3)
				setPreset(L9_C1R2);
			else if (getPreset() == L9_C1R2)
				setPreset(L9_C1R1);
			else if (getPreset() == L9_C2R3)
				setPreset(L9_C2R2);
			else if (getPreset() == L9_C2R2)
				setPreset(L9_C2R1);
			else if (getPreset() == L9_C3R3)
				setPreset(L9_C3R2);
			else if (getPreset() == L9_C3R2)
				setPreset(L9_C3R1);
		}

		else if (e.getKey() == ButtonEvent.DOWN) {
			if (getPreset() == L9_C1R1)
				setPreset(L9_C1R2);
			else if (getPreset() == L9_C1R2)
				setPreset(L9_C1R3);
			else if (getPreset() == L9_C2R1)
				setPreset(L9_C2R2);
			else if (getPreset() == L9_C2R2)
				setPreset(L9_C2R3);
			else if (getPreset() == L9_C3R1)
				setPreset(L9_C3R2);
			else if (getPreset() == L9_C3R2)
				setPreset(L9_C3R3);
			else if (getPreset() == L9_C1R3 || getPreset() == L9_C2R3 || getPreset() == L9_C3R3)
				setPreset(BACK);
		}
	}

	private void buttonPressed10(ButtonEvent e) {

		if (e.getKey() == ButtonEvent.LEFT) {
			if (getPreset() == L12_C3R1)
				setPreset(L12_C2R1);
			else if (getPreset() == L12_C2R1)
				setPreset(L12_C1R1);
			else if (getPreset() == L12_C3R2)
				setPreset(L12_C2R2);
			else if (getPreset() == L12_C2R2)
				setPreset(L12_C1R2);
			else if (getPreset() == L12_C3R3)
				setPreset(L12_C2R3);
			else if (getPreset() == L12_C2R3)
				setPreset(L12_C1R3);
		}

		else if (e.getKey() == ButtonEvent.RIGHT) {
			if (getPreset() == L12_C1R1)
				setPreset(L12_C2R1);
			else if (getPreset() == L12_C2R1)
				setPreset(L12_C3R1);
			else if (getPreset() == L12_C1R2)
				setPreset(L12_C2R2);
			else if (getPreset() == L12_C2R2)
				setPreset(L12_C3R2);
			else if (getPreset() == L12_C1R3)
				setPreset(L12_C2R3);
			else if (getPreset() == L12_C2R3)
				setPreset(L12_C3R3);
		}

		else if (e.getKey() == ButtonEvent.UP) {
			if (getPreset() == BACK)
				setPreset(L12_C2R4);
			else if (getPreset() == L12_C2R4)
				setPreset(L12_C2R3);
			else if (getPreset() == L12_C1R3)
				setPreset(L12_C1R2);
			else if (getPreset() == L12_C1R2)
				setPreset(L12_C1R1);
			else if (getPreset() == L12_C2R3)
				setPreset(L12_C2R2);
			else if (getPreset() == L12_C2R2)
				setPreset(L12_C2R1);
			else if (getPreset() == L12_C3R3)
				setPreset(L12_C3R2);
			else if (getPreset() == L12_C3R2)
				setPreset(L12_C3R1);
		}

		else if (e.getKey() == ButtonEvent.DOWN) {
			if (getPreset() == L12_C1R1)
				setPreset(L12_C1R2);
			else if (getPreset() == L12_C1R2)
				setPreset(L12_C1R3);
			else if (getPreset() == L12_C2R1)
				setPreset(L12_C2R2);
			else if (getPreset() == L12_C2R2)
				setPreset(L12_C2R3);
			else if (getPreset() == L12_C3R1)
				setPreset(L12_C3R2);
			else if (getPreset() == L12_C3R2)
				setPreset(L12_C3R3);
			else if (getPreset() == L12_C1R3 || getPreset() == L12_C2R3 || getPreset() == L12_C3R3)
				setPreset(L12_C2R4);
			else if (getPreset() == L12_C2R4)
				setPreset(BACK);
		}
	}

	private void buttonPressed11(ButtonEvent e) {

		if (e.getKey() == ButtonEvent.LEFT) {
			if (getPreset() == L12_C3R1)
				setPreset(L12_C2R1);
			else if (getPreset() == L12_C2R1)
				setPreset(L12_C1R1);
			else if (getPreset() == L12_C3R2)
				setPreset(L12_C2R2);
			else if (getPreset() == L12_C2R2)
				setPreset(L12_C1R2);
			else if (getPreset() == L12_C3R3)
				setPreset(L12_C2R3);
			else if (getPreset() == L12_C2R3)
				setPreset(L12_C1R3);
			else if (getPreset() == L8_C2R4)
				setPreset(L8_C1R4);
		}

		else if (e.getKey() == ButtonEvent.RIGHT) {
			if (getPreset() == L12_C1R1)
				setPreset(L12_C2R1);
			else if (getPreset() == L12_C2R1)
				setPreset(L12_C3R1);
			else if (getPreset() == L12_C1R2)
				setPreset(L12_C2R2);
			else if (getPreset() == L12_C2R2)
				setPreset(L12_C3R2);
			else if (getPreset() == L12_C1R3)
				setPreset(L12_C2R3);
			else if (getPreset() == L12_C2R3)
				setPreset(L12_C3R3);
			else if (getPreset() == L8_C1R4)
				setPreset(L8_C2R4);
		}

		else if (e.getKey() == ButtonEvent.UP) {
			if (getPreset() == BACK)
				setPreset(L8_C1R4);
			else if (getPreset() == L8_C1R4)
				setPreset(L12_C2R3);
			else if (getPreset() == L8_C2R4)
				setPreset(L12_C2R3);
			else if (getPreset() == L12_C1R3)
				setPreset(L12_C1R2);
			else if (getPreset() == L12_C1R2)
				setPreset(L12_C1R1);
			else if (getPreset() == L12_C2R3)
				setPreset(L12_C2R2);
			else if (getPreset() == L12_C2R2)
				setPreset(L12_C2R1);
			else if (getPreset() == L12_C3R3)
				setPreset(L12_C3R2);
			else if (getPreset() == L12_C3R2)
				setPreset(L12_C3R1);
		}

		else if (e.getKey() == ButtonEvent.DOWN) {
			if (getPreset() == L12_C1R1)
				setPreset(L12_C1R2);
			else if (getPreset() == L12_C1R2)
				setPreset(L12_C1R3);
			else if (getPreset() == L12_C2R1)
				setPreset(L12_C2R2);
			else if (getPreset() == L12_C2R2)
				setPreset(L12_C2R3);
			else if (getPreset() == L12_C3R1)
				setPreset(L12_C3R2);
			else if (getPreset() == L12_C3R2)
				setPreset(L12_C3R3);
			else if (getPreset() == L12_C1R3 || getPreset() == L12_C2R3)
				setPreset(L8_C1R4);
			else if (getPreset() == L12_C3R3)
				setPreset(L8_C2R4);
			else if (getPreset() == L8_C1R4 || getPreset() == L8_C2R4)
				setPreset(BACK);
		}
	}

	private void buttonPressed12(ButtonEvent e) {

		if (e.getKey() == ButtonEvent.LEFT) {
			if (getPreset() == L12_C3R1)
				setPreset(L12_C2R1);
			else if (getPreset() == L12_C2R1)
				setPreset(L12_C1R1);
			else if (getPreset() == L12_C3R2)
				setPreset(L12_C2R2);
			else if (getPreset() == L12_C2R2)
				setPreset(L12_C1R2);
			else if (getPreset() == L12_C3R3)
				setPreset(L12_C2R3);
			else if (getPreset() == L12_C2R3)
				setPreset(L12_C1R3);
			else if (getPreset() == L12_C3R4)
				setPreset(L12_C2R4);
			else if (getPreset() == L12_C2R4)
				setPreset(L12_C1R4);
		}

		else if (e.getKey() == ButtonEvent.RIGHT) {
			if (getPreset() == L12_C1R1)
				setPreset(L12_C2R1);
			else if (getPreset() == L12_C2R1)
				setPreset(L12_C3R1);
			else if (getPreset() == L12_C1R2)
				setPreset(L12_C2R2);
			else if (getPreset() == L12_C2R2)
				setPreset(L12_C3R2);
			else if (getPreset() == L12_C1R3)
				setPreset(L12_C2R3);
			else if (getPreset() == L12_C2R3)
				setPreset(L12_C3R3);
			else if (getPreset() == L12_C1R4)
				setPreset(L12_C2R4);
			else if (getPreset() == L12_C2R4)
				setPreset(L12_C3R4);
		}

		else if (e.getKey() == ButtonEvent.UP) {
			if (getPreset() == BACK)
				setPreset(L12_C2R4);
			else if (getPreset() == L12_C1R4)
				setPreset(L12_C1R3);
			else if (getPreset() == L12_C1R3)
				setPreset(L12_C1R2);
			else if (getPreset() == L12_C1R2)
				setPreset(L12_C1R1);
			else if (getPreset() == L12_C2R4)
				setPreset(L12_C2R3);
			else if (getPreset() == L12_C2R3)
				setPreset(L12_C2R2);
			else if (getPreset() == L12_C2R2)
				setPreset(L12_C2R1);
			else if (getPreset() == L12_C3R4)
				setPreset(L12_C3R3);
			else if (getPreset() == L12_C3R3)
				setPreset(L12_C3R2);
			else if (getPreset() == L12_C3R2)
				setPreset(L12_C3R1);
		}

		else if (e.getKey() == ButtonEvent.DOWN) {
			if (getPreset() == L12_C1R1)
				setPreset(L12_C1R2);
			else if (getPreset() == L12_C1R2)
				setPreset(L12_C1R3);
			else if (getPreset() == L12_C1R3)
				setPreset(L12_C1R4);
			else if (getPreset() == L12_C2R1)
				setPreset(L12_C2R2);
			else if (getPreset() == L12_C2R2)
				setPreset(L12_C2R3);
			else if (getPreset() == L12_C2R3)
				setPreset(L12_C2R4);
			else if (getPreset() == L12_C3R1)
				setPreset(L12_C3R2);
			else if (getPreset() == L12_C3R2)
				setPreset(L12_C3R3);
			else if (getPreset() == L12_C3R3)
				setPreset(L12_C3R4);
			else if (getPreset() == L12_C1R4 || getPreset() == L12_C2R4 || getPreset() == L12_C3R4)
				setPreset(BACK);
		}
	}

}
