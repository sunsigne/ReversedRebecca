package com.sunsigne.reversedrebecca.object.characteristics.interactive;

import java.awt.Color;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.upgrade.UpgradePlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.DifficultyComparator;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionThreeKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionTwoKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.Key;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.KeyAnalyzer;

public abstract class Action {

	public Action(Interactive interactive, String name, GenericListener listener, Key key, int keyEvent) {
		this(interactive, name, null, null, listener, key, keyEvent);
	}

	public Action(Interactive interactive, String name, ToolPlayer toolPlayer, UpgradePlayer upgradePlayer, GenericListener listener, Key key,
			int keyEvent) {
		this.interactive = interactive;
		this.name = name;
		this.toolPlayer = toolPlayer;
		this.upgradePlayer = upgradePlayer;
		this.listener = listener;
		setTextColor(TEXT_COLOR.WHITE);
		setKeyEvent(key, keyEvent);
	}

	private Interactive interactive;

	public Interactive getInteractive() {
		return interactive;
	}

	////////// NAME ////////////

	private String name;

	public void setName(String name) {
		this.name = name;
	}

	////////// INTERACTION ////////////

	private ToolPlayer toolPlayer;

	protected void setToolPlayer(ToolPlayer toolPlayer) {
		this.toolPlayer = toolPlayer;
	}

	private UpgradePlayer upgradePlayer;

	public void setUpgradePlayer(UpgradePlayer upgradePlayer) {
		this.upgradePlayer = upgradePlayer;
	}

	private GenericListener listener;

	public void setListener(GenericListener listener) {
		this.listener = listener;
	}

	public boolean canDoAction() {

		// no tool is required
		if (toolPlayer == null)
			return true;

		// the object has no difficulty registered
		if (getInteractive() instanceof Difficulty == false) {
			return true;
		}

		// comparaison between the object's level and the tool's level
		Difficulty difficultyObject = (Difficulty) getInteractive();
		boolean canUseTool = new DifficultyComparator().canUseTool(difficultyObject.getDifficulty(),
				toolPlayer.getDifficulty());

		// no upgrade is required
		if (upgradePlayer == null)
			return canUseTool;

		if (upgradePlayer.getValue()) {
			return canUseTool;
		}

		return false;
	}

	public void doAction() {
		if (listener == null)
			return;

		if (canDoAction()) {
			listener.doAction();
			paralysePlayer();
		}
	}

	public void forceAction() {
		if (listener == null)
			return;

		listener.doAction();
	}

	private void paralysePlayer() {
		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		if (player.canInteract() == false)
			return;

		player.setCanInteract(false, true);
		new GameTimer(3, () -> {
			if (player.getFakingCanInteract())
				player.setCanInteract(true);
		});

	}

	////////// TEXT COLOR ////////////

	public enum TEXT_COLOR {
		WHITE("white", Color.WHITE), YELLOW("yellow", new Color(255, 220, 0)), RED("red", new Color(255, 0, 0));

		private String name;
		private Color color;

		TEXT_COLOR(String name, Color color) {
			this.name = name;
			this.color = color;
		}

		public String getName() {
			return name;
		}

		public Color getColor() {
			return color;
		}
	}

	private TEXT_COLOR textColor;

	public TEXT_COLOR getTextColor() {
		return textColor;
	}

	public void setTextColor(TEXT_COLOR textColor) {
		this.textColor = textColor;
	}

	////////// RENDER ////////////

	public String getDisplayedText() {
		String key = "[" + new KeyAnalyzer(getKeyEvent()).getKeyText() + "]";
		if (ControllerManager.getInstance().isUsingGamepad())
			key = "  ";

		return key + " " + name.toUpperCase();
	}

	////////// KEYBOARD ////////////

	private Key key;
	private int keyEvent;

	public int getKeyEvent() {
		if (key == null)
			return keyEvent;

		if (key.getValueToRead().contains("1")) {
			if (ActionOneKey.getKey() != keyEvent)
				keyEvent = ActionOneKey.getKey();
		}

		if (key.getValueToRead().contains("2")) {
			if (ActionTwoKey.getKey() != keyEvent)
				keyEvent = ActionTwoKey.getKey();
		}

		if (key.getValueToRead().contains("3")) {
			if (ActionThreeKey.getKey() != keyEvent)
				keyEvent = ActionThreeKey.getKey();
		}

		return keyEvent;
	}

	public void setKeyEvent(Key key, int keyEvent) {
		this.key = key;
		this.keyEvent = keyEvent;

		if (keyEvent == ActionOneKey.getKey())
			buttonEvent = ActionOneKey.getGamepadKey();
		if (keyEvent == ActionTwoKey.getKey())
			buttonEvent = ActionTwoKey.getGamepadKey();
		if (keyEvent == ActionThreeKey.getKey())
			buttonEvent = ActionThreeKey.getGamepadKey();
	}

	////////// GAMEPAD ////////////

	private int buttonEvent;

	public int getButtonEvent() {
		return buttonEvent;
	}

}
