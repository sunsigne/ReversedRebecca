package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.RectDecoration;
import com.sunsigne.reversedrebecca.pattern.render.RectDecoration.RECTSIZE;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.physic.natural.independant.FadeMenuLaw;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;

public class TitleScreenButton extends ButtonObject {

	public TitleScreenButton(String text, int x, int y, int w, int h, GenericListener onPress,
			GenericListener onRelease) {
		super(text.toUpperCase(), x, y, w, h, onPress, onRelease);
		rectsize = RECTSIZE.X_SMALL;
	}

	////////// TEXT ////////////

	private Font font = new FontTask().createNewFont("dogicabold.ttf", 35f);

	public Font getFont() {
		return font;
	}

	public void setFontSize(float size) {
		font = new FontTask().createNewFont("dogicabold.ttf", size);

		if (size > 40f)
			rectsize = RECTSIZE.SMALL;
	}

	////////// TEXTURE ////////////

	protected Color text_color;
	protected Color shadow_color;

	public Color getTextColor() {
		if (text_color == null)
			text_color = new Color(255, 204, 0);
		return text_color;
	}

	public Color getShadowColor() {
		if (shadow_color == null)
			shadow_color = new Color(255, 163, 0, 80);
		return shadow_color;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color color = getTextColor();
		int[] rect = getRect();

		if (isSelected()) {
			if (ControllerManager.getInstance().isUsingGamepad())
				new RectDecoration().drawRoundRect(g, rect, rectsize);

			color = new Color(255, 232, 170);
			rect = new int[] { getX(), getY() - 3, getWidth(), getHeight() };
		}

		new TextDecoration().drawShadowedString(g, getFont(), getText(), color, getShadowColor(), getFacing(), rect);
	}

	////////// MOUSE ////////////

	@Override
	public boolean isClickable() {
		// if the menu is fading, buttons are no longer clickable
		PhysicLaw law = PhysicList.getList().getObject(new FadeMenuLaw());
		return ((FadeMenuLaw) law).isFading() == false && super.isClickable();
	}

}
