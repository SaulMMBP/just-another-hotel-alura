package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;

import javax.swing.*;

public class HotelAluraGui extends JFrame {

	private static final long serialVersionUID = 8934221359034362015L;
	
	private JCustomTitleBar tb;
	private JPanel panels;
	private Image iconImg;
	
	public HotelAluraGui() {
		/* Frame Configs */
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(960, 540);
		setResizable(false);
		setUndecorated(true);
		setLayout(null);
		setFont(new Font("Robot", Font.PLAIN, 16));
	
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
		
		/* Add title bar */
		tb = new JCustomTitleBar(this);
		tb.setTitle("Hotel Alura");
		iconImg = new ImageIcon(getClass().getResource("/images/aH-40px.png"))
				.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
		tb.setIcon(new ImageIcon(iconImg));
		add(tb);
		
		/* sort components */
		setComponentZOrder(tb, 0);
		setComponentZOrder(panels, 1);
	}
	
	/**
	 * Change between panels
	 * @param panelName
	 */
	public void setPanel(String panelName) {
		((CardLayout) panels.getLayout()).show(panels, panelName);
		tb.repaint();
	}

}
