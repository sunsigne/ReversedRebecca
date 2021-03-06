package com.sunsigne.reversedrebecca.system;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.sunsigne.reversedrebecca.Infos;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class Window {

	public static final int WIDHT = 1920;
	public static final int HEIGHT = 1080;

	protected Window(Game game) {

		game.setMinimumSize(new Dimension(WIDHT, HEIGHT));
		game.setMaximumSize(new Dimension(WIDHT, HEIGHT));
		game.setPreferredSize(new Dimension(WIDHT, HEIGHT));

		ImageIcon icon = Infos.ICON;
		JFrame frame = new JFrame(Infos.NAME);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.setIconImage(icon.getImage());
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.pack();
	}

}
