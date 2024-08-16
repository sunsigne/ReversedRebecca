package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Interactive;
import com.sunsigne.reversedrebecca.object.puzzler.RequirementBubbleObject;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class SingleInteractivityLaw extends IndependantLaw {

	////////// INDEPENDANT LAW ////////////

	private static IndependantLaw independantLaw = new SingleInteractivityLaw();

	@Override
	public IndependantLaw getIndependantLaw() {
		return independantLaw;
	}

	////////// INTERACTIVE ////////////

	private static Interactive singleInteractor;

	public static Interactive getCurrentInteractor() {
		return singleInteractor;
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object instanceof World)
			singleInteractor = null;

		if (object instanceof Interactive == false)
			return;

		Interactive interactive = (Interactive) object;
		if (interactive.canPlayerInteract() == false)
			return;

		if (interactive.getTripleAction() != null && interactive.getTripleAction().cannotDoAnyAction() == false)
			singleInteractor = interactive;
	}

	////////// RENDER ////////////

	private RequirementBubbleObject singleBubble;
	private boolean flag;

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {
		if (object instanceof World)
			singleBubble = null;

		if (object instanceof RequirementBubbleObject == false)
			return;

		RequirementBubbleObject bubble = (RequirementBubbleObject) object;

		if (singleBubble != null) {
			flag = true;
			Graphics2D g2d = (Graphics2D) g;
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0));
		}

		if (bubble.isVisible())
			singleBubble = bubble;
	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {
		if (flag == false)
			return;

		flag = false;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

}
