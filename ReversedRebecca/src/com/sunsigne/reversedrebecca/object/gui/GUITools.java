package com.sunsigne.reversedrebecca.object.gui;

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

public class GUITools extends GameObject implements GUI, Blinking {

	private GUITools() {
		super(0, Window.HEIGHT - Size.M - 10, Size.M, Size.M);
		GUIList.getList().addObject(this);
		loadImages(null);
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "GUI TOOL : ";
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

	////////// GUI ////////////

	private static GUI gui = new GUITools();

	@Override
	public GUI getGUI() {
		return gui;
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

	public void loadImages(ToolPlayer tool) {
		map.clear();
		images.clear();

		var list = ToolList.getList();

		for (ToolPlayer tempTool : list.getList()) {
			if (tempTool.getDifficulty() == LVL.NULL)
				continue;

			BufferedImage tool_image = new ImageTask().loadImage("textures/tools/" + tempTool.getName() + "_null");
			BufferedImage battery_image = new ImageTask().loadImage("textures/" + "gui/battery_max_"
					+ tempTool.getMaxDifficulty().getName() + "_current_" + tempTool.getDifficulty().getName());

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

			// tool
			g.drawImage(images.getList().get(index), getX() + index * getWidth(), getY(), getWidth(), getHeight(),
					null);

			// blinking
			if (isBlinking() && map.get(images.getList().get(index)) != null) {
				int pixel = 6; // -> almost Size.XS / 5
				g.drawImage(blinking_tool_image, getX() - pixel + index * getWidth(), getY() - pixel,
						getWidth() + 2 * pixel, getHeight() + 2 * pixel, null);
			}

			// battery
			try {
				g.drawImage(images.getList().get(index + 1), getX() + (1 + index) * getWidth() - Size.XL / 8, getY(), getWidth(),
						getHeight(), null);
			} catch (IndexOutOfBoundsException e) {
				// can occurs when MultiToolMode is used
			}
		}
	}

}
