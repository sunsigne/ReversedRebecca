package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.ActionOption;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.ActionOption.ACTION_DESIGN;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.ActionOption.ACTION_HIGHLIGHT;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.chest.ChestObject;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.font.TextsOption;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.ressources.lang.Language;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;

public class ActionOptionPreview extends GameObject implements SheetableImage {

	public ActionOptionPreview(int x, int y) {
		super(x, y);
		loadPuzzler();
		loadImages();
	}

	////////// NAME ////////////

	public String toString() {
		var clazz = "ACTION OPTION PREVIEW ";
		return clazz + " : " + getX() + "-" + getY();
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.MENU;
	}
	
	////////// TICK ////////////

	private Font font;

	@Override
	public void tick() {
		float size = 24f / (float) Math.sqrt(Window.SCALE_X);
		font = new FontTask().createNewFont("square_sans_serif_7.ttf", size * TextsOption.getSize());
	}

	////////// TEXTURE ////////////

	private PuzzlerObject puzzler;

	private void loadPuzzler() {
		puzzler = new ChestObject(-1, getX(), getY()) {
			@Override
			public boolean getHighlightCondition() {
				return ActionOption.getHighlight() == ACTION_HIGHLIGHT.BRIGHT;
			};
		};
	}

	private BufferedImage rebecca_img;
	private BufferedImage coming_soon;

	@Override
	public int getSheetColCriterion() {
		return -1;
	}

	@Override
	public int getSheetRowCriterion() {
		return -1;
	}

	private void loadImages() {
		BufferedImage sheet = null;

		sheet = new ImageTask().loadImage("textures/characters/rebecca/" + "rebecca");
		rebecca_img = getSheetSubImage(sheet, 1, 2, 16, 16);

		String path = "textures/other/coming_soon";
		String lang = Language.getInstance().getLang();

		if (lang.equalsIgnoreCase("french")) {
			path = path.concat("_fr");
		}

		coming_soon = new ImageTask().loadImage(path);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (rebecca_img == null)
			return;

		String text = puzzler.getTripleAction().getAction(0).getDisplayedText();
		
		int gap = 180 + (10 - text.length() * 6) - (int) (TextsOption.getSize() * 90);
		
		g.drawImage(rebecca_img, getX() - gap, getY(), getWidth(), getHeight(), null);

		if(ActionOption.getDesign() == ACTION_DESIGN.COLOR) {
			g.drawImage(puzzler.getImage(), getX() + 100 - gap, getY(), getWidth(), getHeight(), null);
			puzzler.drawHighlight(g, puzzler.getHighlightImage(), 100 - gap, 0, 0, 0);
		}
		else
			g.drawImage(coming_soon, getX() + 100 - gap, getY() + 20, 125, 66, null);


		drawTextAction(g, text, gap);
	}

	private void drawTextAction(Graphics g, String text, int gap) {
		int[] rect = new int[] { getX() - 100 - gap, getY(), getWidth(), getHeight() };

		if (ControllerManager.getInstance().isUsingGamepad()) {
			text = text.concat("   ");
			rect = new int[] { getX() - Size.XS - gap, getY() + Size.XS, Size.XS, Size.XS };
			int size = 2;
			g.drawImage(ActionOneKey.getGamepadButton(), rect[0] - size, rect[1] - size, rect[2] + size * 2,
					rect[3] + size * 2, null);
			gap = (int) (((TextsOption.getSize() * 100) - 100) / 2.6f);
			rect = new int[] { rect[0] + gap, rect[1], rect[2], rect[3] };
		}

		new TextDecoration().drawOutlinesString(g, font, text, DIRECTION.RIGHT, rect);
	}

}
