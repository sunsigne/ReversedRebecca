package com.sunsigne.reversedrebecca.menu;

import com.sunsigne.reversedrebecca.menu.submenu.DifficultyScreen;
import com.sunsigne.reversedrebecca.menu.submenu.LanguageScreen;
import com.sunsigne.reversedrebecca.menu.submenu.OptionsScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.FlagLangageButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Feeling.CONDITION;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.physic.natural.independant.FadeMenuLaw;
import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.world.World;

public class TitleScreen extends MenuScreen {

	public TitleScreen() {
		super();

		createPlayButton();
		createOptionsButton();
		createQuitButton();
		createFlagLanguageButton();
	}

	////////// BUTTONS ////////////

	private void createTitleScreenButton(String text, int x, GenericListener onPress) {
		ButtonObject button = new TitleScreenButton(text, x, 940, 420, 140, onPress, null);
		((TitleScreenButton) button).setFontSize(45f);
		LAYER.MENU.addObject(button);
	}

	private void createPlayButton() {
		GenericListener onPress = () -> playRequest();
		String text = new Translatable().getTranslatedText("PlayButton", file);
		createTitleScreenButton(text, 140, onPress);
	}

	private void createOptionsButton() {
		GenericListener onPress = () -> new OptionsScreen();
		String text = new Translatable().getTranslatedText("OptionsButton", file);
		createTitleScreenButton(text, 740, onPress);
	}

	private void createQuitButton() {
		GenericListener onPress = () -> new Conductor().stopApp();
		String text = new Translatable().getTranslatedText("QuitButton", file);
		createTitleScreenButton(text, 1340, onPress);
	}

	private void createFlagLanguageButton() {
		GenericListener onPress = () -> new LanguageScreen();
		ButtonObject button = new FlagLangageButton(onPress, null);
		LAYER.MENU.addObject(button);
	}

	////////// BUTTON ACTION ////////////

	private void playRequest() {
		String currentlvl = new Save().getLevel(false);
		GenericListener startWorld = () -> startWorld();

		if (currentlvl.equalsIgnoreCase("rebeccas_room_0"))
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

}
