package com.sunsigne.reversedrebecca.object.puzzler.hole.upward;

import com.sunsigne.reversedrebecca.object.puzzler.hole.downward.HoleObject;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class HoleUpwardObject extends HoleObject {

	public HoleUpwardObject(DEV_LVL devDifficulty, DIRECTION facing, int x, int y) {
		super(devDifficulty, facing, x, y);
	}

	public HoleUpwardObject(LVL difficulty, DIRECTION facing, int x, int y) {
		super(difficulty, facing, x, y);
	}

	@Override
	public LAYER getExitLayer(Handler currentHandler) {
		LAYER exit_layer = LAYER.WORLD_CONTENT;
		boolean flag = false;

		for (LAYER tempLayer : LAYER.values()) {
			if (tempLayer.getName().contains("content") == false)
				continue;

			if (currentHandler == tempLayer.getHandler()) {
				flag = true;
				continue;
			}

			if (flag) {
				exit_layer = tempLayer;
				break;
			}
		}

		return exit_layer;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "hole_upward";
	}

}
