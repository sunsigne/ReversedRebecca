package com.sunsigne.reversedrebecca.object.interactive.puzzler;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.interactive.Action;
import com.sunsigne.reversedrebecca.object.interactive.TripleAction;
import com.sunsigne.reversedrebecca.ressources.DIFFICULTY;

public class DoorObject extends PuzzlerObject {

	public DoorObject(DIFFICULTY difficulty, int x, int y) {
		super(difficulty, x, y);
		loadTripleAction();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "door";
	}

	////////// INTERACTION ////////////

	private Action open;
	private TripleAction tripleAction;

	@Override
	public TripleAction getTripleAction() {
		return tripleAction;
	}

	private void loadTripleAction() {
		loadOpenAction();
		tripleAction = new TripleAction(open, null, null);
	}

	private void loadOpenAction() {
		open = new Action() {

			@Override
			public String getName() {
				return getTranslatedText("OPEN", "actions.csv", 1);
			}

			@Override
			public int getKeyEvent() {
				return KeyEvent.VK_E;
			}

			@Override
			public void doAction() {
//				getHandler().removeObject(this);
			}
		};
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
		drawTripleActionText(g);
	}

}
