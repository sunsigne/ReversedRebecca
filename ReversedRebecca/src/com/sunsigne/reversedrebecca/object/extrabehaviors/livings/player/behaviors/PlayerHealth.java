package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.behaviors;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.behaviors.Blinking;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;

public class PlayerHealth implements TickBehavior {

	public PlayerHealth(Player player) {
		this.player = player;
		maxhp = 3;
		hp = maxhp;
	}

	////////// BEHAVIOR ////////////

	private Player player;

	@Override
	public Player getExtraBehaviorsObject() {
		return player;
	}

	////////// HP ////////////

	///// max hp /////

	private int maxhp;

	public int getMaxHp() {
		return maxhp;
	}

	public void setMaxHp(int maxhp) {
		this.maxhp = maxhp;
	}

	public boolean isFullHp() {
		return hp == maxhp;
	}

	public void setFullHp() {
		hp = maxhp;
	}

	///// hp /////

	private int hp;

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void addHp() {
		addHp(1);
	}

	public void addHp(int amount) {
		if (hp + amount > maxhp)
			setFullHp();
		else
			hp = hp + amount;
	}

	public void removeHp() {
		removeHp(1);
	}

	public void removeHp(int amount) {
		if (isRecovering)
			return;

		hp = hp - amount;
		setRecovering(true);
	}

	private boolean isRecovering;

	public void setRecovering(boolean isRecovering) {
		this.isRecovering = isRecovering;
		if (isRecovering)
			time = RECOVERING_TIME;
	}

	////////// TICK ////////////

	private final int RECOVERING_TIME = 30;
	private int time = RECOVERING_TIME;

	@Override
	public void tick() {
		recoveringProcess();

		if (hp <= 0)
			kill();
	}

	private Behavior blinking;

	private void recoveringProcess() {
		if (!isRecovering)
			return;

		time--;

		if (time == RECOVERING_TIME - 1) {
			blinking = new Blinking(player, time);
			player.addBehavior(blinking);
		}

		if (time <= 0) {
			setRecovering(false);
			time = RECOVERING_TIME;
		}
	}

	private void kill() {
		player.removeBehavior(player.canInteract);
		player.removeBehavior(player.userCanKeyMove);
		player.setFacing(DIRECTION.NULL);
	}

}
