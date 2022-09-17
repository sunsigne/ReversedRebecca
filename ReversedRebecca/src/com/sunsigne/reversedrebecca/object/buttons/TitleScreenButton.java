package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.physic.natural.independant.FadeMenuLaw;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;

public class TitleScreenButton extends ButtonObject {

	public TitleScreenButton(String text, int x, int y, int w, int h, GenericListener onPress,
			GenericListener onRelease) {
		super(text.toUpperCase(), x, y, w, h, onPress, onRelease);
	}

	////////// TEXT ////////////
	
	private Font font = new FontTask().createNewFont("dogicabold.ttf", 35f);

	public void setFontSize(float size) {
		font = new FontTask().createNewFont("dogicabold.ttf", size);
	}
	
	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color text_color = new Color(255, 204, 0);
		Color shadow_color = new Color(255, 163, 0, 80);
		int[] rect = getRect();

		if (isSelected()) {
			text_color = new Color(255, 232, 170);
			rect = new int[] { getX(), getY() - 3, getWidth(), getHeight() };
		}

		new TextDecoration().drawShadowedString(g, font, getText(), text_color, shadow_color, getFacing(), rect);
	}

	////////// MOUSE ////////////

	@Override
	public boolean isClickable() {
		// if the menu is fading, buttons are no longer clickable
		PhysicLaw law = PhysicList.getList().getObject(new FadeMenuLaw());
		return ((FadeMenuLaw) law).isFading() == false && super.isClickable();
	}

}
