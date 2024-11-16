package com.sunsigne.reversedrebecca.pattern.render;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.layers.LayerDualizer;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.camera.CameraDependency;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class TransluantLayer implements CameraDependency {

	public void drawGray(Graphics g, int width, int height) {
		Color gray = new Color(64, 64, 64, 204);
		g.setColor(gray);
		g.fillRect(0, 0, width, height);
	}

	public void drawDarkGray(Graphics g, int width, int height) {
		Color gray = new Color(40, 40, 40, 224);
		g.setColor(gray);
		g.fillRect(0, 0, width, height);
	}

	public void drawPuzzle(Graphics g, Color color) {
		g.setColor(color);
		g.fillRect(Size.L, Size.L, Window.WIDHT - 2 * Size.L, Window.HEIGHT - 2 * Size.L);
	}

	@Override
	public boolean isCameraDependant() {
		return true;
	}
	
	public void drawPsycopath(Graphics g) {
		Updatable blackFilter = new Updatable() {

			@Override
			public void tick() {

			}

			@Override
			public void render(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;

				int alpha = 0;
				Color deep_black = new Color(0, 0, 0, 255);
				Color black = new Color(0, 0, 0, alpha);

				renderDependency(g2d, false);
				
				GradientPaint up_paint = new GradientPaint(0, -Size.M, deep_black, 0, Window.HEIGHT / 3, black);
				g2d.setPaint(up_paint);
				g2d.fillRect(0, 0, Window.WIDHT, Window.HEIGHT / 2);

				GradientPaint down_paint = new GradientPaint(0, Size.M + 2 * Window.HEIGHT / 3, black, 0, Window.HEIGHT,
						deep_black);
				g2d.setPaint(down_paint);
				g2d.fillRect(0, Window.HEIGHT / 2, Window.WIDHT, Window.HEIGHT / 2);
				
				renderDependency(g2d, true);

				removeObject();
			}
		};

		LAYER layer = new LayerDualizer().getLightFromContent(World.get().getHandler());
		layer.addObject(blackFilter);
	}

}
