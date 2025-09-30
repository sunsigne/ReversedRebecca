package com.sunsigne.reversedrebecca.object.hud;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;

public class HUDHealth extends GameObject implements HUD {

	private HUDHealth() {
		super(0, 0, Size.M, Size.M);
		HUDList.getList().addObject(this);
		loadImages();
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		Player player = new PlayerFinder().getPlayer();
		var clazz = "HUD HP";

		if (player != null) {
			if (player.isInvulnerable())
				return clazz + " : " + "INVULNERABLE";
		}

		var maxhp = player == null ? "N/A" : player.getMaxHp();
		var hp = player == null ? "N/A" : player.getHp();

		return clazz + " : " + hp + "/" + maxhp;
	}

	////////// HUD ////////////

	private static HUD hud = new HUDHealth();

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
	
	private BufferedImage bonus_half_img;
	private BufferedImage bonus_full_img;

	@Override
	public int getSheetColCriterion() {
		return -1;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	private void loadImages() {
		BufferedImage sheet = new ImageTask().loadImage("textures/hud/" + "heart");
		empty_img = getSheetSubImage(sheet, 3);
		half_img = getSheetSubImage(sheet, 2);
		full_img = getSheetSubImage(sheet, 1);
		
		bonus_half_img = getSheetSubImage(sheet, 2, 2, 16, 16);
		bonus_full_img = getSheetSubImage(sheet, 1, 2, 16, 16);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Player player = new PlayerFinder().getPlayer();

		if (player == null)
			return;

		if (player.isInvulnerable())
			return;

		// drawing maxHp empty heart
		for (int index = 0; index < player.getMaxHp(); index = index + 2) {
			g.drawImage(empty_img, getX() + index * getWidth() / 2, getY(), getWidth(), getHeight(), null);
		}

		// drawing hp hearts above
		for (int index = 0; index < player.getHp(); index++) {
			if (index % 2 == 0)
				g.drawImage(half_img, getX() + index * getWidth() / 2, getY(), getWidth(), getHeight(), null);
			else
				g.drawImage(full_img, getX() + (getWidth() / 2) * (index - 1), getY(), getWidth(), getHeight(), null);
		}
		
		// drawing bonus hp
		for (int index = player.getMaxHp(); index < player.getMaxHp() + player.getBonusHp(); index++) {
			if (index % 2 == 0)
				g.drawImage(bonus_half_img, getX() + index * getWidth() / 2, getY(), getWidth(), getHeight(), null);
			else
				g.drawImage(bonus_full_img, getX() + (getWidth() / 2) * (index - 1), getY(), getWidth(), getHeight(), null);
		}
	}

}
