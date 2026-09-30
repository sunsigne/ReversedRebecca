package com.sunsigne.reversedrebecca.menu.submenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.menu.DemoObject;
import com.sunsigne.reversedrebecca.object.menu.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingOption;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingOption.LIVING_TYPE;
import com.sunsigne.reversedrebecca.pattern.render.RectDecoration.RECTSIZE;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public class DemoScreen extends SubMenuScreen {

	public DemoScreen() {
		this(NULL);
	}

	protected DemoScreen(PresetMousePos defaultPreset) {
		super(defaultPreset);
		loadMusic();
		loadText();
		loadDemos();

		customBackButton();
	}

	private void loadMusic() {
		new SoundTask().playMusic("3_joys_and_the_truth", false, false);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "demo";
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new TitleScreen(TitleScreen.PLAY);
	}

	////////// TEXT ////////////

	private Font[] fontText;
	private String[] text;

	private void loadText() {
		TitleScreenText demo = new TitleScreenText(translate("demo"), Window.WIDHT / 2 - 208, 22);
		LAYER.MENU.addObject(demo);

		fontText = new Font[2];
		text = new String[2];

		fontText[0] = new FontTask().createNewFont("dogicabold.ttf", 25f);
		fontText[1] = new FontTask().createNewFont("dogicabold.ttf", 40f);
		text[0] = translate("Demo" + "FullGame");

		int end = getEnding();
		text[1] = translate("Demo" + "End").concat(" : ");

		if (end == 1)
			text[1] = text[1].concat(translate("Demo" + "KillergRobot"));
		else if (end == 2)
			text[1] = text[1].concat(translate("Demo" + "TwoRebeccas"));
		else if (end == 3)
			text[1] = text[1].concat(translate("Demo" + "Psychopath"));
		else if (end == 4)
			text[1] = text[1].concat(translate("Demo" + "ForeverAndEver"));
		else
			text[1] = text[1].concat(translate("Demo" + "Error"));
	}

	private int getEnding() {
		String file = "save.csv";
		boolean userData = true;

		String[] data = new FileTask().read(userData, file).split(System.getProperty("line.separator"));

		boolean foreverImpsossible = false;

		for (String tempDatum : data)
			if (tempDatum.equalsIgnoreCase("FOREVER_AND_EVER*IMPOSSIBLE"))
				foreverImpsossible = true;
		if (foreverImpsossible == false)
			return 4;

		for (String tempDatum : data) {
			if (tempDatum.equalsIgnoreCase("ANTAGONIST*DOUBLE-Y"))
				return 1;
			if (tempDatum.equalsIgnoreCase("STEPHAN*MET"))
				return 2;
			if (tempDatum.equalsIgnoreCase("PSYCHOPATH*SHOOTOUT"))
				return 3;
		}

		return 0;
	}

	////////// DEMOS ////////////

	private void loadDemos() {
		boolean female = LivingOption.getType() == LIVING_TYPE.FEMALE;
		boolean male = LivingOption.getType() == LIVING_TYPE.MALE;

		String alexia = male ? "alexia_alexis" : "alexia_alicia";
		String nolancy = male ? "nolancy_nolan" : "nolancy_nancy";
		String marichel = male ? "marichel_michel" : "marichel_marie";
		String dougly = female ? "dougly_dolly" : "dougly_doug";

		loadDemo("sarah", 0, 0, "Sequester", "PSYCHOPATH*SARAH_SEQUESTRATION");
		loadDemo("camille", 1, 0, "Awaken", "STEPHAN*MET", "AboutToKillYou", "ANTAGONIST*DOUBLE-Y");
		loadDemo("delta", 2, 0, "Princess", "STEPHAN*MET");
		loadDemo(alexia, 0, 1, "Abandoned", "ALICIA*NOBODY_FREED_HER", "Killed", "PSYCHOPATH*ALICIA_KILLED");
		loadDemo(marichel, 1, 1, "Dated", "MARIE*START_DATING");
		loadDemo(dougly, 2, 1, "Met", "DOUG*MET", "Dated", "PSYCHOPATH*SHOOTOUT", "HelpedToKill",
				"PSYCHOPATH*SHOOTOUT");
		loadDemo("nathan", 0, 2, "Met", "ANTAGONIST*DOUBLE-Y", "BeatenUp", "NATHAN*BEATEN_UP", "HelpedToKill",
				"NATHAN*POISONED_HIS_COLLEAGUE");
		loadDemo(nolancy, 1, 2, "Met", "ANTAGONIST*DOUBLE-Y", "Dated", "NANCY*KISSED");
		loadDemo("double-y", 2, 2, "Strong", "yes", "Smart", "yes", "GoodLooking", "yes");
	}

	private void loadDemo(String name, int col, int row, String... parameters) {
		DemoObject demo_object = new DemoObject(name, Size.L - 10 + col * 6 * Size.M, 421 + row * 185, parameters);
		LAYER.MENU.addObject(demo_object);
	}

	////////// BUTTONS ////////////

	@Override
	protected String getBackButtonText() {
		return translate("QuitButton");
	}

	private void customBackButton() {
		getBackButton().setRectsize(RECTSIZE.CUSTOM_BACK_BUTTON);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), 0, 0, Window.WIDHT, Window.HEIGHT, null);

		if (fontText == null || text == null)
			return;

		int[] rect = new int[] { Window.WIDHT / 2, 102, 0, 200 };
		new TextDecoration().drawShadowedString(g, fontText[0], text[0], Color.WHITE, Color.BLACK, DIRECTION.NULL,
				rect);
		rect = new int[] { Window.WIDHT / 2, 202, 0, 200 };
		new TextDecoration().drawShadowedString(g, fontText[1], text[1], Color.WHITE, Color.BLACK, DIRECTION.NULL,
				rect);
	}

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

		else if (getPreset() == BACK)
			backPressed(e);
	}

	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}

}
