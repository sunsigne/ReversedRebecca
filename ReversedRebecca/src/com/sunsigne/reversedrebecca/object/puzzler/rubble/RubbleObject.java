package com.sunsigne.reversedrebecca.object.puzzler.rubble;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class RubbleObject extends PuzzlerObject {

	public RubbleObject(DEV_LVL devDifficulty, COLOR color, boolean horizontal, int x, int y) {
		super(devDifficulty, x, y);
		this.horizontal = horizontal;
		this.color = color;
	}

	public RubbleObject(LVL difficulty, COLOR color, boolean horizontal, int x, int y) {
		super(difficulty, x, y);
		this.horizontal = horizontal;
		this.color = color;
	}

	////////// NAME ////////////

	private boolean horizontal;		
	private COLOR color;
	
	@Override
	public String getName() {
		return "rubble"  + "_" + color.getName() + "_" + Facing.getAxisName(horizontal);
	}

	////////// INTERACTION ////////////

	protected TripleAction tripleAction;

	@Override
	public TripleAction getTripleAction() {
		return tripleAction;
	}

	protected void loadTripleAction() {
		String noActionText = new Translatable().getTranslatedText("RubbleBlocked", FilePath.ACTION);
		Action explodeAction = new ExplodeRubbleAction(this);
		tripleAction = new TripleAction(noActionText, explodeAction, null, null);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX() - getWidth(), getY() - getHeight(), 3 * getWidth(), 3 * getHeight(), null);
	}

}
