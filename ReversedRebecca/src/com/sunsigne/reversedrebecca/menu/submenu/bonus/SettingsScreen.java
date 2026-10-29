package com.sunsigne.reversedrebecca.menu.submenu.bonus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.menu.submenu.BonusScreen;
import com.sunsigne.reversedrebecca.menu.submenu.SubMenuScreen;
import com.sunsigne.reversedrebecca.menu.submenu.options.ResetScreen;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.menu.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.menu.buttons.PreferencePreview;
import com.sunsigne.reversedrebecca.object.menu.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.menu.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.object.menu.buttons.TitleScreenTextSelectable;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingOption;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingOption.LIVING_TYPE;
import com.sunsigne.reversedrebecca.object.piranha.living.player.RebeccaDesign;
import com.sunsigne.reversedrebecca.object.piranha.living.player.RebeccaDesign.DESIGN_TYPE;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.piranha.condition.global.GothicCondition;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public class SettingsScreen extends SubMenuScreen implements SheetableImage {

	public SettingsScreen() {
		super(REBECCA_DESIGN);
		loadText();

		createLeftArrowButton(DIRECTION.LEFT);
		createRightArrowButton(DIRECTION.RIGHT);
		createResetButton();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "settings_xxl";
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new BonusScreen(BACK);
	}

	////////// TEXT ////////////

	private TitleScreenText preference;
	private TitleScreenText rebeccaType;
	private TitleScreenText preferenceType;
	private PreferencePreview preferencePreview;

	private static final int gap = -278;

	private void loadText() {
		String text = null;
		int x = 325 + 416 - 269;
		int y = 323;

		// preference
		preference = new TitleScreenText(translate("preference"), x + gap, y);
		LAYER.MENU.addObject(preference);

		// classic / gothic
		String designTypeName = RebeccaDesign.getType().getName();
		text = translate("rebecca" + designTypeName);
		rebeccaType = new TitleScreenTextSelectable(text, x + gap, y + 104);
		LAYER.MENU.addObject(rebeccaType);

		// man / woman
		String livingTypeName = LivingOption.getType().getName();
		text = translate("preference" + livingTypeName);
		preferenceType = new TitleScreenTextSelectable(text, x + gap, y + 208);
		LAYER.MENU.addObject(preferenceType);

		// preview of the preference
		preferencePreview = new PreferencePreview(x + gap + 147, y + 330 + 10);
		LAYER.MENU.addObject(preferencePreview);

		TitleScreenText resetDetail;

		// save, achievements, comics ...
		text = translate("HardResetDetail" + "1");
		resetDetail = new TitleScreenText(text, x - gap, y + 210 + 79);
		resetDetail.setFontSize(18f);
		LAYER.MENU.addObject(resetDetail);

		// ... drawing and pixel art ...
		text = translate("HardResetDetail" + "2");
		resetDetail = new TitleScreenText(text, x - gap, y + 210 + 113);
		resetDetail.setFontSize(18f);
		LAYER.MENU.addObject(resetDetail);

		// ... Dave never forget
		text = translate("HardResetDetail" + "3");
		resetDetail = new TitleScreenText(text, x - gap, y + 314 + 113);
		resetDetail.setFontSize(18f);
		LAYER.MENU.addObject(resetDetail);

	}

	////////// BUTTONS ////////////

	private ButtonObject resetButton;

	private void createArrowButton(String text, DIRECTION direction, int x, int y, GenericListener onPress) {
		ButtonObject button = new TitleScreenButton(text, 710 - 269 + x, 439 + y, 60, 60, onPress, null);
		((TitleScreenButton) button).setFontSize(40f);
		arrow_buttons.put(direction, button);
		LAYER.MENU.addObject(button);
	}

	private void createLeftArrowButton(DIRECTION direction) {
		GenericListener onPress = null;

		onPress = () -> choosePreviousRebeccaDesignType();
		createArrowButton("<", direction, 0 + gap, 0, onPress);
		onPress = () -> choosePreviousLivingOptionType();
		createArrowButton("<", direction, 0 + gap, 104, onPress);
	}

	private void createRightArrowButton(DIRECTION direction) {
		GenericListener onPress = null;

		onPress = () -> chooseNextRebeccaDesignType();
		createArrowButton(">", direction, 420 + gap, 0, onPress);
		onPress = () -> chooseNextLivingOptionType();
		createArrowButton(">", direction, 420 + gap, 104, onPress);
	}

	private void createResetButton() {
		LAYER.MENU.getHandler().removeObject(resetButton);

		GenericListener onPress = () -> createConfirmButton();
		resetButton = new TitleScreenButton(translate("HardReset"), 741 - 269 - gap, 323 + 208, 415, 80, onPress,
				null) {

			@Override
			public String getSound() {
				return "button_validate";
			}
		};

		LAYER.MENU.addObject(resetButton);
		buttons.put(HARD_RESET, resetButton);
	}

	private void createConfirmButton() {
		LAYER.MENU.getHandler().removeObject(resetButton);

		GenericListener onPress = () -> new ResetScreen();
		resetButton = new TitleScreenButton(translate("Confirm"), 741 - 269 - gap, 323 + 208, 415, 80, onPress, null) {

			@Override
			public String getSound() {
				return "button_validate";
			}
		};

		LAYER.MENU.addObject(resetButton);
		buttons.put(HARD_RESET, resetButton);
	}

	////////// BUTTON ACTION ////////////

	private void choosePreviousRebeccaDesignType() {
		DESIGN_TYPE design_type = RebeccaDesign.getType().getPrevious();
		new RebeccaDesign().registerType(design_type);
		refresh();
	}

	private void chooseNextRebeccaDesignType() {
		DESIGN_TYPE design_type = RebeccaDesign.getType().getNext();
		new RebeccaDesign().registerType(design_type);
		refresh();
	}

	private void choosePreviousLivingOptionType() {
		LIVING_TYPE living_type = LivingOption.getType().getPrevious();
		new LivingOption().registerType(living_type);
		refresh();
	}

	private void chooseNextLivingOptionType() {
		LIVING_TYPE living_type = LivingOption.getType().getNext();
		new LivingOption().registerType(living_type);
		refresh();
	}

	private void refresh() {
		DESIGN_TYPE designType = RebeccaDesign.getType();
		String designTypeName = designType.getName();
		rebeccaType.setText(translate("rebecca" + designTypeName));
		String livingTypeName = LivingOption.getType().getName();
		preferenceType.setText(translate("preference" + livingTypeName));

		String value = designType == DESIGN_TYPE.GOTHIC ? "on" : "off";
		new GothicCondition().registerValue(value);
		preferencePreview.refresh();

		createResetButton();
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 2;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	@Override
	public int getSheetWidth() {
		return 3 * 16;
	}

	private BufferedImage gamepad_instruction_image;

	protected BufferedImage get_gamepad_instruction_image() {
		if (gamepad_instruction_image == null)
			gamepad_instruction_image = new ImageTask().loadImage("textures/menu/" + "gamepad_nav", true);
		return gamepad_instruction_image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		super.render(g);

		if (ControllerManager.getInstance().isUsingGamepad() == false)
			return;

		g.drawImage(get_gamepad_instruction_image(), 890 + gap, 313, 120, 120, null);
		g.drawImage(get_gamepad_instruction_image(), 890 - gap, 313, 120, 120, null);
	}

	////////// PRESET MOUSE POS ////////////

	private HashMap<DIRECTION, ButtonObject> arrow_buttons = new HashMap<>();

	public static final PresetMousePos REBECCA_DESIGN = new PresetMousePos(925 - 269 + gap, 460);
	public static final PresetMousePos PREFERENCE_TYPE = new PresetMousePos(925 - 269 + gap, 570);
	public static final PresetMousePos HARD_RESET = new PresetMousePos(925 - 269 - gap, 570);

	////////// GAMEPAD ////////////

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;

		if (isPresetNull())
			setPreset(REBECCA_DESIGN);
		else if (e.getKey() == ButtonEvent.B) {
			setPreset(BACK, false);
			buttons.get(BACK).mousePressed(null);
		}

		else if (getPreset() == REBECCA_DESIGN)
			actionRebeccaPressed(e);
		else if (getPreset() == PREFERENCE_TYPE)
			actionPreferencePressed(e);
		else if (getPreset() == HARD_RESET)
			hardResetPressed(e);
		else if (getPreset() == BACK)
			backPressed(e);
	}

	private void actionRebeccaPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT) {
			var sound = arrow_buttons.get(DIRECTION.LEFT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			choosePreviousRebeccaDesignType();
		}

		else if (e.getKey() == ButtonEvent.RIGHT) {
			var sound = arrow_buttons.get(DIRECTION.RIGHT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			chooseNextRebeccaDesignType();
		}

		else if (e.getKey() == ButtonEvent.UP) {
			setPreset(HARD_RESET);
		}

		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(PREFERENCE_TYPE);
	}

	private void actionPreferencePressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT) {
			var sound = arrow_buttons.get(DIRECTION.LEFT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			choosePreviousLivingOptionType();
		}

		else if (e.getKey() == ButtonEvent.RIGHT) {
			var sound = arrow_buttons.get(DIRECTION.RIGHT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			chooseNextLivingOptionType();
		}

		else if (e.getKey() == ButtonEvent.UP) {
			setPreset(REBECCA_DESIGN);
		}

		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);
	}

	private void hardResetPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP) {
			setPreset(REBECCA_DESIGN);
		}

		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);

		else if (e.getKey() == ButtonEvent.A)
			buttons.get(HARD_RESET).mousePressed(null);
	}

	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(PREFERENCE_TYPE);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}

}
