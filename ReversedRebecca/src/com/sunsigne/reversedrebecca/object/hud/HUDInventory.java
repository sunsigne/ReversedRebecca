package com.sunsigne.reversedrebecca.object.hud;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.InventoryPlayer;
import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Blinking;
import com.sunsigne.reversedrebecca.object.hud.InventoryOption.INVENTORY_TYPE;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.system.Size;

public class HUDInventory extends GameObject implements HUD, Blinking {

	private HUDInventory() {
		super(0, Size.M + 10, Size.M, Size.M);
		HUDList.getList().addObject(this);
		setVisible(true);
	}

	private boolean genericClue;

	public boolean isGenericClue() {
		return genericClue;
	}

	public void setGenericClue(boolean genericClue) {
		this.genericClue = genericClue;
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

	private boolean visible;

	@Override
	public boolean isVisible() {
		return visible && (InventoryOption.getType() == INVENTORY_TYPE.VISIBLE || isGenericClue());
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
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
		if (isVisible() == false)
			return;

		int size = InventoryPlayer.getSize();

		for (int index = 0; index < size; index++) {
			try {
				if (index > 0 && isGenericClue() && InventoryOption.getType() != INVENTORY_TYPE.VISIBLE)
					break;

				BufferedImage image = InventoryPlayer.get(index);

				// if no hp HUD
				Player player = new PlayerFinder().getPlayer();
				int yoffset = player != null && player.isInvulnerable() ? getY() : 0;

				// item
				g.drawImage(image, getX() + index * getWidth(), getY() - yoffset, getWidth(), getHeight(), null);

				// blinking
				if (InventoryPlayer.getHighlight() != null && index == size - 1)
					drawHighlight(g, InventoryPlayer.getHighlight(), index * getWidth(), -yoffset, 0, 0);

			} catch (IndexOutOfBoundsException e) {
				// can occurs when MultiToolMode is used
			}
		}
	}

}
