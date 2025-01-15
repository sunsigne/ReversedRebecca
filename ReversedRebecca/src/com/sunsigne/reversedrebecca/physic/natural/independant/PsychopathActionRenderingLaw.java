package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Interactive;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class PsychopathActionRenderingLaw extends IndependantLaw {

	////////// INDEPENDANT LAW ////////////

	private static IndependantLaw independantLaw = new PsychopathActionRenderingLaw();

	@Override
	public IndependantLaw getIndependantLaw() {
		return independantLaw;
	}

	////////// TICK ////////////

	private static boolean psycopathActionOngoing;
	private static String lastMusic;

	@Override
	public void tick(Updatable object) {
		if (object instanceof World == false)
			return;

		flag = false;
		Interactive interactive = SingleInteractivityLaw.getCurrentInteractor();
		if (interactive == null)
			return;

		if (psycopathActionOngoing == containsPsychopathAction(interactive))
			return;
		psycopathActionOngoing = containsPsychopathAction(interactive);

		if (psycopathActionOngoing) {
			lastMusic = SoundTask.getMusicName();
			new SoundTask().stopMusic(true);
		} else {
			if (lastMusic != null)
				new SoundTask().playMusic(lastMusic, true, true);
		}
	}

	private boolean containsPsychopathAction(Interactive interactive) {
		if (interactive.isDisabled())
			return false;

		if (interactive.canPlayerInteract(true) == false)
			return false;

		TripleAction tripleAction = interactive.getTripleAction();
		Action actionOne = tripleAction.getAction(0);
		Action actionTwo = tripleAction.getAction(1);
		Action actionThree = tripleAction.getAction(2);

		if (actionOne != null && actionOne.getDisplayedText().contains("(PSYCHOPATH)"))
			return true;
		if (actionTwo != null && actionTwo.getDisplayedText().contains("(PSYCHOPATH)"))
			return true;
		if (actionThree != null && actionThree.getDisplayedText().contains("(PSYCHOPATH)"))
			return true;

		return false;
	}

	////////// RENDER ////////////

	private boolean flag;

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {
		if (psycopathActionOngoing == false)
			return;

		if (object.getHandler() != LAYER.WORLD_TEXT.getHandler())
			return;

		if (flag)
			return;
		flag = true;
		
		new TransluantLayer().drawPsycopath(g);
	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
