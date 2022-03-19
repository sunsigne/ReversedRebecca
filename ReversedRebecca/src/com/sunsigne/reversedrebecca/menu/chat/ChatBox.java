package com.sunsigne.reversedrebecca.menu.chat;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.instructions.Statement;
import com.sunsigne.reversedrebecca.instructions.instruction.Instruction;
import com.sunsigne.reversedrebecca.instructions.instruction.shortcut.FacingInstruction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class ChatBox implements Updatable, KeyboardEvent {

	public ChatBox(LivingObject living, String target, String dialogue) {
		this.living = living;
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

	private LivingObject living;

	public void openChat() {
		World world = World.get();
		if (world != null)
			world.freeze(true);

		// added as first element to render behind objects
		LAYER.PUZZLE.getHandler().getList().add(0, this);
		goToNextLine();
	}

	private String[] all_lines;
	private ChatContent content;

	private void goToNextLine() {

		LAYER.PUZZLE.getHandler().removeObject(content);

		String line = all_lines[count - 1];
		String living_name = line.split("=")[0];
		String facing = line.split("=")[1];
		String mood = line.split("=")[2];
		String text = line.split("=")[3];

		content = new ChatContent(living_name, mood, text);
		LAYER.PUZZLE.addObject(content);

		Instruction instruction = InstructionList.getList().getObject(new FacingInstruction());

		for (Updatable tempUpdatable : living.getHandler().getList()) {
			if (tempUpdatable instanceof LivingObject == false)
				continue;

			LivingObject tempLiving = (LivingObject) tempUpdatable;

			if (tempLiving.getName().equalsIgnoreCase(living_name)) {
				instruction.doAction(tempLiving, facing);
			}
		}
	}

	////////// CLOSE ////////////

	private String value;

	public void closeChat() {
		World world = World.get();
		if (world != null)
			world.freeze(false);

		LAYER.PUZZLE.getHandler().clear();
		new Statement().chatFinished(value);
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
		if (key == KeyEvent.VK_E) {

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
