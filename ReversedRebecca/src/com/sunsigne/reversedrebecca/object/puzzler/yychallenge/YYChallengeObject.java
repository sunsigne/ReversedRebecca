package com.sunsigne.reversedrebecca.object.puzzler.yychallenge;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.DifficultyOption;

public class YYChallengeObject extends PuzzlerObject {

	public YYChallengeObject(LVL difficulty, CHALLENGE_TYPE type, int x, int y) {
		super(difficulty, x, y);
		this.type = type;
		updateDifficulty(difficulty);

		// not really fancy, but it gets the job done
		super.init();
	}

	@Override
	protected void init() {

	}

	////////// NAME ////////////

	protected CHALLENGE_TYPE type;

	@Override
	public String getName() {
		return "yy_strenght";
	}

	////////// CHALLENGE ////////////

	public enum CHALLENGE_TYPE {
		STRENGHT, INTELLIGENCE;
	}

	////////// DIFFICULTY ////////////

	private void updateDifficulty(LVL difficulty) {
		if (difficulty != LVL.NULL)
			return;

		switch (DifficultyOption.getDifficulty()) {
		case EASY:
			setDifficulty(LVL.CYAN);
			break;
		case NORMAL:
			setDifficulty(LVL.YELLOW);
			break;
		case HARD:
			setDifficulty(LVL.RED);
			break;
		}
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetRowCriterion() {
		return 3;
	}

	@Override
	public int getSheetColCriterion() {
		switch (type) {
		case STRENGHT:
			return 1;
		case INTELLIGENCE:
			return 2;
		default:
			return 1;
		}
	}

	@Override
	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzler/" + "puzzler");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

	@Override
	public BufferedImage getHighlightImage() {
		if (highlightImage == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzler/" + "puzzler" + "_" + "highlight");
			highlightImage = getSheetSubImage(sheet, 1, 5, getSheetWidth() + 2,
					getSheetHeight() + 2);
		}
		return highlightImage;
	}

	////////// INTERACTION ////////////

	private TripleAction tripleAction;

	@Override
	public TripleAction getTripleAction() {
		return tripleAction;
	}

	@Override
	protected void loadTripleAction() {
		OpenPuzzleAction challengeAction = null;

		switch (type) {
		case STRENGHT:
			challengeAction = new StrenghtChallengeAction(this);
			break;
		case INTELLIGENCE:
			challengeAction = new IntelligenceChallengeAction(this);
			break;
		}

		tripleAction = new TripleAction(null, challengeAction, null, null);
	}

}
