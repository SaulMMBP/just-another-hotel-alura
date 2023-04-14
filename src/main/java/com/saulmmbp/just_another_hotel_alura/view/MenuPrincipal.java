package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;

import javax.swing.*;

public class MenuPrincipal extends JPanel {

	private static final long serialVersionUID = -7295880406625492439L;
	
	private MainFrame gui;
	private JLabel lblCopyright;
	private JLabel lblHeader;
	private JLabel logo;
	private JButton btnLogin;
	private Image bgImg;
	
	public MenuPrincipal(MainFrame gui) {
		this.gui = gui;
		
		/* Panel configs */
		setLayout(null);
		
		init();
	}
	
	/**
	 * Initialize components
	 */
	private void init() {
		/* add copyright label */
		lblCopyright = new JLabel("Desarrollado por Saul Malagon Martinez © 2023");
		lblCopyright.setBounds(0, gui.getHeight() - 32, gui.getWidth(), 32);
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopyright.setForeground(Color.WHITE);
		add(lblCopyright);
		
		/* add logo */
		logo = new JLabel(new ImageIcon(getClass().getResource("/images/aH-150px.png")));
		logo.setBounds((gui.getWidth() / 8) * 7 - 75, gui.getHeight() / 3 - 75, 150, 150);
		add(logo);
		
		/* add header */
		lblHeader = new JLabel("LOGIN");
		lblHeader.setFont(getFont().deriveFont(Font.BOLD, 20f));
		lblHeader.setBounds((gui.getWidth() / 4) * 3, gui.getHeight() / 2, gui.getWidth() / 4, 24);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setForeground(Color.decode("0x0d8ac7"));
		add(lblHeader);
		
		/* add login button */
		btnLogin = new JButton(new ImageIcon(getClass().getResource("/images/login.png")));
		btnLogin.setBounds((gui.getWidth() / 8) * 7 - 32, (gui.getHeight() / 3) * 2 - 64, 64, 64);
		btnLogin.setOpaque(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBorderPainted(false);
		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLogin.addActionListener(e -> gui.setPanel("login"));
		add(btnLogin);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		/* Draw background image */
		bgImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/menu-img.png"));
		g.drawImage(bgImg, -50, 0, this);
		
		/* Draw a white rectangle on the right */
		g.setColor(Color.WHITE);
		g.fillRect((gui.getWidth() / 4) * 3, 0, gui.getWidth() / 4, gui.getHeight());
		
		/* Draw a blue rectangle for the copyright label */
		g.setColor(Color.decode("0x0d8ac7"));
		g.fillRect(0, gui.getHeight() - 32, gui.getWidth(), 32);
	}
}
