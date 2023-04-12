package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;

import javax.swing.*;

public class HotelAluraGui extends JFrame {

	private static final long serialVersionUID = 8934221359034362015L;
	
	private JCustomTitleBar tb;
	private JPanel panels;
	
	public HotelAluraGui() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(960, 540);
		setResizable(false);
		setUndecorated(true);
		setLayout(null);
		setFont(new Font("Robot", Font.PLAIN, 16));
	
		initComponents();
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initComponents() {
		panels = new JPanel(new CardLayout());
		panels.setBounds(0, 0, getWidth(), getHeight());
		panels.add("menuPrincipal", new MenuPrincipal(this));
		add(panels);
		
		tb = new JCustomTitleBar(this);
		tb.setTitle("Hotel Alura");
		Image img = new ImageIcon(getClass().getResource("/images/aH-40px.png"))
				.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
		tb.setIcon(new ImageIcon(img));
		add(tb);
		
		setComponentZOrder(tb, 0);
		setComponentZOrder(panels, 1);
	}
	
	public void setPanel(String panelName) {
		((CardLayout) panels.getLayout()).show(panels, panelName);
	}

}
