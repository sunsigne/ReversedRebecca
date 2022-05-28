package com.sunsigne.reversedrebecca.object.buttons;

public class TitleScreenText extends TitleScreenButton {

	public TitleScreenText(String text, int x, int y) {
		this(text, x, y, 415, 80);
	}
	
	public TitleScreenText(String text, int x, int y, int w, int h) {
		super(text, x, y, w, h, null, null);
	}

	////////// TEXT ////////////

	public void setText(String text) {
		this.text = text.toUpperCase();
	}

	////////// MOUSE ////////////

	@Override
	public boolean isClickable() {
		return false;
	}

}
