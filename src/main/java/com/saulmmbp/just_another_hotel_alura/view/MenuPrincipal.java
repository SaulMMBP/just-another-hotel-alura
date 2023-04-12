package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;

import javax.swing.*;

public class MenuPrincipal extends JPanel {

	private static final long serialVersionUID = -7295880406625492439L;
	
	private HotelAluraGui gui;
	private JLabel lblCopyright;
	private JLabel lblTitulo;
	private JLabel logo;
	private JButton btnLogin;
	
	public MenuPrincipal(HotelAluraGui gui) {
		this.gui = gui;
		setLayout(null);
		init();
	}
	
	public void init() {
		lblCopyright = new JLabel("Desarrollado por Saul Malagon Martinez © 2023");
		lblCopyright.setFont(gui.getFont().deriveFont(12f));
		lblCopyright.setBounds(0, gui.getHeight() - 32, gui.getWidth(), 32);
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopyright.setForeground(Color.WHITE);
		add(lblCopyright);
		
		logo = new JLabel(new ImageIcon(getClass().getResource("/images/aH-150px.png")));
		logo.setBounds((gui.getWidth() / 8) * 7 - 75, gui.getHeight() / 3 - 75, 150, 150);
		add(logo);
		
		lblTitulo = new JLabel("LOGIN");
		lblTitulo.setBounds((gui.getWidth() / 4) * 3, gui.getHeight() / 2, gui.getWidth() / 4, 24);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(SystemColor.textHighlight);
		lblTitulo.setFont(gui.getFont().deriveFont(Font.BOLD, 20));
		add(lblTitulo);
		
		btnLogin = new JButton(new ImageIcon(getClass().getResource("/images/login.png")));
		btnLogin.setBounds((gui.getWidth() / 8) * 7 - 32, (gui.getHeight() / 3) * 2 - 64, 64, 64);
		btnLogin.setOpaque(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBorderPainted(false);
		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLogin.addActionListener(e -> System.out.println("Ir a login")/* TODO agregar mi login */);
		add(btnLogin);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image bgImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/menu-img.png"));
		g.drawImage(bgImg, -50, 0, null);
		g.setColor(Color.WHITE);
		g.fillRect((gui.getWidth() / 4) * 3, 0, gui.getWidth() / 4, gui.getHeight());
		g.setColor(Color.decode("0x0d8ac7"));
		g.fillRect(0, gui.getHeight() - 32, gui.getWidth(), 32);
	}
}
