package com.sunsigne.reversedrebecca.object.hud.nurse;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.ActionOption;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.ActionOption.ACTION_DESIGN;
import com.sunsigne.reversedrebecca.object.hud.HUD;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.physic.debug.MultiToolMode;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class HUDNurseTool extends GameObject implements TickFree, HUD {

	public HUDNurseTool() {
		super(Window.WIDHT - Size.M, Window.HEIGHT - Size.M - 10, Size.M, Size.M);
		loadImages();
	}

	private boolean isNumberSettings() {
		return ActionOption.getDesign() == ACTION_DESIGN.NUMBER;
	}

	////////// TOOL ////////////

	private int numberOfTools() {
		return 3;
	}

	private String getToolName(int num) {
		switch (num) {
		case 1:
			return "KEY";
		case 2:
			return "SYRINGUE";
		case 3:
			return "BANDAGE";
		}
		return "UNKNOWN";
	}

	private LVL getToolDifficulty(int num) {
		switch (num) {
		case 1:
			return LVL.CYAN;
		case 2:
			return LVL.GREEN;
		case 3:
			return LVL.YELLOW;
		}
		return LVL.NULL;
	}

	private LVL getToolMaxDifficulty(int num) {
		switch (num) {
		case 1:
			return LVL.CYAN;
		case 2:
			return LVL.YELLOW;
		case 3:
			return LVL.YELLOW;
		}
		return LVL.CYAN;
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "HUD NURSE";
		var result = clazz;

		for (int tool = 1; tool < numberOfTools() + 1; tool++) {
			var name = getToolName(tool).toUpperCase();
			var lvl = getToolDifficulty(tool).getName().toUpperCase();
			var maxlvl = getToolMaxDifficulty(tool).getName().toUpperCase();
			result = result.concat(name + " : " + lvl + "/" + maxlvl + " - ");
		}

		return result;
	}

	////////// HUD ////////////

	private static HUD hud = new HUDNurseTool();

	@Override
	public HUD getHUD() {
		return hud;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.HUD;
	}

	////////// TEXTURE ////////////

	private GameList<BufferedImage> images = new GameList<>(LISTTYPE.ARRAY);

	@Override
	public int getSheetColCriterion() {
		return 1;
	}

	@Override
	public int getSheetRowCriterion() {
		return -1;
	}

	@Override
	public int getSheetSize() {
		return 32;
	}

	private BufferedImage loadBatteryImage(LVL maxDifficulty, LVL difficulty) {
		if (MultiToolMode.debugMode.getDebugMode().getState())
			difficulty = maxDifficulty = LVL.RED;

		String number = isNumberSettings() ? "_number" : "";
		BufferedImage sheet = new ImageTask().loadImage("textures/hud/" + "batteries" + number);
		BufferedImage image = getSheetSubImage(sheet, difficulty.ordinal(), maxDifficulty.ordinal(), getSheetWidth(),
				getSheetHeight());

		return image;
	}

	public void loadImages() {
		images.clear();

		for (int tool = 1; tool < numberOfTools() + 1; tool++) {

			BufferedImage sheet = null;
			sheet = new ImageTask().loadImage("textures/tools/" + "nurse");
			BufferedImage tool_image = getSheetSubImage(sheet, tool, 1, 16, 16);
			BufferedImage battery_image = loadBatteryImage(getToolMaxDifficulty(tool), getToolDifficulty(tool));

			images.addObject(tool_image);
			images.addObject(battery_image);
		}
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		int size = images.getList().size();

		for (int index = 0; index < size; index += 2) {
			try {

				// tool
				g.drawImage(images.getList().get(index), getX() - (1 + index) * getWidth() + Size.XL / 8, getY(), getWidth(), getHeight(),
						null);

				// battery
				g.drawImage(images.getList().get(index + 1), getX() - index * getWidth(), getY(),
						getWidth(), getHeight(), null);

			} catch (IndexOutOfBoundsException e) {
				// can occurs when MultiToolMode is used
			}
		}
	}

}
