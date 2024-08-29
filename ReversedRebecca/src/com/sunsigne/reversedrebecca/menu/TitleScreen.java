package com.sunsigne.reversedrebecca.menu;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolList;
import com.sunsigne.reversedrebecca.menu.submenu.AchievementsScreen;
import com.sunsigne.reversedrebecca.menu.submenu.DifficultyScreen;
import com.sunsigne.reversedrebecca.menu.submenu.LanguageScreen;
import com.sunsigne.reversedrebecca.menu.submenu.OptionsScreen;
import com.sunsigne.reversedrebecca.object.buttons.AchievementButton;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.CrashButton;
import com.sunsigne.reversedrebecca.object.buttons.FlagLangageButton;
import com.sunsigne.reversedrebecca.object.buttons.SteamButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Feeling.CONDITION;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.physic.natural.independant.FadeMenuLaw;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.ressources.lang.Language;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.system.DifficultyOption;
import com.sunsigne.reversedrebecca.system.DifficultyOption.GAME_DIFFICULTY;
import com.sunsigne.reversedrebecca.system.WebTask;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.world.World;

public class TitleScreen extends MenuScreen {

	public TitleScreen(PresetMousePos defaultPreset) {
		super(defaultPreset);
		new SoundTask().playMusic("3 Joys & the Truth", false, true);

		createPlayButton();
		createOptionsButton();
		createQuitButton();

		createFlagLanguageButton();
		createAchievementsButton();
		createCrashButton();
		createSteamButton();

		createTestMapButton();
	}

	////////// BUTTONS ////////////

	private void createTitleScreenButton(String text, PresetMousePos preset, boolean validate_sound, int x,
			GenericListener onPress) {
		String sound = validate_sound ? "button_validate" : "button";
		ButtonObject button = new TitleScreenButton(text, x, 940, 420, 140, onPress, null) {

			@Override
			public String getSound() {
				return sound;
			}
		};

		((TitleScreenButton) button).setFontSize(45f);
		LAYER.MENU.addObject(button);
		buttons.put(preset, button);
	}

	private void createPlayButton() {
		GenericListener onPress = () -> playRequest();
		createTitleScreenButton(translate("PlayButton"), PLAY, true, 140, onPress);
	}

	private void createOptionsButton() {
		GenericListener onPress = () -> new OptionsScreen(OptionsScreen.GENERAL);
		createTitleScreenButton(translate("OptionsButton"), OPTION, false, 740, onPress);
	}

	private void createQuitButton() {
		GenericListener onPress = () -> new Conductor().stopApp();
		createTitleScreenButton(translate("QuitButton"), QUIT, true, 1340, onPress);
	}

	private void createFlagLanguageButton() {
		GenericListener onPress = () -> new LanguageScreen();
		ButtonObject button = new FlagLangageButton(onPress, null);
		LAYER.MENU.addObject(button);
		buttons.put(FLAG, button);
	}

	private void createAchievementsButton() {
		GenericListener onPress = () -> new AchievementsScreen();
		ButtonObject button = new AchievementButton(onPress, null);
		LAYER.MENU.addObject(button);
		buttons.put(ACHIEVEMENT, button);
	}

	private void createCrashButton() {
		GenericListener onPress = () -> crashRequest();
		ButtonObject button = new CrashButton(onPress, null);
		LAYER.MENU.addObject(button);
	}

	private void createSteamButton() {
		GenericListener onPress = () -> new WebTask().openSteam(WebTask.STEAM_LINK, WebTask.STEAM_LINK_HTTP);
		ButtonObject button = new SteamButton(onPress, null);
		LAYER.MENU.addObject(button);
	}

	private void createTestMapButton() {
		GenericListener onPress = () -> loadTestMap();
		
		// placed exactly on the "hot water tap" in the kitchen
		ButtonObject hidden_button = new TitleScreenButton("", 105, 250, 10, 10, onPress, null);
		
		/*
		placed exactly on the "power button" of the washing machine in the bathroom
		ButtonObject hidden_button = new TitleScreenButton("", 111, 597, 10, 10, onPress, null);
		*/

		LAYER.MENU.addObject(hidden_button);
	}

