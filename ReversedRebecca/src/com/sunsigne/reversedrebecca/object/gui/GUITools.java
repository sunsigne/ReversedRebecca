package com.sunsigne.reversedrebecca.object.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayerList;
import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;

public class GUITools extends GameObject implements GUI {

	private GUITools() {
		super(0, Window.HEIGHT - Size.M, Size.M, Size.M);
		GUIList.getList().addObject(this);
		loadImage();
	}

	////////// GUI ////////////

	private static GUI gui = new GUITools();

	@Override
	public GUI getGUI() {
		return gui;
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// TEXTURE ////////////

	private GameList<BufferedImage> images = new GameList<>(LISTTYPE.ARRAY);

	public void loadImage() {
		images.clear();

		var list = ToolPlayerList.getList();

		for (ToolPlayer tempTool : list.getList()) {
			if (tempTool.getDifficulty() == LVL.NULL)
				continue;

			BufferedImage tool_image = new ImageTask().loadImage("textures/tools/" + tempTool.getName() + "_null");
			BufferedImage battery_image = new ImageTask().loadImage("textures/" + "gui/battery_max_"
					+ tempTool.getMaxDifficulty().getName() + "_current_" + tempTool.getDifficulty().getName());

			images.addObject(tool_image);
			images.addObject(battery_image);
		}
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		int size = images.getList().size();

		for (int index = 0; index < size; index += 2) {
			g.drawImage(images.getList().get(index), getX() + index * getWidth(), getY(), getWidth(), getHeight(),
					null);
			g.drawImage(images.getList().get(index + 1), getX() + (1 + index) * getWidth(), getY(), getWidth(),
					getHeight(), null);
		}
	}

}
