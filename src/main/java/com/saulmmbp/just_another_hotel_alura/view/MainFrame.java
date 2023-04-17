package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;

import javax.swing.*;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 8934221359034362015L;

	private JPanel root;
	
	public MainFrame() {
		/* Frame Configs */
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1280, 720);
		setResizable(false);
		setTitle("Hotel Alura");

		/* initialize componens */
		init();
		
		/* Center de window */
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Initialize components
	 */
	private void init() {
		/* Add panels */
		root = new JPanel(new CardLayout());
//		root.add("menuPrincipal", new MenuPrincipal(this));
		root.add("menuUsuario", new MenuUsuario(this));
		root.add("busqueda", new Busqueda(this));
		
		add(root);
	}

	/**
	 * Change between panels
	 * 
	 * @param panelName
	 */
	public void setPanel(String panelName) {
		((CardLayout) root.getLayout()).show(root, panelName);
	}

}
