package com.sunsigne.reversedrebecca.system;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.sunsigne.reversedrebecca.Infos;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class Window {

	public static final int WIDHT = 1920;
	public static final int HEIGHT = 1080;

	private static int SCREEN_WIDHT = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	public static float SCALE_X = (float) SCREEN_WIDHT / WIDHT;
	public static float SCALE_Y = (float) SCREEN_HEIGHT / HEIGHT;

	protected Window(Game game) {

		game.setMinimumSize(new Dimension(SCREEN_WIDHT, SCREEN_HEIGHT));
		game.setMaximumSize(new Dimension(SCREEN_WIDHT, SCREEN_HEIGHT));
		game.setPreferredSize(new Dimension(SCREEN_WIDHT, SCREEN_HEIGHT));
		game.setFocusTraversalKeysEnabled(false); // allow TAB as valid key

		ImageIcon icon = Infos.ICON;
		JFrame frame = new JFrame(Infos.NAME);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.setIconImage(icon.getImage());
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.pack();
		// frame.setLocationRelativeTo(null);		
	}

	public static void updateDim(Game game) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		
		int screen_width = dimension.width;
		int screen_height = dimension.height;
		float scale_x = (float) screen_width / WIDHT;
		float scale_y = (float) screen_height / HEIGHT;
		
		if(SCREEN_WIDHT == screen_width && SCREEN_HEIGHT == screen_height && SCALE_X == scale_x && SCALE_Y == scale_y)
			return;
		
		SCREEN_WIDHT = screen_width;
		SCREEN_HEIGHT = screen_height;
		SCALE_X = scale_x;
		SCALE_Y = scale_y;
		
		game.setMinimumSize(new Dimension(SCREEN_WIDHT, SCREEN_HEIGHT));
		game.setMaximumSize(new Dimension(SCREEN_WIDHT, SCREEN_HEIGHT));
		game.setPreferredSize(new Dimension(SCREEN_WIDHT, SCREEN_HEIGHT));
	}
	
}
