package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.saulmmbp.just_another_hotel_alura.controller.ServiceController;

public class Login extends JPanel {

	private static final long serialVersionUID = -2974838587728175976L;

	private HotelAluraGui gui;
	private JLabel logo;
	private JLabel lblHeader;
	private JLabel lblUser;
	private JLabel lblPassword;
	private JTextField fldUser;
	private JPasswordField fldPassword;
	private JButton btnLogin;
	private Image hotelImg;

	public Login(HotelAluraGui gui) {
		this.gui = gui;
		
		/* Panel configs */
		setLayout(null);
		setBackground(Color.WHITE);
		
		init();
	}
	
	/**
	 * Initialize components
	 */
	private void init() {
		/* add logo */
		logo = new JLabel(new ImageIcon(getClass().getResource("/images/lOGO-50PX.png")));
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setBounds(65, 65, 50, 50);
		add(logo);
		
		/* add header */
		lblHeader = new JLabel("INICIAR SESIÓN");
		lblHeader.setFont(gui.getFont().deriveFont(26f));
		lblHeader.setBounds(65, 149, 400, 26);
		lblHeader.setForeground(Color.decode("0x0d8ac7"));
		add(lblHeader);
		
		/* add Usuario label */
		lblUser = new JLabel("USUARIO");
		lblUser.setForeground(Color.GRAY);
		lblUser.setFont(gui.getFont().deriveFont(20f));
		lblUser.setBounds(65, 219, 400, 26);
		add(lblUser);
		
		/* add usuario field */
		fldUser = new JTextField();
		fldUser.setFont(gui.getFont().deriveFont(12f));
		fldUser.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("0x0d8ac7")));
		fldUser.setForeground(Color.LIGHT_GRAY);
		fldUser.setBounds(65, 256, 324, 32);
		fldUser.setText("Ingrese su nombre de usuario");
		fldUser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				JTextField fld = (JTextField) e.getComponent();
				if(fld.getText().equals("Ingrese su nombre de usuario")) {
					fld.setText("");
					fld.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				JTextField fld = (JTextField) e.getComponent();
				if(fld.getText().isBlank()) {
					fld.setText("Ingrese su nombre de usuario");
					fld.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		add(fldUser);
		
		/* add password label */
		lblPassword = new JLabel("CONTRASEÑA");
		lblPassword.setForeground(Color.GRAY);
		lblPassword.setFont(gui.getFont().deriveFont(20f));
		lblPassword.setBounds(65, 316, 140, 26);
		add(lblPassword);
		
		/* add password field */
		fldPassword = new JPasswordField();
		fldPassword.setBounds(65, 353, 324, 32);
		fldPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("0x0d8ac7")));
		fldPassword.setFont(gui.getFont().deriveFont(12f));
		fldPassword.setText("**********");
		fldPassword.setForeground(Color.LIGHT_GRAY);
		fldPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				JPasswordField fld = (JPasswordField) e.getComponent();
				if(String.valueOf(fld.getPassword()).equals("**********")) {
					fld.setText("");
					fld.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				JPasswordField fld = (JPasswordField) e.getComponent();
				if(String.valueOf(fld.getPassword()).isBlank()) {
					fld.setText("**********");
					fld.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		fldPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == '\n') {
					login();
				}
			}
		});
		add(fldPassword);
		
		/* add button login */
		btnLogin = new JButton("ENTRAR");
		btnLogin.setFont(gui.getFont().deriveFont(Font.BOLD, 12f));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.decode("0x0d8ac7"));
		btnLogin.setBounds(65, 431, 122, 44);
		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLogin.setBorderPainted(false);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setBackground(new Color(118, 187, 223));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setBackground(Color.decode("0x0d8ac7"));
			}
		});
		btnLogin.addActionListener(event -> login());
		add(btnLogin);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		/* Draw a blue rectangle on the right */
		g.setColor(Color.decode("0x0d8ac7"));
		g.fillRect((gui.getWidth() / 3) * 2, 0, gui.getWidth() / 3, gui.getHeight());
		
		/* Draw an image on the right */
		hotelImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/img-hotel-login-.png"));
		g.drawImage(hotelImg, (gui.getWidth() / 6) * 5 - 150, 0, this);
		
	}
	
	/**
	 * Athenticate the user
	 */
	private void login() {
		boolean isAuth = ServiceController.login(fldUser.getText(), new String(fldPassword.getPassword()));
		if(isAuth) {
			gui.setPanel("menuUsuario");
		} else {
			JOptionPane.showMessageDialog(this, "Su usuario y/o contraseña son incorrectos", 
					"Hotel Alura Message", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
