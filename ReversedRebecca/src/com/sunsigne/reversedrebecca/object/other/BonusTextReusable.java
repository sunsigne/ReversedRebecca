package com.sunsigne.reversedrebecca.object.other;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class BonusTextReusable extends BonusText {

	public BonusTextReusable(String text, int x, int y, boolean importante) {
		super(text, x, y, importante);
		autoReuse();
	}

	private void autoReuse() {
		var handler = LAYER.WORLD_TEXT.getHandler();

		for (Updatable tempUpdatable : handler.getList()) {
			if (tempUpdatable instanceof BonusTextReusable == false)
				continue;

			// if a reusable text exists in the handler, reuse it
			BonusTextReusable tempBonusText = (BonusTextReusable) tempUpdatable;
			if (isReusable(tempBonusText)) {
				reuse(tempBonusText);
				return;
			}
		}

		// else, add this very new one to the handler
		LAYER.WORLD_TEXT.addObject(this);
	}

	private boolean isReusable(BonusTextReusable bonusText) {
		if (bonusText.text.contentEquals(text) == false)
			return false;

		if (bonusText.getX() != getX())
			return false;

		return bonusText.extra_time < MAX_TIME - time;
	}

	private void reuse(BonusTextReusable bonusText) {
		bonusText.setY(getY());
		bonusText.setVelY(-1);
		bonusText.extra_time = MAX_TIME;
	}

	////////// TICK ////////////

	private final int MAX_TIME = 150;
	private int extra_time = MAX_TIME;

	@Override
	public void tick() {
		--extra_time;
		if (extra_time == MAX_TIME - time)
			setVelY(0);

		if (extra_time <= 0)
			LAYER.WORLD_TEXT.getHandler().removeObject(this);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (extra_time > MAX_TIME - time)
			super.render(g);
	}

}
