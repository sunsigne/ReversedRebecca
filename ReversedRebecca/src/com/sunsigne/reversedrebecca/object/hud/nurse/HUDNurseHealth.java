package com.sunsigne.reversedrebecca.object.hud.nurse;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.hud.HUD;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;

public class HUDNurseHealth extends GameObject implements HUD {

	public HUDNurseHealth() {
		super(Window.WIDHT - Size.M, 0, Size.M, Size.M);
		loadImages();
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "HUD NURSE HP";
		return clazz + " : " + getHp() + "/" + getMaxHp();
	}

	////////// TOOL ////////////

	private int getHp() {
		return 4;
	}

	private int getMaxHp() {
		return 6;
	}

	////////// HUD ////////////

	private static HUD hud = new HUDNurseHealth();

	@Override
	public HUD getHUD() {
		return hud;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.HUD;
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// TEXTURE ////////////

	private BufferedImage empty_img;
	private BufferedImage half_img;
	private BufferedImage full_img;

	@Override
	public int getSheetColCriterion() {
		return -1;
	}

	@Override
	public int getSheetRowCriterion() {
		return 4;
	}

	private void loadImages() {
		BufferedImage sheet = new ImageTask().loadImage("textures/hud/" + "heart");
		empty_img = getSheetSubImage(sheet, 3);
		half_img = getSheetSubImage(sheet, 2);
		full_img = getSheetSubImage(sheet, 1);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

		// drawing maxHp empty heart
		for (int index = 0; index < getMaxHp(); index = index + 2) {
			g.drawImage(empty_img, getX() - index * getWidth() / 2, getY(), getWidth(), getHeight(), null);
		}

		// drawing hp hearts above
		for (int index = 0; index < getHp(); index++) {
			if (index % 2 == 0)
				g.drawImage(half_img, getX() - index * getWidth() / 2, getY(), getWidth(), getHeight(), null);
			else
				g.drawImage(full_img, getX() - (getWidth() / 2) * (index - 1), getY(), getWidth(), getHeight(), null);
		}
	}

}
