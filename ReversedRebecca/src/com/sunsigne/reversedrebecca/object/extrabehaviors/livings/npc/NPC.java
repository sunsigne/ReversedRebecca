package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.behaviors.BlockingPath;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.behaviors.InteractWithPlayer;
import com.sunsigne.reversedrebecca.system.Size;

public class NPC extends LivingObject {

	private static final int walking_speed = Size.XS / 5;
	private static final int running_speed = Size.XS / 2;

	public NPC(int x, int y) {
		this("error", x, y);
	}

	public NPC(String name, int x, int y) {
		super(name, x, y, walking_speed);
		addNPCBehaviors();
	}
	
	////////// USEFUL ////////////
	
	public void setRunning(boolean running) {
		if(running)
			this.speed = running_speed;
		else
			this.speed = walking_speed;
	}

	////////// BEHAVIOR ////////////

	public Behavior blockingPath;
	public Behavior interactWithPlayer;

	private void addNPCBehaviors() {

		blockingPath = new BlockingPath(this);
		addBehavior(blockingPath);

		interactWithPlayer = new InteractWithPlayer(this);
		addBehavior(interactWithPlayer);

		// aller à / se tourner vers le joueur quand se dernier lui parle / sauter
		// (living ?)
		// marcher sur place (pour avoir l'air humain) -> vérifier the escapist, faire
		// ça même qand ils travaillent sur un bureau ?
		// faire une blague meta sur le fait précédent "tu n'a jamais remarqué qu'on
		// donnait tous l'impression de marcher sur place ? Weird ..."
	}

}
