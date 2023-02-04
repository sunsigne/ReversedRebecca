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
	
	public static final float SCALE_X = (float) Toolkit.getDefaultToolkit().getScreenSize().width / WIDHT;
	public static final float SCALE_Y = (float) Toolkit.getDefaultToolkit().getScreenSize().height / HEIGHT;

	protected Window(Game game) {

		game.setMinimumSize(new Dimension(WIDHT, HEIGHT));
		game.setMaximumSize(new Dimension(WIDHT, HEIGHT));
		game.setPreferredSize(new Dimension(WIDHT, HEIGHT));
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
		frame.setLocationRelativeTo(null);
		
	}

}
