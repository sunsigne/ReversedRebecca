package com.sunsigne.reversedrebecca.object.puzzler.hole.downward;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.RequirementBubbleObject;
import com.sunsigne.reversedrebecca.object.puzzler.hole.DigAction;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class HoleObject extends PuzzlerObject implements Facing {

	public HoleObject(DEV_LVL devDifficulty, DIRECTION facing, int x, int y) {
		super(devDifficulty, x, y);
		this.facing = facing;
	}

	public HoleObject(LVL difficulty, DIRECTION facing, int x, int y) {
		super(difficulty, x, y);
		this.facing = facing;
	}

	public LAYER getExitLayer(Handler currentHandler) {
		LAYER exit_layer = LAYER.WORLD_CONTENT;

		for (LAYER tempLayer : LAYER.values()) {
			if (tempLayer.getName().contains("content") == false)
				continue;

			if (currentHandler != tempLayer.getHandler())
				exit_layer = tempLayer;

			else
				break;
		}

		return exit_layer;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "hole";
	}

	////////// FACING ////////////

	private DIRECTION facing;

	public DIRECTION getFacing() {
		return facing;
	}

	public void setFacing(DIRECTION facing) {
		this.facing = facing;
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetRowCriterion() {
		return 1 + getFacing().getNum();
	}

	@Override
	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzler/" + "hole");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

	@Override
	public BufferedImage getHighlightImage() {
		if (highlightImage == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzler/" + "hole" + "_" + "highlight");
			highlightImage = getSheetSubImage(sheet, 1, getSheetRowCriterion(), getSheetWidth() + 2,
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
		OpenPuzzleAction digAction = new DigAction(this);

		RequirementBubbleObject requirementDig = new RequirementBubbleObject(getX(), getY(), digAction.getToolPlayer(),
				getDifficulty());

		tripleAction = new TripleAction(requirementDig, digAction, null, null);
	}

	////////// HIGHLIGHT ////////////

	@Override
	public boolean isHighlightAbovePlayer() {
		return false;
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingPath() {
		return false;
	}

}
