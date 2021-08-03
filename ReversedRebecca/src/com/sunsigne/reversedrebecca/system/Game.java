package com.sunsigne.reversedrebecca.system;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.util.ConcurrentModificationException;

import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.handler.HandlerRender;
import com.sunsigne.reversedrebecca.system.handler.HandlerTick;
import com.sunsigne.reversedrebecca.util.AnnotationBank.Singleton;

@Singleton
public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	////////// MAIN ////////////

	public static void main(String args[]) {
		instance = new Game();
		Conductor.start();
	}

	////////// SIGNELTON ////////////

	private Game() {
	}

	private static Game instance = null;

	public static Game getInstance() {
		return instance;
	}

	////////// THREAD ////////////

	private Thread thread;
	private boolean running;

	public synchronized void start() {
		forceLoop();
		if (running)
			return;

		running = true;
		thread = new Thread(this, Window.NAME + "_main");
		thread.start();
		forceLoop();
	}

	public synchronized void stop() {
		running = false;

		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	////////// MAIN LOOP ////////////

	@Override
	public void run() {

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
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
					// some list are sometimes changed during a tick instead of between
					// two ticks, which may cause crash. As the next tick repair the problem,
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

	private void forceLoop() {
		try {
			tick();
			render();
		} catch (Exception e) {
			// same problem as above but can be more annoying.
			// Clearly, It's just better to ignore this catch
			// as this method is called locally.
		}
	}

	////////// TICK ////////////

	private void tick() {
		Point pos = MouseInfo.getPointerInfo().getLocation();
		new GameCursor().setPos(pos);

		HandlerTick.getInstance().tick();
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

		HandlerRender.getInstance().render(g);

		g.dispose();
		bs.show();
	}

}
