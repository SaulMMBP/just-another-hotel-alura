package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;

import javax.swing.*;

import com.saulmmbp.just_another_hotel_alura.util.Resources;

public class MenuPrincipal extends JPanel {

	private static final long serialVersionUID = -7295880406625492439L;
	
	private MainFrame gui;
	private Box rightPanel;
	private JLabel lblCopyright;
	private JLabel lblHeader;
	private JLabel logo;
	private JLabel menuImg;
	private JButton btnLogin;
	private Login login;
	
	public MenuPrincipal(MainFrame gui) {
		this.gui = gui;
		
		/* Configs */
		setLayout(new BorderLayout());
		
		/* initialize components */
		init();
	}
	
	/**
	 * Initialize components
	 */
	private void init() {
		/* add center background label */
		menuImg = new JLabel(Resources.getImageIcon("/images/menu-img.png", this));
		add(menuImg, BorderLayout.CENTER);
		
		/* add copyright label */
		lblCopyright = new JLabel("Desarrollado por Saul Malagon Martinez © 2023");
		lblCopyright.setOpaque(true);
		lblCopyright.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));
		lblCopyright.setBackground(new Color(12, 138, 199));
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopyright.setForeground(Color.WHITE);
		lblCopyright.setFont(getFont().deriveFont(12f));
		add(lblCopyright, BorderLayout.SOUTH);
		
		/* add box */
		rightPanel = new Box(BoxLayout.Y_AXIS);
		rightPanel.setOpaque(true);
		rightPanel.setBackground(Color.WHITE);
		rightPanel.setBorder(BorderFactory.createEmptyBorder(128, 64, 32, 64));
		add(rightPanel, BorderLayout.EAST);
		
		/* add logo */
		logo = new JLabel(Resources.getImageIcon("/images/aH-150px.png", this));
		logo.setBorder(BorderFactory.createEmptyBorder(32, 0, 32, 0));
		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		rightPanel.add(logo);
		
		/* add header */
		lblHeader = new JLabel("LOGIN");
		lblHeader.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 12));
		lblHeader.setFont(getFont().deriveFont(Font.BOLD, 32f));
		lblHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblHeader.setForeground(new Color(12, 138, 199));
		rightPanel.add(lblHeader);
		
		/* add login button */
		btnLogin = new JButton(new ImageIcon(getClass().getResource("/images/login.png")));
		btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLogin.setOpaque(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBorderPainted(false);
		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLogin.addActionListener(e -> login());
		rightPanel.add(btnLogin);
	}

	/**
	 * Open the optionpane for login
	 */
	private void login() {
		login = new Login();
		int result = JOptionPane.showOptionDialog(this, login, "Login", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[] {"ENTRAR"}, null);
		if(result == JOptionPane.OK_OPTION && login.isAuth()) {
			gui.setPanel("menuUsuario");
		} else if (result == JOptionPane.OK_OPTION && !login.isAuth()){
			JOptionPane.showMessageDialog(this, "Su usuario y/o contraseña son incorrectos", 
				"Hotel Alura Message", JOptionPane.ERROR_MESSAGE);			
		}
	}
}
