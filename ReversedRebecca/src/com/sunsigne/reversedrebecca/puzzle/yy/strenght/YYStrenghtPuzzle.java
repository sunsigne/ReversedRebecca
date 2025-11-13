package com.sunsigne.reversedrebecca.puzzle.yy.strenght;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.hud.HUD;
import com.sunsigne.reversedrebecca.object.hud.HUDHealth;
import com.sunsigne.reversedrebecca.object.hud.HUDList;
import com.sunsigne.reversedrebecca.object.puzzle.yy.strenght.StrenghtLauncherObject;
import com.sunsigne.reversedrebecca.object.puzzle.yy.strenght.StrenghtPlayerObject;
import com.sunsigne.reversedrebecca.object.puzzle.yy.strenght.StrenghtProjectileObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;

public abstract class YYStrenghtPuzzle extends Puzzle {

	public YYStrenghtPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning, GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
		new GameCursor().setCursor(null);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "yy_strenght";
	}

	////////// FACTORY ////////////

	@Override
	public PuzzleFactory getFactory() {
		return new YYStrenghtPuzzleFactory();
	}

	////////// PUZZLE ////////////

	public abstract int getPuzzleSpeed();

	public abstract StrenghtPlayerObject getPlayer();

	public abstract StrenghtLauncherObject getLauncher();

	protected void createPlayer() {
		StrenghtPlayerObject player = getPlayer();
		player.setX(getCol(10));
		player.setY(getRow(4) + 3 * Size.XS);

		LAYER.PUZZLE.addObject(player);
	}

	protected void createLauncher() {
		StrenghtLauncherObject launcher = getLauncher();
		launcher.setX(getCol(2) - Size.S);
		launcher.setY(getRow(3) + 3 * Size.XS);

		LAYER.PUZZLE.addObject(launcher);
	}

	private GameList<StrenghtProjectileObject> projectile_list;

	protected void addProjectile(StrenghtProjectileObject projectile) {
		if (projectile_list == null)
			projectile_list = new GameList<>(LISTTYPE.ARRAY);

		projectile_list.addObject(projectile);
	}

	private StrenghtProjectileObject projectile;

	////////// TICK ////////////

	private int time;
	private int loop = 50 * getPuzzleSpeed();

	@Override
	public void tick() {
		if (new PlayerFinder().getPlayer().isDead())
			return;

		time++;

		if (projectile_list.getList().isEmpty() && projectile == null)
			return;

		if (time == loop / 2) {
			new SoundTask().playSound(SOUNDTYPE.SOUND, "chest");
			projectile = projectile_list.getList().get(0);
			projectile_list.removeObject(projectile);
			LAYER.PUZZLE.addObject(projectile);
		}

		if (time == loop) {
			new SoundTask().playSound(SOUNDTYPE.SOUND, "jump");
			projectile.throwing(getPuzzleSpeed());
			projectile = null;
			time = 0;
		}
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 1;
	}

	@Override
	public int getSheetRowCriterion() {
		return 3;
	}

	@Override
	protected BufferedImage getWallTexture() {
		BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "wall");
		return getSheetSubImage(sheet, getSheetColCriterion(), getSheetRowCriterion(), getSheetWidth(),
				getSheetHeight());

	}

	////////// RENDER ////////////

	private Font font = new FontTask().createNewFont("square_sans_serif_7.ttf", 95f);
	private String text;

	private String getText() {
		if (text == null)
			text = new Translatable().getTranslatedText("YYStrenghtJump", FilePath.PUZZLE);
		return text;
	}

	private HUD hud;

	private HUD getHUDHealth() {
		if (hud == null) {
			for (HUD tempHUD : HUDList.getList().getList()) {
				if (tempHUD instanceof HUDHealth)
					hud = tempHUD;
			}
		}
		return hud;
	}

	@Override
	public void render(Graphics g) {
		Color green = new Color(85, 100, 75, 240);
		new TransluantLayer().drawPuzzle(g, green);

		int[] rect = new int[] { Window.WIDHT / 2, Window.HEIGHT / 2, 0, 0 };
		new TextDecoration().drawOutlinesString(g, font, getText(), DIRECTION.NULL, rect);

		drawHealth(g);
	}

	private void drawHealth(Graphics g) {
		int x = getHUDHealth().getX();
		int y = getHUDHealth().getY();
		getHUDHealth().setX(getCol(1));
		getHUDHealth().setY(getRow(1));
		getHUDHealth().render(g);
		getHUDHealth().setX(x);
		getHUDHealth().setY(y);
	}

}
