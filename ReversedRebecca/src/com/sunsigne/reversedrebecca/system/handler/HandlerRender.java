package com.sunsigne.reversedrebecca.system.handler;

import java.awt.Graphics;
import java.util.LinkedList;

import com.sunsigne.reversedrebecca.util.AnnotationBank.Singleton;

@Singleton
public class HandlerRender implements IRender {
	
	//////////SIGNELTON ////////////
	
	private HandlerRender() {
	}
	
	private static HandlerRender instance = null;
	
	public static HandlerRender getInstance() {
		if (instance == null)
			instance = new HandlerRender();
		return instance;
	}
	
	////////// MAP OR LIST ////////////
	
	private LinkedList<IRender> handler_render_list = new LinkedList<>();
	
	public void addObject(IRender renderable) {
		if (renderable != null)
			handler_render_list.add(renderable);
	}
	
	public void removeObject(IRender renderable) {
		if (renderable != null)
			handler_render_list.remove(renderable);
	}
	
	////////// RENDER ////////////
	
	@Override
	public void render(Graphics g) {
		for (IRender tempRender : handler_render_list)
			tempRender.render(g);;
	}
	
	

}