	////////// BUTTON ACTION ////////////

	private void playRequest() {
		String currentlvl = new Save().getLevel(false);
		GenericListener startWorld = () -> startWorld();

		if (currentlvl.equalsIgnoreCase(FilePath.LVL000))
			new DifficultyScreen(startWorld);

		else
			startWorld.doAction();
	}

	private void startWorld() {
		String currentlvl = new Save().getLevel(false);

		// if currentlvlmenu and currentlvl are the same
		if (new Save().getLevel(true).equalsIgnoreCase(currentlvl)) {
			loadLvlMenu();
			return;
		}

		// else load classical level
		LAYER.MENU.getHandler().clear();
		new World(currentlvl);
	}

	private void loadLvlMenu() {

		// fade menu
		PhysicLaw law = PhysicList.getList().getObject(new FadeMenuLaw());
		((FadeMenuLaw) law).setFading(true);

		// user can control player
		Player player = new PlayerFinder().getPlayer();
		int time = player.getCondition() == CONDITION.BED ? 4 : 0; // if in bed, must awake first
		GenericListener listener = () -> new PlayerFinder().setUserAllowedToControlPlayer(true);
		new GameTimer(time * Game.SEC, listener);
	}

	private void crashRequest() {
		String crash_link = WebTask.CRASH_LINK_ENG;

		String lang = Language.getInstance().getLang();
		if (lang.equalsIgnoreCase("french"))
			crash_link = WebTask.CRASH_LINK_FR;

		new WebTask().openHtml(crash_link);
	}

	private void loadTestMap() {
		DifficultyOption.setDifficulty(GAME_DIFFICULTY.NORMAL);
		LAYER.MENU.getHandler().clear();
		new World(FilePath.TEST);
		ToolList.getList().getList().forEach(tempTool -> tempTool.setMaxDifficulty(LVL.RED));
	}

	////////// PRESET MOUSE POS ////////////

	public static final PresetMousePos PLAY = new PresetMousePos(325, 1000);
	public static final PresetMousePos OPTION = new PresetMousePos(925, 1000);
	public static final PresetMousePos QUIT = new PresetMousePos(1525, 1000);
	public static final PresetMousePos FLAG = new PresetMousePos(1820, 30);
	public static final PresetMousePos ACHIEVEMENT = new PresetMousePos(1820, 170);

	////////// GAMEPAD ////////////

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;

		if (isPresetNull())
			setPreset(PLAY);
		else if (getPreset() == PLAY)
			playPressed(e);
		else if (getPreset() == OPTION)
			optionPressed(e);
		else if (getPreset() == QUIT)
			quitPressed(e);
		else if (getPreset() == ACHIEVEMENT)
			achievementPressed(e);
		else if (getPreset() == FLAG)
			flagPressed(e);
	}

	private void playPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.RIGHT)
			setPreset(OPTION);
		else if (e.getKey() == ButtonEvent.UP)
			setPreset(FLAG);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(PLAY).mousePressed(null);
	}

	private void optionPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT)
			setPreset(PLAY);
		else if (e.getKey() == ButtonEvent.RIGHT)
			setPreset(QUIT);
		else if (e.getKey() == ButtonEvent.UP)
			setPreset(FLAG);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(OPTION).mousePressed(null);
	}

	private void quitPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT)
			setPreset(OPTION);
		else if (e.getKey() == ButtonEvent.RIGHT)
			setPreset(ACHIEVEMENT);
		else if (e.getKey() == ButtonEvent.UP)
			setPreset(FLAG);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(QUIT).mousePressed(null);
	}

	private void achievementPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT)
			setPreset(QUIT);
		else if (e.getKey() == ButtonEvent.UP)
			setPreset(FLAG);
		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(QUIT);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(ACHIEVEMENT).mousePressed(null);
	}

	private void flagPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT)
			setPreset(QUIT);
		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(ACHIEVEMENT);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(FLAG).mousePressed(null);
	}

}
