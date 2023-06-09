package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class JCustomTitleBar extends JPanel {

	private static final long serialVersionUID = 8841715085532009238L;

	private MainFrame gui;
	private JLabel lblTitle;
	private JButton btnExit;
	private int xMouse, yMouse;

	public JCustomTitleBar(MainFrame gui) {
		this.gui = gui;
		setLayout(null);
		setOpaque(false);
		setBounds(0, 0, gui.getWidth(), 32);
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				titleBarMouseDragged(e);
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				titleBarMousePressed(e);
			}
		});
		
		init();
	}

	private void init() {
		btnExit = new JButton();
		btnExit.setDefaultCapable(false);
		btnExit.setBounds(getWidth() - 48, 0, 48, 28);
		btnExit.setOpaque(false);
		btnExit.setContentAreaFilled(false);
		btnExit.setBorder(BorderFactory.createEmptyBorder());
		btnExit.setIcon(new ImageIcon(getClass().getResource("/images/x-lg.png")));
		btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				JButton btn = (JButton) e.getComponent();
				btn.setOpaque(true);
				btn.setContentAreaFilled(true);
				btn.setBackground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JButton btn = (JButton) e.getComponent();
				btn.setOpaque(false);
				btn.setContentAreaFilled(false);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				JButton btn = (JButton) e.getSource();
				btn.setOpaque(false);
				btn.setContentAreaFilled(false);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		add(btnExit);
		
		lblTitle = new JLabel();
		lblTitle.setText(gui.getTitle());
		lblTitle.setFont(getFont().deriveFont(Font.BOLD, 12f));
		lblTitle.setBounds(0, 0, getWidth(), getHeight());
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitle);
	}
	
	private void titleBarMousePressed(MouseEvent event) {
		xMouse = event.getX();
		yMouse = event.getY();
	}
	
	private void titleBarMouseDragged(MouseEvent event) {
		int x = event.getXOnScreen();
		int y = event.getYOnScreen();
		gui.setLocation(x - xMouse, y - yMouse);
	}

}
