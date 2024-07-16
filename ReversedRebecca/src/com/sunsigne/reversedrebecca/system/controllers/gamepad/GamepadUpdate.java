package com.sunsigne.reversedrebecca.system.controllers.gamepad;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWJoystickCallback;

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

	private GLFWJoystickCallback joystickCallback;
	
	public void start() {
		
		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if (!GLFW.glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");
		
		System.out.println(GLFW.glfwJoystickPresent(GLFW.GLFW_JOYSTICK_1));

		this.joystickCallback = new GLFWJoystickCallback() {
            @Override
            public void invoke(int jid, int event) {
                if (event == GLFW.GLFW_CONNECTED) {
                    System.out.println("Joystick " + jid + " connected");
                } else if (event == GLFW.GLFW_DISCONNECTED) {
                    System.out.println("Joystick " + jid + " disconnected");
                }
            }
        };
        GLFW.glfwSetJoystickCallback(this.joystickCallback);
		
		run();
		executorService.scheduleAtFixedRate(this, 0, 500, TimeUnit.MILLISECONDS);
		
	}

	public void stop() {
		executorService.shutdown();
	}

	////////// MAIN LOOP ////////////

	public static boolean running;

	private final static String JINPUT_THREAD_TO_KILL = "net.java.games.input.RawInputEventQueue$QueueThread";
	
	@Override
	public void run() {
		if (activedMemoryLeakPrevention)
			return;

		running = true;

		System.out.println(GLFW.glfwJoystickPresent(GLFW.GLFW_JOYSTICK_1));
		/*
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
		*/
		running = false;
		//GamepadUpdate.controllers = controllers;
		
		updateMemoryLeakPreventer();
		
	}

	private static boolean activedMemoryLeakPrevention;

	private void updateMemoryLeakPreventer() {
		activedMemoryLeakPrevention = true;
		new GameTimer(1, true, () -> activedMemoryLeakPrevention = false);
	}

}
