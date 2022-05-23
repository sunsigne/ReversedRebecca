package com.sunsigne.reversedrebecca.menu;

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

	private TitleScreenButton createTitleScreenButton(String text, int x, GenericListener onPress) {
		return new TitleScreenButton(text, x, 940, 420, 140, onPress, null);
	}

	private void createPlayButton() {
		GenericListener onPress = () -> startWorld();
		String text = new Translatable().getTranslatedText("PlayButton", file);
		ButtonObject button = createTitleScreenButton(text, 140, onPress);
		LAYER.MENU.addObject(button);
	}

	private void createOptionsButton() {
		GenericListener onPress = null;
		String text = new Translatable().getTranslatedText("OptionsButton", file);
		ButtonObject button = createTitleScreenButton(text, 740, onPress);
		LAYER.MENU.addObject(button);
	}

	private void createQuitButton() {
		GenericListener onPress = () -> new Conductor().stopApp();
		String text = new Translatable().getTranslatedText("QuitButton", file);
		ButtonObject button = createTitleScreenButton(text, 1340, onPress);
		LAYER.MENU.addObject(button);
	}

	private void createFlagLanguageButton() {
		GenericListener onPress = () -> openLanguageScreen();
		ButtonObject button = new FlagLangageButton(onPress, null);
		LAYER.MENU.addObject(button);
	}

	////////// WORLD ////////////

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

	private void openLanguageScreen() {
		LAYER.MENU.getHandler().clear();
		new LanguageScreen();
	}

}
