package com.sunsigne.reversedrebecca.system.controllers.gamepad;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class GamepadUpdate implements Runnable {

	private ControllerEnvironment env;
	private Class<? extends ControllerEnvironment> clazz;

	public GamepadUpdate() {
		env = ControllerEnvironment.getDefaultEnvironment();
		clazz = env.getClass();
		loadedPluginNames = buildLoadedPluginNames();
	}

	private Collection<String> loadedPluginNames;

	@SuppressWarnings("unchecked")
	private Collection<String> buildLoadedPluginNames() {
		Field field = null;

		try {
			field = clazz.getDeclaredField("loadedPluginNames");
			field.setAccessible(true);
			loadedPluginNames = (Collection<String>) field.get(env);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return loadedPluginNames;
	}

	////////// GAMEPAD ////////////
	
	private static Controller[] controllers;
	
	protected static Controller[] getRegisteredGamepad() {
		if(running)
			return controllers;
		
		controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		return controllers;
	}
	
	////////// THREAD ////////////

	private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

	public void start() {
		executorService.scheduleAtFixedRate(this, 1000, 500, TimeUnit.MILLISECONDS);
	}

	public void stop() {
		executorService.shutdown();
	}

	////////// MAIN LOOP ////////////

	private static boolean running;
	
	@Override
	public void run() {
		running = true;
		
		GamepadManager.registeredControllers = null;
		Field field = null;

		// System.out.println("Scanning USB ports (for Gamepad detection)");

		try {
			field = clazz.getDeclaredField("controllers");
			field.setAccessible(true);
			field.set(env, null);
			loadedPluginNames.clear();

		} catch (Exception e) {
			e.printStackTrace();
		}
				
		ControllerEnvironment.getDefaultEnvironment().getControllers();		
		running = false;
		controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
	}

}
