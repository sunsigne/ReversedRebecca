package com.sunsigne.reversedrebecca.object.hud;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolList;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Blinking;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;

public class HUDTools extends GameObject implements HUD, Blinking {

	private HUDTools() {
		super(0, Window.HEIGHT - Size.M - 10, Size.M, Size.M);
		HUDList.getList().addObject(this);
		loadImages(null);
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "HUD TOOL : ";
		var result = clazz;

		var list = ToolList.getList();

		for (ToolPlayer tempTool : list.getList()) {
			var name = tempTool.getName().toUpperCase();
			var lvl = tempTool.getDifficulty().getName().toUpperCase();
			var maxlvl = tempTool.getMaxDifficulty().getName().toUpperCase();
			result = result.concat(name + " : " + lvl + "/" + maxlvl + " - ");
		}

		return result;
	}

	////////// HUD ////////////

	private static HUD hud = new HUDTools();

	@Override
	public HUD getHUD() {
		return hud;
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

	private Map<BufferedImage, BufferedImage> map = new HashMap<>();
	private GameList<BufferedImage> images = new GameList<>(LISTTYPE.ARRAY);
	private BufferedImage blinking_tool_image;

	@Override
	public int getSheetColCriterion() {
		return -1;
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
		BufferedImage image = new ImageTask().loadImage("textures/hud/" + "batteries");	
		BufferedImage battery_img = getSheetSubImage(image, difficulty.ordinal(), maxDifficulty.ordinal(), getSheetWidth(), getSheetHeight());
		return battery_img;
	}
	
	public void loadImages(ToolPlayer tool) {
		map.clear();
		images.clear();

		var list = ToolList.getList();
		ImageTask task = new ImageTask();

		for (ToolPlayer tempTool : list.getList()) {
			if (tempTool.getDifficulty() == LVL.NULL)
				continue;

			BufferedImage tool_image = task.loadImage("textures/tools/" + tempTool.getName() + "_null");
			BufferedImage battery_image = loadBatteryImage(tempTool.getMaxDifficulty(), tempTool.getDifficulty());

			images.addObject(tool_image);
			images.addObject(battery_image);

			if (tool == null)
				continue;

			if (tool.getName() != tempTool.getName())
				continue;

			setBlinking();
			blinking_tool_image = new ImageTask().loadImage("textures/tools/" + tempTool.getName() + "_blinking");
			map.put(tool_image, blinking_tool_image);
		}
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		int size = images.getList().size();

		for (int index = 0; index < size; index += 2) {
			try {

				// tool
				g.drawImage(images.getList().get(index), getX() + index * getWidth(), getY(), getWidth(), getHeight(),
						null);

				// blinking
				if (map.get(images.getList().get(index)) != null)
					drawHighlight(g, blinking_tool_image, index * getWidth(), 0, 0, 0);

				// battery

				g.drawImage(images.getList().get(index + 1), getX() + (1 + index) * getWidth() - Size.XL / 8, getY(),
						getWidth(), getHeight(), null);

			} catch (IndexOutOfBoundsException e) {
				// can occurs when MultiToolMode is used
			}
		}
	}

}
