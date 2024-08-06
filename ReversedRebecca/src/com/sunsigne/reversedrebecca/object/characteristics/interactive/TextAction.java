package com.sunsigne.reversedrebecca.object.characteristics.interactive;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.object.puzzler.RequirementBubbleObject;
import com.sunsigne.reversedrebecca.pattern.FormattedString;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.font.TextsOption;
import com.sunsigne.reversedrebecca.ressources.font.TextsOption.TEXTS_SIZE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.camera.CameraDependency;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionThreeKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionTwoKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.KeyAnalyzer;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class TextAction implements Updatable {

	public TextAction(Interactive interactive, TripleAction tripleAction) {
		this.interactive = interactive;
		this.tripleAction = tripleAction;

		build_choice_font();
	}

	private Interactive interactive;
	private TripleAction tripleAction;

	////////// TICK ////////////

	private TEXTS_SIZE size;

	@Override
	public void tick() {
		if (tripleAction != interactive.getTripleAction())
			removeObject();

		if (size == TextsOption.getType())
			return;

		size = TextsOption.getType();
		build_choice_font();
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (tripleAction == null)
			return;

		if (tripleAction != interactive.getTripleAction())
			return;

		// player doesn't exist
		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		// player can't interact
		if (!interactive.canPlayerInteractDelayer(true))
			return;

		// no action can be performed
		if (tripleAction.cannotDoAnyAction()) {
			if (tripleAction.getRequirementBubble() != null)
				drawRequirementBubble(player, tripleAction.getRequirementBubble());
			return;
		}

		// if ONE action can be performed, draw it in front of the player
		if (tripleAction.canDoExactlyOneAction()) {
			Action action = tripleAction.getTheOnlyOnePerformableAction();
			drawChoiceText(g, player, action, 0);
			return;
		}

		// if TWO actions can be performed, draw them perpendicularly to the player
		if (tripleAction.canDoExactlyTwoActions()) {
			Action[] action = tripleAction.getTheOnlyTwoPerformableActions();
			drawChoiceText(g, player, action[0], -12);
			drawChoiceText(g, player, action[1], +12);
			return;
		}

		// if THREE actions, draw the second in front of the player
		if (tripleAction.canDoExactlyThreeActions()) {
			drawChoiceText(g, player, tripleAction.getAction(0), -24);
			drawChoiceText(g, player, tripleAction.getAction(1), 0);
			drawChoiceText(g, player, tripleAction.getAction(2), +24);
			return;
		}
	}

	///// requirement bubble /////

	private void drawRequirementBubble(Player player, RequirementBubbleObject requirementBubble) {
		if (thereIsAPiranhaAbove())
			return;

		DIRECTION facing = player.getFacing();

		for (DIRECTION tempDirection : DIRECTION.values()) {
			facing = protrudeFixOnBorder(facing, player, tempDirection, true);
		}

		int[] rect = getRequirementBubbleRect(facing);
		if (facing == DIRECTION.LEFT)
			rect[0] = rect[0] + rect[2] - requirementBubble.getWidth();

		requirementBubble.setX(rect[0]);
		requirementBubble.setY(rect[1]);
		requirementBubble.setVisible(true);
	}

	// fix subliminal display of RequirementBubble when a priority Piranha is on the same spot
	private boolean thereIsAPiranhaAbove() {
		Interactive obj = interactive;
		var list = Handler.getObjectsAtPos(obj.getHandler(), obj.getX(), obj.getY(), obj.getSize(), true);

		for (GameObject tempObj : list.getList()) {
			if (tempObj instanceof PiranhaObject == false)
				continue;

			PiranhaObject tempPiranha = (PiranhaObject) tempObj;
			if(tempPiranha.getTripleAction() == null)
				continue;
			
			if (tempPiranha.canPlayerInteract() && tempPiranha.getTripleAction().cannotDoAnyAction() == false)
				return true;
		}

		return false;
	}

	private int[] getRequirementBubbleRect(DIRECTION facing) {

		int gap = Size.XS / 2;

		switch (facing) {
		case LEFT:
			return new int[] { interactive.getX() - Size.M - gap, interactive.getY() - Size.XS / 2,
					interactive.getWidth(), interactive.getHeight() };
		case RIGHT:
			return new int[] { interactive.getX() + Size.M + gap, interactive.getY() - Size.XS / 2,
					interactive.getWidth(), interactive.getHeight() };
		case UP:
			return new int[] { interactive.getX() - Size.XS / 2, interactive.getY() - Size.L - gap,
					interactive.getWidth(), interactive.getHeight() };
		case DOWN:
			return new int[] { interactive.getX() - Size.XS / 2, interactive.getY() + Size.M + gap,
					interactive.getWidth(), interactive.getHeight() };
		default:
			return interactive.getRect();
		}
	}

	///// choice text /////

	private Font choice_font;

	private void build_choice_font() {
		float size = 20f / (float) Math.sqrt(Window.SCALE_X);
		choice_font = new FontTask().createNewFont("square_sans_serif_7.ttf", size * TextsOption.getSize());
	}

	private void drawChoiceText(Graphics g, Player player, Action action, int gap) {
		DIRECTION facing = player.getFacing();

		facing = protrudeFixOnBorder(facing, player, DIRECTION.LEFT, false);
		facing = protrudeFixOnBorder(facing, player, DIRECTION.RIGHT, false);

		int[] rect = getChoiceRect(player, facing, gap);

		DIRECTION centeredText = DIRECTION.LEFT;
		if (facing == DIRECTION.RIGHT)
			centeredText = DIRECTION.RIGHT;

		Color color = Color.WHITE;

		String text = action.getDisplayedText();
		if (text.contains("(END_LVL)")) {
			text = text.replace("(END_LVL)", "");
			color = new Color(255, 220, 0);
		}

		if (ControllerManager.getInstance().isUsingGamepad()) {
			text = text.concat("   ");
			drawActionGamepadButton(g, player, facing, action, gap);

			while (text.contains("[") && text.contains("]")) {

				BufferedImage gamepadButton = KeyAnalyzer.getGamepadButton(text);
				int key_gap = getGapBeforeWithinKeyText(text, centeredText);
				int key_rect[] = new int[] { player.getX() + Size.M, player.getY() + gap + Size.XS, Size.XS, Size.XS };
				text = new FormattedString().replaceWithinKeyText(text, "  ");

				g.drawImage(gamepadButton, key_rect[0] + key_gap, key_rect[1], key_rect[2], key_rect[3], null);
			}
		}

		new TextDecoration().drawOutlinesString(g, choice_font, text, color, Color.BLACK, centeredText, rect);
	}

	private int getGapBeforeWithinKeyText(String text, DIRECTION facing) {
		int gap = 0;

		if (facing == DIRECTION.RIGHT) {
			text = text.split("\\[")[1];

			switch (TextsOption.getType()) {

			case SMALL:
				gap = (int) (Math.pow(text.length(), 0.58d) + 6);
				break;
			case MEDIUM:
				gap = (int) (Math.pow(text.length(), 0.90d) + 5);
				break;
			case LARGE:
				gap = (int) (Math.pow(text.length(), 0.70d) + 0);
				break;
			}

			gap = -(Size.M - Size.XS / 6)
					- (int) (gap + ((12.6f * Math.pow(text.length(), 1.03d)) * TextsOption.getSize()));
		} else {
			text = text.split("\\[")[0];

			switch (TextsOption.getType()) {

			case SMALL:
				gap = (int) (-Math.pow(text.length(), 0.70d) + 12);
				break;
			case MEDIUM:
				gap = (int) (Math.pow(text.length(), 0.75d) + 13);
				break;
			case LARGE:
				gap = (int) (-Math.pow(text.length(), 0.70d) + 22);
				break;
			}

			gap = (int) (gap + ((13 * Math.pow(text.length(), 1.025d)) * TextsOption.getSize()));
		}

		return gap;
	}

	private void drawActionGamepadButton(Graphics g, Player player, DIRECTION facing, Action action, int gap) {
		int rect[] = new int[] { player.getX() + Size.M, player.getY() + gap + Size.XS, Size.XS, Size.XS };
		if (facing == DIRECTION.RIGHT)
			rect[0] = player.getX() - Size.XS;

		// following line is to display buttons on the left of "right facing texts"
		// rect[0] = rect[0] - (100) - (18 * (int)
		// Math.pow(action.getDisplayedText().length(), 1.04d));

		if (action.getKeyEvent() == ActionOneKey.getKey())
			g.drawImage(ActionOneKey.getGamepadButton(), rect[0], rect[1], rect[2], rect[3], null);
		if (action.getKeyEvent() == ActionTwoKey.getKey())
			g.drawImage(ActionTwoKey.getGamepadButton(), rect[0], rect[1], rect[2], rect[3], null);
		if (action.getKeyEvent() == ActionThreeKey.getKey())
			g.drawImage(ActionThreeKey.getGamepadButton(), rect[0], rect[1], rect[2], rect[3], null);
	}

	private int[] getChoiceRect(Player player, DIRECTION facing, int gap) {
		switch (facing) {
		case RIGHT:
			return new int[] { player.getX() - Size.M, player.getY() + gap, Size.M, Size.M };
		default:
			return new int[] { player.getX() + Size.M, player.getY() + gap, Size.M, Size.M };
		}
	}

	private DIRECTION protrudeFixOnBorder(DIRECTION facing, Player player, DIRECTION border, boolean opposite) {

		if (CameraDependency.CAMERA.isObjectCloseFromBorder(player, border) == false)
			return facing;

		if (CameraDependency.CAMERA.isObjectCloseFromBorder(interactive, border))
			return opposite ? border.getOpposite() : border;

		return facing;

	}

}
