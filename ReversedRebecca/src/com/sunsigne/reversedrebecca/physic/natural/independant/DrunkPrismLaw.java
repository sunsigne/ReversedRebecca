package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.sunsigne.reversedrebecca.characteristics.drunk.DrunkLevel;
import com.sunsigne.reversedrebecca.characteristics.drunk.DrunkTask;
import com.sunsigne.reversedrebecca.menu.Cutscene;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.system.camera.CameraDependency;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class DrunkPrismLaw extends IndependantLaw implements CameraDependency {

	////////// INDEPENDANT LAW ////////////

	private static IndependantLaw independantLaw = new DrunkPrismLaw();

	@Override
	public IndependantLaw getIndependantLaw() {
		return independantLaw;
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {

	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {
		int drunk = DrunkTask.getDrunk();
		if (drunk <= 0 || object instanceof Player)
			return;

		Graphics2D g2d = (Graphics2D) g;
		new DrunkLevel().beforeObjectRender(g2d, drunk);
	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {
		int drunk = DrunkTask.getDrunk();
		if (drunk <= 0)
			return;

		Graphics2D g2d = (Graphics2D) g;

		if (object instanceof Player == false) {
			new DrunkLevel().afterObjectRender(g2d, drunk);
			return;
		}

		Player player = (Player) object;
		if (drunk < 5 || player.isMotionless() || Cutscene.isRunning())
			return;

		int pixel = 16;
		player.setDisplayXY(+8 * pixel, 0 * pixel);
		player.render(g2d);
		player.resetLastDisplayXY();
	}

}
