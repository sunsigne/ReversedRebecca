package com.sunsigne.reversedrebecca.system.controllers.gamepad;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
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
		//run();
		//executorService.scheduleAtFixedRate(this, 0, 500, TimeUnit.MILLISECONDS);
		
	}

	public void stop() {
		//executorService.shutdown();
	}

	////////// MAIN LOOP ////////////

	public static boolean running;

	private final static String JINPUT_THREAD_TO_KILL = "net.java.games.input.RawInputEventQueue$QueueThread";
	
	@Override
	public void run() {
		if (activedMemoryLeakPrevention)
			return;

		running = true;

		GamepadManager.currentControllers = null;
		Field field = null;
		
		// System.err.println("start thread scan");

		for (final var thread : Thread.getAllStackTraces().keySet()) {
			System.out.println(thread.getClass());
			if(thread.getClass().getName().equalsIgnoreCase(JINPUT_THREAD_TO_KILL)) {
                thread.interrupt();
                try {
					thread.join();
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        }
		
		// System.err.println("finished thread scan");
		
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
