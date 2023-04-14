package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;

import javax.swing.*;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 8934221359034362015L;

	private JCustomTitleBar tb;
	private JPanel panels;

	public MainFrame() {
		/* Frame Configs */
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(960, 540);
		setResizable(false);
		setLayout(null);
		setFont(new Font("Robot", Font.PLAIN, 16));
		setUndecorated(true);
		setTitle("Hotel Alura");

		init();
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Initialize components
	 */
	private void init() {
		/* Add panels */
		panels = new JPanel(new CardLayout());
		panels.setBounds(0, 0, getWidth(), getHeight());
		panels.add("menuPrincipal", new MenuPrincipal(this));
		panels.add("login", new Login(this));
		panels.add("menuUsuario", new MenuUsuario(this));
		panels.add("busqueda", new Busqueda(this));
		add(panels);
		
		tb = new JCustomTitleBar(this);
		add(tb);
		
		setComponentZOrder(tb, 0);
		setComponentZOrder(panels, 1);
		tb.repaint();
	}

	/**
	 * Change between panels
	 * 
	 * @param panelName
	 */
	public void setPanel(String panelName) {
		((CardLayout) panels.getLayout()).show(panels, panelName);
		tb.repaint();
	}

}
