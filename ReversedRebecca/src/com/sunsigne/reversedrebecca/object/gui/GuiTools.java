package com.sunsigne.reversedrebecca.object.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayerList;
import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;

public class GuiTools extends GameObject {

	public GuiTools() {
		super(0, Window.HEIGHT - Size.M, Size.M, Size.M);
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		var list = ToolPlayerList.getList();
		int index = 0;

		for (ToolPlayer tempTool : list.getList()) {
			if (tempTool.getDifficulty() == LVL.NULL)
				continue;

			BufferedImage tool_image = new ImageTask()
					.loadImage("textures/" + tempTool.getPuzzlerName() + "/" + tempTool.getName() + "_null");
			g.drawImage(tool_image, getX() + 2 * index * getWidth(), getY(), getWidth(), getHeight(), null);

			BufferedImage battery_image = new ImageTask()
					.loadImage("textures/" + "gui/" + "battery_" + tempTool.getDifficulty().getName());
			g.drawImage(battery_image, getX() + (1 + 2 * index) * getWidth(), getY(), getWidth(), getHeight(), null);
			index++;
		}

	}

}
