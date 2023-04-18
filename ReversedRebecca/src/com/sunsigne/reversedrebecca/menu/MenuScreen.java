package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.sunsigne.reversedrebecca.Infos;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePreseting;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public abstract class MenuScreen implements Updatable, TickFree, GamepadEvent, MousePreseting {

	public MenuScreen(PresetMousePos defaultPreset) {
		LAYER.MENU.getHandler().clear();
		LAYER.MENU.addObject(this);
		loadImages();
		loadGamepadSetup(defaultPreset);
	}

	////////// USEFUL ////////////

	protected String translate(String text) {
		return new Translatable().getTranslatedText(text, FilePath.MENU);
	}

	////////// TEXTURE ////////////

	private static BufferedImage title_img;

	private void loadImages() {

		if (World.get() == null)
			drawRebeccasRoom();

		if (title_img == null)
			drawTitle();
	}

	private void drawRebeccasRoom() {
		World world = new World(new Save().getLevel(true));
		world.getLevelStats().getStopWatch().stop();
		new PlayerFinder().setUserAllowedToControlPlayer(false);
	}

	private void drawTitle() {
		title_img = new ImageTask().loadImage("textures/menu/" + "title");
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		drawTransluantLayer(g2d);
		drawTitle(g);
		drawVersion(g);
	}

	private void drawTransluantLayer(Graphics2D g2d) {
		int alpha = 155;
		Color purple = new Color(0, 25, 195, alpha);
		Color black = new Color(0, 0, 0, alpha);

		GradientPaint up_paint = new GradientPaint(0, 0, purple, 0, Window.HEIGHT / 4, black);
		g2d.setPaint(up_paint);
		g2d.fillRect(0, 0, Window.WIDHT, Window.HEIGHT / 2);

		GradientPaint down_paint = new GradientPaint(0, 3 * Window.HEIGHT / 4, black, 0, Window.HEIGHT, purple);
		g2d.setPaint(down_paint);
		g2d.fillRect(0, Window.HEIGHT / 2, Window.WIDHT, Window.HEIGHT / 2);
	}

	private void drawTitle(Graphics g) {
		g.drawImage(title_img, 525, 80, 856, 380, null);
	}

	private void drawVersion(Graphics g) {
		var font = new Font("arial", 1, 30);
		int[] rect = new int[] { 940, 460, 0, 0 };

		new TextDecoration().drawOutlinesString(g, font, Infos.VERSION, DIRECTION.NULL, rect);
	}

	////////// PRESET MOUSE POS ////////////

	protected HashMap<PresetMousePos, ButtonObject> buttons = new HashMap<>();

	private PresetMousePos preset;
	private PresetMousePos defaultPreset;
	
	@Override
	public PresetMousePos getDefaultPreset() {
		return defaultPreset;
	}
	
	@Override
	public PresetMousePos getPreset() {
		return preset;
	}

	@Override
	public void setPreset(PresetMousePos preset) {
		this.setPreset(preset, true);
	}
	
	private void setPreset(PresetMousePos preset, boolean sound) {
		this.preset = preset;
		preset.moveMouse();

		if (isPresetNull() == false && sound)
			new SoundTask().playSound(SOUNDTYPE.SOUND, "button");
	}
		
	////////// GAMEPAD ////////////

	private GamepadController gamepadController = new GamepadController(this);

	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}

	private void loadGamepadSetup(PresetMousePos defaultPreset) {
		this.defaultPreset = defaultPreset;
		
		if (ControllerManager.getInstance().isUsingGamepad())
			setPreset(defaultPreset, false);
	}

	private boolean pressingButton;

	protected boolean pressingButton() {
		if (pressingButton)
			return true;

		pressingButton = true;
		new GameTimer(3, () -> pressingButton = false);
		return false;
	}

	@Override
	public void buttonReleased(ButtonEvent e) {
		
	}

}
