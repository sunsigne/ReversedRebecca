package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.FormattedString;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.font.TextsOption;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;

public class TextsSizePreview extends GameObject {

	public TextsSizePreview(int x, int y) {
		super(x, y);
		loadCharacter();
	}

	////////// NAME ////////////

	public String toString() {
		var clazz = "TEXT SIZE PREVIEW ";
		return clazz + " : " + getX() + "-" + getY();
	}

	////////// TICK ////////////

	private Font font;

	@Override
	public void tick() {
		font = new FontTask().createNewFont("square_sans_serif_7.ttf", 22f * TextsOption.getSize());
	}

	////////// TEXTURE ////////////

	private String character;
	private BufferedImage image;

	private void loadCharacter() {
		File file = new File(FilePath.RESSOURCES_PATH + "textures/characters");
		var file_list = new ArrayList<String>(Arrays.asList(file.list()));
		var chara_list = new GameList<String>(LISTTYPE.ARRAY);

		file_list.forEach(tempfile -> {
			if (tempfile.contains(".") == false) // check if folder or file
				if (tempfile.contains("error") == false)
					chara_list.addObject(tempfile);
		});

		character = new RandomGenerator().getElementFromList(chara_list);
		image = new ImageTask().loadImage("textures/characters/" + character + "/" + "standing_down");

		character = new FormattedString().capitalize(character);
		if (character.contains("_"))
			character = character.split("_")[0];
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (character.isEmpty() || image == null)
			return;

		int gap = 3 * character.length() - 12;

		g.drawImage(image, getX() + Size.XS - gap, getY(), getWidth(), getHeight(), null);

		int[] rect = new int[] { getX() + Size.XL + gap, getY(), getWidth(), getHeight() };
		new TextDecoration().drawOutlinesString(g, font, character, DIRECTION.NULL, rect);
	}

}
