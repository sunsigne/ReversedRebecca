package com.sunsigne.reversedrebecca.system.mainloop;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.util.ConcurrentModificationException;

import com.sunsigne.reversedrebecca.Infos;
import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.system.Snitch;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadAdapter;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadManager;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadUpdate;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	////////// NATIVES ////////////

	static {
		System.setProperty("net.java.games.input.librarypath",
				new File("dependencies/JInput/natives").getAbsolutePath());
	}

	////////// MAIN ////////////

	public static void main(String args[]) {
		instance = new Game();
		new Conductor().startApp();
	}

	////////// SIGNELTON ////////////

	private Game() {
		new Snitch().registerEntry(System.getProperty("line.separator") + "__________");
	}

	private static Game instance;

	public static Game getInstance() {
		return instance;
	}

	////////// THREAD ////////////

	private Thread thread;
	private GamepadUpdate gamepadUpdate;
	private boolean running;

	public synchronized void start() {
		if (running)
			return;

		running = true;
		
		thread = new Thread(this, Infos.NAME + "_main");
		thread.start();
		
		gamepadUpdate = new GamepadUpdate();
		gamepadUpdate.start();
	}

	public synchronized void stop() {
		running = false;

		try {
			gamepadUpdate.stop();
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	////////// GAMEPAD ////////////

	public void addGamepadListener(GamepadAdapter adapter) {
		new GamepadManager().addGamepadListener(adapter);
	}

	public void removeGamepadListener(GamepadAdapter adapter) {
		new GamepadManager().removeGamepadListener(adapter);
	}

	////////// MAIN LOOP ////////////

	public static final int SEC = 60;

	@Override
	public void run() {

		long lastTime = System.nanoTime();
		double amountOfTicks = SEC;
		double ns = 1000000000 / amountOfTicks;

//		int ticks = 0;
//		int frames = 0;

		double delta = 0;
		long timer = System.currentTimeMillis();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			boolean shouldRender = false;

			while (delta >= 1) {

//				ticks++;
				try {
					tick();
				} catch (ConcurrentModificationException | NullPointerException e) {
					// some ticks may be compromised. As the next tick repair the problem,
					// no need to proccess this exception.
				} catch (Exception e) {
					e.printStackTrace();
				}
				delta--;
				shouldRender = true;
			}

			if (shouldRender) {
//				frames++;
				try {
					render();
				} catch (ConcurrentModificationException | NullPointerException e) {
					// same problem as above
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
//				System.out.println("Ticks : " + ticks + ", FPS : " + frames);
//				frames = 0;
//				ticks = 0;
			}
		}
		stop();
	}

	public void forceLoop() {
		try {
			tick();
			render();
		} catch (Exception e) {
			// same problem as above but can be more annoying.
			// Clearly, It's just better to ignore this catch
			// as this method is called rarelly.
		}
	}

	////////// TICK ////////////

	private void tick() {
		GamepadManager.tick();
		SuperHandler.tick();
	}

	////////// RENDER ////////////

	private void render() {

		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, Window.WIDHT, Window.HEIGHT);

		Graphics2D g2d = (Graphics2D) g;
		g2d.scale(Window.SCALE_X, Window.SCALE_Y);

		SuperHandler.render(g);

		g.dispose();
		bs.show();
	}
}
