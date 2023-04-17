package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;

import com.saulmmbp.just_another_hotel_alura.business.dto.ReservaDTO;
import com.saulmmbp.just_another_hotel_alura.util.Resources;

public class MenuUsuario extends JPanel {

	private static final long serialVersionUID = -1820063700734725906L;

	private MainFrame gui;
	private ReservaFormView reservaForm;
	private JSeparator separator;
	private JButton btnReg;
	private JButton btnSearch;
	private JTextPane body;
	private JLabel logo;

	private ReservaDTO reservaDto;

	public MenuUsuario(MainFrame gui) {
		this.gui = gui;
		
		/* configs */
		setLayout(new BorderLayout());

		/* Initialize components */
		init();
	}

	/**
	 * Initialize components
	 */
	private void init() {
		/* Menu panel */
		JPanel menu = new JPanel(new BorderLayout());
		menu.setOpaque(true);
		menu.setBackground(new Color(12, 138, 199));
		add(menu, BorderLayout.WEST);
		
		/* add menu header panel */
		JPanel menuHeader = new JPanel(new BorderLayout());
		menuHeader.setOpaque(false);
		menuHeader.setBorder(BorderFactory.createEmptyBorder(64, 16, 16, 16));
		menu.add(menuHeader, BorderLayout.NORTH);
		
		/* add menu logo */
		logo = new JLabel(Resources.getImageIcon("/images/aH-150px.png", this));
		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		logo.setBorder(BorderFactory.createEmptyBorder(0, 0, 32, 0));
		menuHeader.add(logo);
		
		/* add separator */
		separator = new JSeparator();
		separator.setAlignmentX(Component.CENTER_ALIGNMENT);
		menuHeader.add(separator, BorderLayout.AFTER_LAST_LINE);
		
		/* add buttons panel */
		Box buttons = new Box(BoxLayout.Y_AXIS);
		buttons.setOpaque(false);
		buttons.setBorder(BorderFactory.createEmptyBorder(16, 0, 32, 0));
		menu.add(buttons, BorderLayout.CENTER);
		
		/* add button for registries view */
		btnReg = new JButton("Registro de reservas", Resources.getImageIcon("/images/reservado-white.png", this));
		btnReg.setBorderPainted(false);
		btnReg.setBackground(new Color(12, 138, 199));
		btnReg.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnReg.addActionListener(e -> setReserva());
		btnReg.setForeground(Color.WHITE);
		btnReg.setHorizontalAlignment(SwingConstants.LEFT);
		btnReg.setMaximumSize(new Dimension(256, 64));
		buttons.add(btnReg);

		/* add button for search */
		btnSearch = new JButton("Búsqueda", Resources.getImageIcon("/images/icon-buscar.png", this));
		btnSearch.setBorderPainted(false);
		btnSearch.setBackground(new Color(12, 138, 199));
		btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSearch.addActionListener(e -> gui.setPanel("busqueda"));
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setHorizontalAlignment(SwingConstants.LEFT);
		btnSearch.setMaximumSize(new Dimension(256, 64));
		buttons.add(btnSearch);

		/* add content panel */
		JPanel content = new JPanel(new BorderLayout());
		content.setBorder(BorderFactory.createEmptyBorder(64, 64, 64, 64));
		add(content, BorderLayout.CENTER);
		
		/* add header panel */
		JPanel header = new JPanel(new BorderLayout());
		content.add(header, BorderLayout.NORTH);
		
		/* add date */
		JLabel lblDate = new JLabel(new SimpleDateFormat("MMM dd yyyy").format(new Date()));
		lblDate.setFont(getFont().deriveFont(12f));
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		header.add(lblDate);
		
		/* add header */
		JLabel lblheader = new JLabel("Sistema de reservas Hotel Alura");
		lblheader.setFont(getFont().deriveFont(Font.BOLD, 32f));
		lblheader.setBorder(BorderFactory.createEmptyBorder(0, 0, 64, 0));
		lblheader.setHorizontalAlignment(SwingConstants.CENTER);
		lblheader.setForeground(new Color(12, 138, 199));
		header.add(lblheader, BorderLayout.AFTER_LAST_LINE);
		
		/* Add body */
		body = new JTextPane();
		body.setContentType("text/html");
		body.setText("<html>" + "<body>" + "<h2>Bienvenido</h2>"
				+ "<p>Sistema de reserva de hotel. Controle y administre de forma óptima y fácil el "
				+ "flujo de reservas y de huespédes del hotel.</p>"
				+ "<p>Esta herramienta le permitirá llevar un control completo y detallado de sus "
				+ "reservas y huéspedes, tendrá acceso a heramientas especiales para tareas "
				+ "específicas como lo son:</p>" + "<ul>" + "<li>Registro de Reservas y Huéspedes</li>"
				+ "<li>Edición de Reservas y Huéspedes existentes</li>" + "<li>Eliminar todo tipo de registros</li>"
				+ "</ul>" + "</body>" + "</html>");
		content.add(body, BorderLayout.CENTER);

		/* add back button */
		Box boxBtnBack = new Box(BoxLayout.PAGE_AXIS);
		content.add(boxBtnBack, BorderLayout.SOUTH);
		
		JButton btnBack = new JButton("SALIR");
		btnBack.addActionListener(e -> gui.setPanel("menuPrincipal"));
		btnBack.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boxBtnBack.add(btnBack);
	}

	/**
	 * Open a form view for set a reservation
	 */
	private void setReserva() {
		reservaForm = new ReservaFormView();
		int resultado = JOptionPane.showOptionDialog(this, reservaForm, "Formulario de reservación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[] {"SIGUIENTE", "CANCELAR"}, null);
		if (resultado == JOptionPane.OK_OPTION) {
			this.reservaDto = reservaForm.getReserva();
		}
	}

}
