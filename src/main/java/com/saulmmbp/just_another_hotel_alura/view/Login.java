package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.saulmmbp.just_another_hotel_alura.controller.ServiceController;

public class Login extends JPanel {

	private static final long serialVersionUID = -2974838587728175976L;

	private MainFrame gui;
	private JLabel logo;
	private JLabel lblHeader;
	private JLabel lblUser;
	private JLabel lblPassword;
	private JTextField fldUser;
	private JPasswordField fldPassword;
	private JButton btnLogin;
	private Image hotelImg;
	
	private final Color BLUE = new Color(12, 138, 199);

	public Login(MainFrame gui) {
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
		lblHeader.setFont(getFont().deriveFont(Font.BOLD, 20f));
		lblHeader.setBounds(65, 149, 400, 26);
		lblHeader.setForeground(BLUE);
		add(lblHeader);
		
		/* add Usuario label */
		lblUser = new JLabel("USUARIO");
		lblUser.setForeground(Color.GRAY);
		lblUser.setFont(getFont().deriveFont(20f));
		lblUser.setBounds(65, 219, 400, 26);
		add(lblUser);
		
		/* add usuario field */
		fldUser = new JTextField();
		fldUser.setFont(getFont().deriveFont(12f));
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
		lblPassword.setFont(getFont().deriveFont(20f));
		lblPassword.setBounds(65, 316, 140, 26);
		add(lblPassword);
		
		/* add password field */
		fldPassword = new JPasswordField();
		fldPassword.setBounds(65, 353, 324, 32);
		fldPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("0x0d8ac7")));
		fldPassword.setFont(getFont().deriveFont(12f));
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
		btnLogin.setBackground(new Color(12, 138, 199));
		btnLogin.setBounds(65, 431, 122, 44);
		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
