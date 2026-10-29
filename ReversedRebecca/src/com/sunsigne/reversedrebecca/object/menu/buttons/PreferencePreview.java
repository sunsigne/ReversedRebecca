package com.sunsigne.reversedrebecca.object.menu.buttons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingOption;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingOption.LIVING_TYPE;
import com.sunsigne.reversedrebecca.object.piranha.living.player.RebeccaDesign;
import com.sunsigne.reversedrebecca.object.piranha.living.player.RebeccaDesign.DESIGN_TYPE;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class PreferencePreview extends GameObject implements TickFree, SheetableImage {

	public PreferencePreview(int x, int y) {
		super(x, y, Size.L, Size.L);
		loadImages();
	}

	public void refresh() {
		loadImages();
	}

	////////// NAME ////////////

	public String toString() {
		var clazz = "PREFERENCE PREVIEW ";
		return clazz + " : " + getX() + "-" + getY();
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.MENU;
	}

	////////// TEXTURE ////////////

	private BufferedImage rebecca_img;
	private BufferedImage marichel_img;
	private BufferedImage miaka_img;

	@Override
	public int getSheetColCriterion() {
		return 1;
	}

	@Override
	public int getSheetRowCriterion() {
		return 4;
	}

	private void loadImages() {
		DESIGN_TYPE design = RebeccaDesign.getType();
		String name = "rebecca";
		name = design == DESIGN_TYPE.GOTHIC ? name.concat("/gothic") : name.concat("");
		BufferedImage sheet = new ImageTask().loadImage("textures/characters/" + name + "/world");
		rebecca_img = getSheetSubImage(sheet);

		LIVING_TYPE type = LivingOption.getType();
		name = "marichel/";
		name = type == LIVING_TYPE.MALE ? name.concat("michel") : name.concat("marie");
		sheet = new ImageTask().loadImage("textures/characters/" + name + "/world");
		marichel_img = getSheetSubImage(sheet);
		
		name = "miaka/";
		name = type == LIVING_TYPE.FEMALE ? name.concat("mia") : name.concat("mika");
		sheet = new ImageTask().loadImage("textures/characters/" + name + "/world");
		miaka_img = getSheetSubImage(sheet);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (rebecca_img == null || marichel_img == null || miaka_img == null)
			return;

		int gap = 128;
		
		g.drawImage(rebecca_img, getX(), getY(), getWidth(), getHeight(), null);
		g.drawImage(marichel_img, getX() - gap, getY(), getWidth(), getHeight(), null);
		g.drawImage(miaka_img, getX() + gap, getY(), getWidth(), getHeight(), null);
	}

}
