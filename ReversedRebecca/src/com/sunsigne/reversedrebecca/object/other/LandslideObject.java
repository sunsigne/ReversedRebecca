package com.sunsigne.reversedrebecca.object.other;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.RubbleObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;

public class LandslideObject extends GameObject implements RenderFree, CollisionReactor {

	public LandslideObject(int x, int y, RubbleObject rubble) {
		super(x, y);
		this.rubble = rubble;
	}

	private RubbleObject rubble;
	
	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "LANDSLIDE";
		var goal = new GoalObject(getX(), getY(), true);
		return clazz + " : " + goal.getX() + "-" + goal.getY();
	}

	////////// TICK ////////////

	private final int LANDSLIDE_TIME = 180;
	private int time = LANDSLIDE_TIME;

	@Override
	public void tick() {
		playSound();
		displayText();
		pushLiving();
		createRubble();

		time--;

		if (time <= 0)
			getHandler().removeObject(this);
	}

	private void playSound() {
		if (time != LANDSLIDE_TIME)
			return;

		new SoundTask().playSound(SOUNDTYPE.SOUND, "landslide");
	}

	private String text = new Translatable().getTranslatedText("LANDSLIDE", FilePath.BONUS_TEXT);
	private RandomGenerator rad = new RandomGenerator();
	
	private void displayText() {
		if (time % 10 != 0)
			return;

		int radX = getX() + rad.getIntBetween(-getSize(), getSize());
		int radY = getY() + getSize() + rad.getIntBetween(-getSize(), getSize());
		
		BonusText bonusText = new BonusText(text, radX, radY);
		LAYER.WORLD_TEXT.addObject(bonusText);
	}

	private void pushLiving() {
		if (time != 95)
			return;

		// hurt livings and push them if still alive
	}

	private void createRubble() {
		if (time != 90)
			return;

		getHandler().addObject(rubble);
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingSight() {
		return false;
	}

	@Override
	public boolean isBlockingPath() {
		return false;
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		blockPath(detectorObject);
	}

}
