package com.sunsigne.reversedrebecca.object.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.ressources.images.Texture;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseClickEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;

public class ExempleHP extends GameObject implements Texture, MouseClickEvent {

	public ExempleHP() {
		super(0, 0);
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// TEXTURE ////////////

	@Override
	public BufferedImage getImage() {
		return null;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

		g.setColor(Color.BLUE);
		g.fillRect(getX(), getY(), getWidth(), getHeight());

		Font font = new Font("arial", 1, 50);
		String text = "hp";
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString(text, getX() + 20, getY() + 60);
	}
	
	//////////RENDER ////////////
	
	private MouseController mouseController = new MouseController(this);

	@Override
	public MouseController getMouseController() {
		return  mouseController;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(mouseOver(mx, my, getRect()))
		{
			System.out.println("hello world");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
