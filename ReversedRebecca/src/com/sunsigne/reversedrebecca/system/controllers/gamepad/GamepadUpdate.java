package com.sunsigne.reversedrebecca.system.controllers.gamepad;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.studiohartman.jamepad.ControllerManager;
import com.sunsigne.reversedrebecca.pattern.GameTimer;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class GamepadUpdate implements Runnable {

	private ControllerEnvironment env;
	private Class<? extends ControllerEnvironment> clazz;
	private static ControllerManager jamepad = new ControllerManager();

	public GamepadUpdate() {
		env = ControllerEnvironment.getDefaultEnvironment();
		clazz = env.getClass();
	}

	////////// GAMEPAD ////////////

	static Controller[] controllers;

	protected static Controller[] getRegisteredGamepad() {
		if (running)
			return controllers;

		controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		return controllers;
	}

	////////// THREAD ////////////

	private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

	public void start() {
		jamepad.initSDLGamepad();
		requiredUpdate = jamepad.getNumControllers();

		run();
		executorService.scheduleAtFixedRate(this, 0, 500, TimeUnit.MILLISECONDS);

	}

	public void stop() {
		jamepad.quitSDLGamepad();
		executorService.shutdown();
	}

	////////// MAIN LOOP ////////////

	public static boolean running;
	private int requiredUpdate;

	@Override
	public void run() {
		jamepad.update();

		if (jamepad.getNumControllers() != requiredUpdate)
			updateControllers();

		requiredUpdate = jamepad.getNumControllers();
	}

	private void updateControllers() {
		if (activedMemoryLeakPrevention)
			return;

		running = true;

		GamepadManager.currentControllers = null;
		Field field = null;

		try {
			field = clazz.getDeclaredField("loadedPluginNames");
			field.setAccessible(true);
			field.set(env, new ArrayList<>());

			field = clazz.getDeclaredField("controllers");
			field.setAccessible(true);
			field.set(env, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		var controllers = env.getControllers();

		running = false;
		GamepadUpdate.controllers = controllers;

		updateMemoryLeakPreventer();
	}

	private static boolean activedMemoryLeakPrevention;

	private void updateMemoryLeakPreventer() {
		activedMemoryLeakPrevention = true;
		new GameTimer(1, true, () -> activedMemoryLeakPrevention = false);
	}

	////////// VIBRATION ////////////

	public static void doVibration(int shift) {
		Controller gamepad = GamepadManager.currentControllers;
		if (gamepad == null)
			return;

		jamepad.doVibration(0, getIntensity(shift), getIntensity(shift), 5 * shift);
	}

	private static float getIntensity(float shift) {
		float intensity = shift <= 5 ? shift / 40 : (float) Math.pow(shift / 40, 2);
		if (intensity < 0)
			intensity = 0;
		if (intensity > 1)
			intensity = 1f;

		return intensity;
	}

}
