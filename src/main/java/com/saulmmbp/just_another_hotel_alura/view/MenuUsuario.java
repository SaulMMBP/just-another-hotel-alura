package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;

import com.saulmmbp.just_another_hotel_alura.business.ReservaService;
import com.saulmmbp.just_another_hotel_alura.business.dto.*;
import com.saulmmbp.just_another_hotel_alura.util.Resources;

public class MenuUsuario extends JPanel {

	private static final long serialVersionUID = -1820063700734725906L;

	private MainFrame gui;
	private JPanel menu;
	private JPanel content;
	private JPanel menuHeader;
	private JPanel header;
	private Box buttons;
	private Box boxBtnBack;
	private JLabel lblDate;
	private JLabel lblheader;
	private JLabel logo;
	private JButton btnReg;
	private JButton btnSearch;
	private JButton btnBack;
	private ReservaForm reservaForm;
	private HuespedForm huespedForm;
	private JSeparator separator;
	private JTextPane body;

	private ReservaDTO reservaDto;
	private HuespedDTO huespedDto;

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
		/* add menu panel */
		menu = new JPanel(new BorderLayout());
		menu.setOpaque(true);
		menu.setBackground(new Color(12, 138, 199));
		add(menu, BorderLayout.WEST);
		
		/* add menu header */
		menuHeader = new JPanel(new BorderLayout());
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
		
		/* add menu buttons panel */
		buttons = new Box(BoxLayout.Y_AXIS);
		buttons.setOpaque(false);
		buttons.setBorder(BorderFactory.createEmptyBorder(16, 0, 32, 0));
		menu.add(buttons, BorderLayout.CENTER);
		
		/* add button for registries view */
		btnReg = new JButton("Registro de reservas", Resources.getImageIcon("/images/reservado-white.png", this));
		btnReg.setBorderPainted(false);
		btnReg.setBackground(new Color(12, 138, 199));
		btnReg.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnReg.setForeground(Color.WHITE);
		btnReg.setHorizontalAlignment(SwingConstants.LEFT);
		btnReg.setMaximumSize(new Dimension(256, 64));
		btnReg.addActionListener(e -> {
			setData();
			if(this.reservaDto != null && this.huespedDto != null) {
				HuespedDTO data = ReservaService.registrarReserva(this.huespedDto, this.reservaDto);
				JOptionPane.showMessageDialog(this, new Detalles(data, false), "Detalles", JOptionPane.PLAIN_MESSAGE);
			}
		});
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
		content = new JPanel(new BorderLayout());
		content.setBorder(BorderFactory.createEmptyBorder(64, 64, 32, 64));
		add(content, BorderLayout.CENTER);
		
		/* add header content panel */
		header = new JPanel(new BorderLayout());
		content.add(header, BorderLayout.NORTH);
		
		/* add date label */
		lblDate = new JLabel(new SimpleDateFormat("MMM dd yyyy").format(new Date()));
		lblDate.setFont(getFont().deriveFont(12f));
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		header.add(lblDate);
		
		/* add header label */
		lblheader = new JLabel("Sistema de reservas Hotel Alura");
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

		/* add box for back button */
		boxBtnBack = new Box(BoxLayout.PAGE_AXIS);
		content.add(boxBtnBack, BorderLayout.SOUTH);
		
		/* add back button */
		btnBack = new JButton("SALIR");
		btnBack.addActionListener(e -> gui.setPanel("menuPrincipal"));
		btnBack.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boxBtnBack.add(btnBack);
	}

	/**
	 * Open a form view for set a reservation
	 */
	private void setData() {
		reservaForm = new ReservaForm();
		huespedForm = new HuespedForm();
		int optReserva = JOptionPane.showOptionDialog(this, reservaForm, "Formulario de reservación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[] {"SIGUIENTE", "CANCELAR"}, null);
		if (optReserva == JOptionPane.OK_OPTION && reservaForm.getReserva() != null) {
			this.reservaDto = reservaForm.getReserva();
			int optHuesped = JOptionPane.showOptionDialog(this, huespedForm, "Registro de huesped", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[] {"FINALIZAR", "CANCELAR"}, null);
			if(optHuesped == JOptionPane.OK_OPTION && huespedForm.getHuesped() != null) {
				this.huespedDto = huespedForm.getHuesped();
			} else if (optHuesped == JOptionPane.OK_OPTION && huespedForm.getHuesped() == null) {
				JOptionPane.showMessageDialog(this, "Ningún campo puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (optReserva == JOptionPane.OK_OPTION && reservaForm.getReserva() == null) {
			JOptionPane.showMessageDialog(this, "Ningún campo puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
