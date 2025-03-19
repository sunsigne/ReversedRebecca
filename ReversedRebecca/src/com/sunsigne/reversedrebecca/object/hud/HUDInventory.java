package com.sunsigne.reversedrebecca.object.hud;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.InventoryPlayer;
import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Blinking;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.system.Size;

public class HUDInventory extends GameObject implements HUD, Blinking {

	private HUDInventory() {
		super(0, Size.M + 10, Size.M, Size.M);
		HUDList.getList().addObject(this);
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		String items = InventoryPlayer.getSize() + " ITEM(S)";
		return "HUD INVENTORY : " + items;
	}

	////////// HUD ////////////

	private static HUD hud = new HUDInventory();

	@Override
	public HUD getHUD() {
		return hud;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.HUD;
	}

	////////// BLINKING ////////////

	@Override
	public int getTotalBlinkingTime() {
		return 80 + Blinking.super.getTotalBlinkingTime();
	}

	private Cycloid<Boolean> blinking = new Cycloid<Boolean>(false, true);

	@Override
	public Cycloid<Boolean> getBlinking() {
		return blinking;
	}

	private int time;

	@Override
	public int getBlinkingTime() {
		return time;
	}

	@Override
	public void setBlinkingTime(int time) {
		this.time = time;
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return -1;
	}

	@Override
	public int getSheetRowCriterion() {
		return -1;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		int size = InventoryPlayer.getSize();

		for (int index = 0; index < size; index++) {
			try {

				BufferedImage image = InventoryPlayer.get(index);

				// item
				g.drawImage(image, getX() + index * getWidth(), getY(), getWidth(), getHeight(), null);

				// blinking
				if (InventoryPlayer.getHighlight() != null)
					drawHighlight(g, InventoryPlayer.getHighlight(), index * getWidth(), 0, 0, 0);

			} catch (IndexOutOfBoundsException e) {
				// can occurs when MultiToolMode is used
			}
		}
	}

}
