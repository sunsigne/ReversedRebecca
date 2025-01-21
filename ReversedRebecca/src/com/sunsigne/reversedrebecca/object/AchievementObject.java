package com.sunsigne.reversedrebecca.object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.achievement.Achievement;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;

public class AchievementObject extends GameObject {

	public AchievementObject(Achievement achievement) {
		this(achievement, Window.WIDHT / 2 - 13 * Size.XS, -Size.L);
	}

	public AchievementObject(Achievement achievement, int x, int y) {
		super(x, y, 5 * Size.XL, Size.L);
		this.achievement = achievement;
		this.unlocked = achievement.isUnlocked();
	}

	private Achievement achievement;
	private boolean unlocked;

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "ACHIEVEMENT";
		return clazz + " " + achievement.getName().toUpperCase() + " : " + getX() + "-" + getY();
	}

	////////// TEXT ////////////

	private String title;
	private String text;

	public String getTitle() {
		if (title == null)
			title = new Translatable().getTranslatedText(achievement.getName() + "title", FilePath.ACHIEVEMENT);
		return title;
	}

	public String getText() {
		if (text == null) {
			if (achievement.isHidden() && achievement.isUnlocked() == false)
				text = "???";
			else
				text = new Translatable().getTranslatedText(achievement.getName() + "text", FilePath.ACHIEVEMENT);
		}

		return text;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.MOVER;
	}
	
	////////// TICK ////////////

	public void popup() {
		new SoundTask().playSound(SOUNDTYPE.SOUND, "achievement");
		time = MAX_TIME;
	}

	private int time;
	private final int MAX_TIME = 400;
	private final int DELAY = 30;
	private final int SPEED = 5;

	@Override
	public void tick() {
		if (time == 0)
			return;

		time--;

		// goes up
		if (time > MAX_TIME - DELAY)
			setVelY(SPEED);

		// stop
		if (time == MAX_TIME - DELAY)
			setMotionless();

		// goes down
		if (time < DELAY)
			setVelY(-SPEED);

		// destroy
		if (time == 1)
			LAYER.DEBUG.getHandler().removeObject(this);
	}

	////////// TEXTURE ////////////

	private final Font fontTitle = new FontTask().createNewFont("dogicabold.ttf", 25f);
	private final Font fontText = new FontTask().createNewFont("dogicabold.ttf", 18f);

	@Override
	public void render(Graphics g) {
		var image = unlocked ? achievement.getImage() : achievement.getImageLocked();
		g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);

		if (unlocked)
			drawTitle(g);

		drawText(g);
	}

	private void drawTitle(Graphics g) {
		Color text_color = new Color(255, 204, 0);
		Color shadow_color = new Color(255, 163, 0, 80);
		int[] rect = new int[] { getX() + Size.XL, getY() - getHeight() / 5, getWidth(), getHeight() };

		new TextDecoration().drawShadowedString(g, fontTitle, getTitle(), text_color, shadow_color, DIRECTION.LEFT,
				rect);
	}

	private void drawText(Graphics g) {
		Color text_color = Color.WHITE;
		Color shadow_color = Color.BLACK;
		int height = unlocked ? getHeight() / 5 : 0;
		int[] rect = new int[] { getX() + Size.XL + 2, getY() + height, getWidth(), getHeight() };

		new TextDecoration().drawShadowedString(g, fontText, getText(), text_color, shadow_color, DIRECTION.LEFT, rect);
	}

}
