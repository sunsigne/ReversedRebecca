package com.sunsigne.reversedrebecca.menu.chat;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import com.sunsigne.reversedrebecca.menu.Cutscene;
import com.sunsigne.reversedrebecca.menu.ingame.MenuIngameController;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.pattern.FormattedString;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.piranha.condition.global.TalkedCondition;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.state.FacingRequest;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.DialogueKey;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class ChatBox implements Updatable, TickFree, KeyboardEvent, GamepadEvent {

	public ChatBox(PiranhaObject object, String target, String dialogue, String tag) {
		this.object = object;
		this.value = target;

		// register the whole dialogue as an array of lines
		all_lines = selectTag(dialogue, tag);
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/menu/" + "chatbox");
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), 0, 0, Window.WIDHT, Window.HEIGHT, null);
	}

	////////// OPEN ////////////

	private PiranhaObject object;

	public void openChat() {
		World world = World.get();
		if (world != null)
			world.freeze(true);

		object.setMotionless();
		new PlayerFinder().setPlayerCanInterract(false);

		// added as first element to render behind chat content object
		LAYER.PUZZLE.getHandler().getList().add(0, this);
		Handler.updateHandlerMap(LAYER.PUZZLE.getHandler(), this);
		goToNextLine();
	}

	private String[] all_lines;
	private ChatContent content;

	private String[] selectTag(String dialogue, String tag) {
		if (tag == null)
			return dialogue.split(System.getProperty("line.separator"));

		String lines[] = dialogue.split("%");
		String line = null;

		for (int index = 0; index < lines.length; index++) {
			String tagLine = lines[index].split(System.getProperty("line.separator"))[0];
			tagLine = tagLine.replace(" ", "");
			if (tag.equalsIgnoreCase(tagLine)) {
				line = lines[index].split(tag)[1];
				break;
			}
		}

		if (line == null)
			stopApp();

		return line.split(System.getProperty("line.separator"));
	}

	private void goToNextLine() {

		LAYER.PUZZLE.getHandler().removeObject(content);

		String line = all_lines[count - 1];

		if (line.isEmpty()) {
			count++;
			goToNextLine();
			return;
		}

		if (line.split("=").length != 4)
			stopApp();

		String living_name = line.contains("=") ? line.split("=")[0] : "error";
		String facing = line.contains("=") ? line.split("=")[1] : "down";
		// authorize words like "player" or "object"
		String formated_living_name = new FormattedString().getName(object, living_name);
		String formated_facing = new FormattedString().getName(object, facing);

		String mood = line.contains("=") ? line.split("=")[2] : "neutral";
		String text = line.contains("=") ? line.split("=")[3] : line;

		content = new ChatContent(formated_living_name, mood, text);
		LAYER.PUZZLE.addObject(content);

		Request instruction = RequestList.getList().getObject(new FacingRequest());
		var handler = object.getHandler();

		for (Updatable tempUpdatable : handler.getList()) {
			if (tempUpdatable instanceof LivingObject == false)
				continue;

			PiranhaObject tempObject = (LivingObject) tempUpdatable;

			if (tempObject.getName().equalsIgnoreCase(formated_living_name))
				if (tempObject.isStunned() == false)
					instruction.doAction(tempObject, formated_facing);
		}
	}

	private void stopApp() {
		new SoundTask().playSound(SOUNDTYPE.ERROR, "error");
		JOptionPane.showMessageDialog(null,
				"An error has occurred : file \"" + value + "\" incorrect format or missing");
		new Conductor().stopApp();
	}

	////////// CLOSE ////////////

	private String value;

	public void closeChat() {
		World world = World.get();
		if (world != null)
			world.freeze(false);

		boolean playerCanInterract = Cutscene.isRunning() == false;
		new PlayerFinder().setPlayerCanInterract(playerCanInterract);
		LAYER.PUZZLE.getHandler().clear();
		new TalkedCondition().registerValue(value);
	}

	////////// KEYBOARD ////////////

	private KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}

	private int count = 1;

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == DialogueKey.getKey())
			inputPressed();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	////////// GAMEPAD ////////////

	private GamepadController gamepadController = new GamepadController(this);

	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (e.getKey() != DialogueKey.getGamepadKey())
			return;

		inputPressed();
	}

	@Override
	public void buttonReleased(ButtonEvent e) {

	}

	////////// INPUT ////////////

	private void inputPressed() {
		if (LAYER.PUZZLE.getHandler().containsObject(this) == false)
			return;

		if (MenuIngameController.getMenu() != null)
			return;

		if (content != null) {
			if (!content.isFulldisplayed()) {
				content.setFulldisplayed(true);
				return;
			}
		}

		if (count < all_lines.length) {
			count++;
			goToNextLine();
		} else
			closeChat();
	}

}
