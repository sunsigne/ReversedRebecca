package com.sunsigne.reversedrebecca.object.interactive;

import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
import com.sunsigne.reversedrebecca.pattern.PlayerFinder;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class TextAction implements Updatable {

	public TextAction(InteractiveControlObject object, TripleAction tripleAction) {
		this.object = object;
		this.tripleAction = tripleAction;
	}

	private InteractiveControlObject object;
	private TripleAction tripleAction;

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	private Font font = new FontTask().createNewFont("square_sans_serif_7", 20f);

	private void drawFacingText(Graphics g, DIRECTION facing, String text) {
		int[] rect = getShiftedRect(facing);

		DIRECTION centeredText = DIRECTION.NULL;
		if (facing == DIRECTION.LEFT)
			centeredText = DIRECTION.RIGHT;
		if (facing == DIRECTION.RIGHT)
			centeredText = DIRECTION.LEFT;

		new TextDecoration().drawOutlinesString(g, text, font, centeredText, rect);
	}

	private void drawDualActionsText(Graphics g, Player player, Action action1, Action action2) {
		if (player.getFacing() == DIRECTION.UP || player.getFacing() == DIRECTION.DOWN) {
			drawFacingText(g, DIRECTION.LEFT, action1.getDisplayedText());
			drawFacingText(g, DIRECTION.RIGHT, action2.getDisplayedText());
		} else {
			drawFacingText(g, DIRECTION.UP, action1.getDisplayedText());
			drawFacingText(g, DIRECTION.DOWN, action2.getDisplayedText());
		}
	}

	@Override
	public void render(Graphics g) {
		if (tripleAction == null)
			return;

		// player can't interact
		if (!object.canPlayerInterfact())
			return;

		Player player = new PlayerFinder().getPlayer();

		// no action can be performed
		if (tripleAction.cannotDoAnyAction()) {
			drawFacingText(g, player.getFacing(), tripleAction.getNoActionText());
			return;
		}

		// if ONE action can be performed, draw it in front of the player
		if (tripleAction.canDoExactlyOneAction()) {
			Action action = tripleAction.getTheOnlyOnePerformableAction();
			drawFacingText(g, player.getFacing(), action.getDisplayedText());
			return;
		}

		// if TWO actions can be performed, draw them perpendicularly to the player
		if (tripleAction.canDoExactlyTwoActions()) {
			Action[] action = tripleAction.getTheOnlyTwoPerformableActions();
			drawDualActionsText(g, player, action[0], action[1]);
			return;
		}

		// if THREE actions, draw the second in front of the player
		if (tripleAction.canDoExactlyThreeActions()) {
			Action action = tripleAction.getAction(1);
			drawFacingText(g, player.getFacing(), action.getDisplayedText());
			drawDualActionsText(g, player, tripleAction.getAction(0), tripleAction.getAction(2));
			return;
		}
	}

	private int[] getShiftedRect(DIRECTION facing) {

		switch (facing) {
		case LEFT:
			return new int[] { object.getX() - (Size.M + Size.XS / 2), object.getY(), object.getWidth(),
					object.getHeight() };
		case RIGHT:
			return new int[] { object.getX() + Size.M + Size.XS / 2, object.getY(), object.getWidth(),
					object.getHeight() };
		case UP:
			return new int[] { object.getX(), object.getY() - Size.XL / 2, object.getWidth(), object.getHeight() };
		case DOWN:
			return new int[] { object.getX(), object.getY() + Size.XL / 2, object.getWidth(), object.getHeight() };
		default:
			return object.getRect();
		}
	}

}
