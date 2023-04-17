package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;

import com.saulmmbp.just_another_hotel_alura.business.LoginService;
import com.saulmmbp.just_another_hotel_alura.util.Resources;

public class Login extends JPanel {

	private static final long serialVersionUID = -2974838587728175976L;

	private JPanel form;
	private JPanel header;
	private Box decoration;
	private Box fields;
	private JLabel logo;
	private JLabel lblHeader;
	private JLabel lblUser;
	private JLabel lblPassword;
	private JLabel hotelImg;
	private JTextField fldUser;
	private JPasswordField fldPassword;
	private boolean auth;

	
	public Login() {
		this.auth = false;
		
		/* Panel configs */
		addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorAdded(AncestorEvent event) {}

			@Override
			public void ancestorMoved(AncestorEvent event) {}
			
			@Override
			public void ancestorRemoved(AncestorEvent event) {
				authenticate();
			}
		});
		
		/* Initialize components */
		init();
	}
	
	/**
	 * Initialize components
	 */
	private void init() {
		/* add form panel */
		form = new JPanel(new BorderLayout());
		form.setBorder(BorderFactory.createEmptyBorder(16, 64, 16, 64));
		add(form);
		
		/* add decoration panel */
		decoration = new Box(BoxLayout.Y_AXIS);
		decoration.setOpaque(true);
		decoration.setBorder(BorderFactory.createEmptyBorder(16, 64, 16, 64));
		decoration.setBackground(new Color(12, 138, 199));
		add(decoration);
		
		/* add logo */
		logo = new JLabel(Resources.getImageIcon("/images/Ha-100px.png", this));
		logo.setBorder(BorderFactory.createEmptyBorder(0, 0, 32, 0));
		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		decoration.add(logo);
		
		/* add image */
		hotelImg = new JLabel(Resources.getImageIcon("/images/img-hotel-login-.png", this));
		hotelImg.setAlignmentX(Component.CENTER_ALIGNMENT);
		decoration.add(hotelImg);

		/* add header panel */
		header = new JPanel();
		form.add(header, BorderLayout.NORTH);
		
		/* add fields panel */
		fields = new Box(BoxLayout.Y_AXIS);
		form.add(fields, BorderLayout.CENTER);
		
		/* add header label */
		lblHeader = new JLabel("INICIAR SESIÓN");
		lblHeader.setFont(getFont().deriveFont(Font.BOLD, 32f));
		lblHeader.setForeground(new Color(12, 138, 199));
		lblHeader.setBorder(BorderFactory.createEmptyBorder(32, 0, 32, 0));
		header.add(lblHeader);
		
		/* add Usuario label */
		lblUser = new JLabel("USUARIO");
		lblUser.setForeground(Color.GRAY);
		lblUser.setFont(getFont().deriveFont(20f));
		lblUser.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblUser.setBorder(BorderFactory.createEmptyBorder(32, 0, 8, 0));
		fields.add(lblUser);
		
		/* add usuario field */
		fldUser = new JTextField();
		fldUser.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("0x0d8ac7")));
		fldUser.setAlignmentX(Component.LEFT_ALIGNMENT);
		fldUser.putClientProperty("JTextField.placeholderText", "Ingrese su nombre de usuario");
		fields.add(fldUser);
		
		/* add password label */
		lblPassword = new JLabel("CONTRASEÑA");
		lblPassword.setForeground(Color.GRAY);
		lblPassword.setFont(getFont().deriveFont(20f));
		lblPassword.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblPassword.setBorder(BorderFactory.createEmptyBorder(32, 0, 8, 0));
		fields.add(lblPassword);
		
		/* add password field */
		fldPassword = new JPasswordField();
		fldPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("0x0d8ac7")));
		fldPassword.setAlignmentX(Component.LEFT_ALIGNMENT);
		fldPassword.putClientProperty("JTextField.placeholderText", "*************************");
		fields.add(fldPassword);
	}
	
	/**
	 * Athenticate the user
	 */
	private void authenticate() {
		this.auth = LoginService.login(fldUser.getText(), new String(fldPassword.getPassword()));
	}
	
	/**
	 * Get if the user is autheticated
	 */
	public boolean isAuth() {
		return this.auth;
	}
	
	/**
	 * Set if the user is autheticated
	 */
	public void setAuth(boolean auth) {
		this.auth = auth;
	}
}
