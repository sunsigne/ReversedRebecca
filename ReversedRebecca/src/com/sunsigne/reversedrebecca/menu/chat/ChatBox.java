package com.sunsigne.reversedrebecca.menu.chat;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.pattern.FormatedName;
import com.sunsigne.reversedrebecca.piranha.condition.global.TalkedCondition;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.state.FacingRequest;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class ChatBox implements Updatable, KeyboardEvent {

	public ChatBox(PiranhaObject object, String target, String dialogue) {
		this.object = object;
		this.value = target;
		loadImage();

		// register the whole dialogue as an array of lines
		all_lines = dialogue.split(System.getProperty("line.separator"));
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	private void loadImage() {
		image = new ImageTask().loadImage("textures/menu/" + "chatbox");
	}

	public BufferedImage getImage() {
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
		// added as first element to render behind objects
		LAYER.PUZZLE.getHandler().getList().add(0, this);
		goToNextLine();
	}

	private String[] all_lines;
	private ChatContent content;

	private void goToNextLine() {

		LAYER.PUZZLE.getHandler().removeObject(content);

		String line = all_lines[count - 1];

		String living_name = line.contains("=") ? line.split("=")[0] : "error";
		String facing = line.contains("=") ? line.split("=")[1] : "down";
		// authorize words like "player" or "object"
		String formated_living_name = new FormatedName().getName(object, living_name);
		String formated_facing = new FormatedName().getName(object, facing);

		String mood = line.contains("=") ? line.split("=")[2] : "neutral";
		String text = line.contains("=") ? line.split("=")[3] : line;

		content = new ChatContent(formated_living_name, mood, text);
		LAYER.PUZZLE.addObject(content);

		Request instruction = RequestList.getList().getObject(new FacingRequest());

		for (Updatable tempUpdatable : object.getHandler().getList()) {
			if (tempUpdatable instanceof LivingObject == false)
				continue;

			PiranhaObject tempObject = (LivingObject) tempUpdatable;

			if (tempObject.getName().equalsIgnoreCase(formated_living_name))
				instruction.doAction(tempObject, formated_facing);
		}
	}

	////////// CLOSE ////////////

	private String value;

	public void closeChat() {
		World world = World.get();
		if (world != null)
			world.freeze(false);

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
		int key = e.getKeyCode();
		if (key == ActionOneKey.getKey()) {

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

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
