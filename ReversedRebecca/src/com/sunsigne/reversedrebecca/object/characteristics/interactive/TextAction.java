package com.sunsigne.reversedrebecca.object.characteristics.interactive;

import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.font.TextsOption;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.camera.CameraDependency;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class TextAction implements Updatable {

	public TextAction(Interactive interactive, TripleAction tripleAction) {
		this.interactive = interactive;
		this.tripleAction = tripleAction;
		
		build_no_action_font();
		build_choice_font();
	}

	private Interactive interactive;
	private TripleAction tripleAction;

	////////// TICK ////////////

	@Override
	public void tick() {
		if (tripleAction != interactive.getTripleAction())
			removeObject();
		
		if(LAYER.MENU.getHandler().getList().isEmpty())
			return;
		
		build_no_action_font();
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
		if (!interactive.canPlayerInterfact(true))
			return;

		// no action can be performed
		if (tripleAction.cannotDoAnyAction()) {
			if (tripleAction.getNoActionText() != null)
				drawNoActionText(g, player, tripleAction.getNoActionText());
			return;
		}

		// if ONE action can be performed, draw it in front of the player
		if (tripleAction.canDoExactlyOneAction()) {
			Action action = tripleAction.getTheOnlyOnePerformableAction();
			drawChoiceText(g, player, action.getDisplayedText(), 0);
			return;
		}

		// if TWO actions can be performed, draw them perpendicularly to the player
		if (tripleAction.canDoExactlyTwoActions()) {
			Action[] action = tripleAction.getTheOnlyTwoPerformableActions();
			drawChoiceText(g, player, action[0].getDisplayedText(), -12);
			drawChoiceText(g, player, action[1].getDisplayedText(), +12);
			return;
		}

		// if THREE actions, draw the second in front of the player
		if (tripleAction.canDoExactlyThreeActions()) {
			drawChoiceText(g, player, tripleAction.getAction(0).getDisplayedText(), -24);
			drawChoiceText(g, player, tripleAction.getAction(1).getDisplayedText(), 0);
			drawChoiceText(g, player, tripleAction.getAction(2).getDisplayedText(), +24);
			return;
		}
	}

	///// no action text /////

	private Font no_action_font;

	private void build_no_action_font() {
		no_action_font = new FontTask().createNewFont("square_sans_serif_7.ttf", 20f * TextsOption.getSize());
	}
	
	private void drawNoActionText(Graphics g, Player player, String text) {
		DIRECTION facing = player.getFacing();

		for (DIRECTION tempDirection : DIRECTION.values()) {
			facing = protrudeFixOnBorder(facing, player, tempDirection, true);
		}

		int[] rect = getNoActionRect(facing);

		DIRECTION centeredText = DIRECTION.NULL;
		if (facing == DIRECTION.LEFT)
			centeredText = DIRECTION.RIGHT;
		if (facing == DIRECTION.RIGHT)
			centeredText = DIRECTION.LEFT;

		new TextDecoration().drawOutlinesString(g, no_action_font, text, centeredText, rect);
	}

	private int[] getNoActionRect(DIRECTION facing) {

		switch (facing) {
		case LEFT:
			return new int[] { interactive.getX() - (Size.M + Size.XS / 2), interactive.getY(), interactive.getWidth(),
					interactive.getHeight() };
		case RIGHT:
			return new int[] { interactive.getX() + Size.M + Size.XS / 2, interactive.getY(), interactive.getWidth(),
					interactive.getHeight() };
		case UP:
			return new int[] { interactive.getX(), interactive.getY() - Size.XL / 2, interactive.getWidth(),
					interactive.getHeight() };
		case DOWN:
			return new int[] { interactive.getX(), interactive.getY() + Size.XL / 2, interactive.getWidth(),
					interactive.getHeight() };
		default:
			return interactive.getRect();
		}
	}

	///// choice text /////

	private float font_size;
	private Font choice_font;

	private void build_choice_font() {
		font_size = 17f / (float) Math.sqrt(Window.SCALE_X);
		choice_font = new FontTask().createNewFont("square_sans_serif_7.ttf", font_size * TextsOption.getSize());
	}
	
	private void drawChoiceText(Graphics g, Player player, String text, int gap) {
		DIRECTION facing = player.getFacing();

		facing = protrudeFixOnBorder(facing, player, DIRECTION.LEFT, false);
		facing = protrudeFixOnBorder(facing, player, DIRECTION.RIGHT, false);

		int[] rect = getChoiceRect(player, facing, gap);

		DIRECTION centeredText = DIRECTION.LEFT;
		if (facing == DIRECTION.RIGHT)
			centeredText = DIRECTION.RIGHT;

		new TextDecoration().drawOutlinesString(g, choice_font, text, centeredText, rect);
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
