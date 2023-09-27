package com.sunsigne.reversedrebecca.system.controllers.gamepad;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.sunsigne.reversedrebecca.pattern.GameTimer;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class GamepadUpdate implements Runnable {

	private ControllerEnvironment env;
	private Class<? extends ControllerEnvironment> clazz;

	public GamepadUpdate() {
		env = ControllerEnvironment.getDefaultEnvironment();
		clazz = env.getClass();
	}

	////////// GAMEPAD ////////////

	private static Controller[] controllers;

	protected static Controller[] getRegisteredGamepad() {
		if (running)
			return controllers;

		controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		return controllers;
	}

	////////// THREAD ////////////

	private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

	public void start() {
		run();
		executorService.scheduleAtFixedRate(this, 0, 100, TimeUnit.MILLISECONDS);
	}

	public void stop() {
		executorService.shutdown();
	}

	////////// MAIN LOOP ////////////

	public static boolean running;

	@Override
	public void run() {
		if (activedMemoryLeakPrevention)
			return;

		running = true;

		GamepadManager.currentControllers = null;
		Field field = null;

		// System.err.println("Scanning USB ports (for Gamepad detection)");

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

}
