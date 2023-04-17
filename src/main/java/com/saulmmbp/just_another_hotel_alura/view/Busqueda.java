package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.saulmmbp.just_another_hotel_alura.business.BusquedaService;
import com.saulmmbp.just_another_hotel_alura.business.dto.*;
import com.saulmmbp.just_another_hotel_alura.util.Resources;

public class Busqueda extends JPanel {

	private static final long serialVersionUID = -7534314046879769085L;

	private MainFrame gui;
	private JPanel header;
	private JPanel search;
	private JPanel content;
	private JTabbedPane tabs;
	private JLabel imgLogo;
	private JLabel lblHeader;
	private JTextField fldSearch;
	private JButton btnSearch;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnDetails;
	private JScrollPane scrtbReservas;
	private JScrollPane scrtbHuespedes;
	private JTable tbReservas;
	private JTable tbHuespedes;
	private DefaultTableModel mdlReservas;
	private DefaultTableModel mdlHuespedes;
	private List<HuespedDTO> huespedes;
	private List<ReservaDTO> reservas;
	private final String[] tbhdReservas = new String[] { "Número", "Fecha de entrada", "Fecha de salida", "Valor",
			"Forma de pago", "Huesped Id" };
	private final String[] tbhdHuespedes = new String[] { "Número", "Nombre", "Apellido", "Fecha de nacimiento",
			"Nacionalidad", "telefono" };




	public Busqueda(MainFrame gui) {
		this.gui = gui;
		/* configs */
		setLayout(new BorderLayout());

		/* Initialize components */
		init();
	}

	/**
	 * Initialize components
	 */
	public void init() {
		/* get data */
		huespedes = BusquedaService.getHuespedes();
		reservas = BusquedaService.getReservas();

		header = new JPanel(new BorderLayout());
		header.setBorder(BorderFactory.createEmptyBorder(16, 16, 0, 16));
		add(header, BorderLayout.NORTH);

		/* add logo */
		imgLogo = new JLabel(Resources.getImageIcon("/images/Ha-100px.png", this));
		imgLogo.setBounds(64, 14, 100, 100);
		header.add(imgLogo);
		
		/* add header */
		lblHeader = new JLabel("SISTEMA DE BÚSQUEDA");
		lblHeader.setFont(getFont().deriveFont(Font.BOLD, 32f));
		lblHeader.setForeground(new Color(12, 138, 199));
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		header.add(lblHeader, BorderLayout.NORTH);

		search = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		search.setBorder(BorderFactory.createEmptyBorder(32, 16, 0, 16));
		header.add(search, BorderLayout.CENTER);
		
		/* add search field */
		fldSearch = new JTextField();
		fldSearch.putClientProperty("JTextField.showClearButton", true);
		fldSearch.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 138, 199)));
		fldSearch.setForeground(Color.LIGHT_GRAY);
		fldSearch.setPreferredSize(new Dimension(256, 32));
		fldSearch.setText("Ingrese busqueda");
		fldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == '\n') {
					search();
				}
			}
		});
		fldSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				JTextField fld = (JTextField) e.getComponent();
				if (fld.getText().equals("Ingrese busqueda")) {
					fld.setText("");
					fld.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				JTextField fld = (JTextField) e.getComponent();
				if (fld.getText().isBlank()) {
					fld.setText("Ingrese busqueda");
					fld.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		search.add(fldSearch);
		
		/* add search button */
		btnSearch = new JButton("BUSCAR");
		btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSearch.addActionListener(e -> search());
		search.add(btnSearch);
		
		content = new JPanel(new BorderLayout());
		content.setBorder(BorderFactory.createEmptyBorder(32, 64, 32, 64));
		add(content, BorderLayout.CENTER);

		/* add content pane */
		tabs = new JTabbedPane();
		tabs.setOpaque(false);
		tabs.setBorder(BorderFactory.createEmptyBorder());
		content.add(tabs, BorderLayout.CENTER);

		/* add reservas table */
		tbReservas = new JTable();
		mdlReservas = (DefaultTableModel) tbReservas.getModel();
		fillTable(mdlReservas, reservas, tbhdReservas);
		scrtbReservas = new JScrollPane(tbReservas);
		tabs.addTab("Reservas", new ImageIcon(getClass().getResource("/images/reservado.png")), scrtbReservas);

		/* add huespedes table */
		tbHuespedes = new JTable();
		mdlHuespedes = (DefaultTableModel) tbHuespedes.getModel();
		fillTable(mdlHuespedes, huespedes, tbhdHuespedes);
		scrtbHuespedes = new JScrollPane(tbHuespedes);
		tabs.addTab("Huespedes", new ImageIcon(getClass().getResource("/images/pessoas-black.png")), scrtbHuespedes);

		/* add buttons panel */
		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 16, 0));
		buttons.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		add(buttons, BorderLayout.SOUTH);
		
		/* add editar button */
		btnEdit = new JButton("EDITAR");
		btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnEdit.addActionListener(e -> { /* TODO implementar acción */});
		buttons.add(btnEdit);

		/* add eliminar button */
		btnDelete = new JButton("ELIMINAR");
		btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnDelete.addActionListener(e -> {/* TODO implementar acción */});
		buttons.add(btnDelete);
		
		/* add detalles button */
		btnDetails = new JButton("DETALLES");
		btnDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnDetails.addActionListener(e -> {/* TODO implementar acción */});
		buttons.add(btnDetails);
	}

	/**
	 * fill a table model with data
	 * @param model
	 * @param rows
	 * @param columns
	 */
	private void fillTable(DefaultTableModel model, List<? extends Dto> rows, String[] columns) {
		model.setDataVector(null, columns);
		rows.forEach(row -> model.addRow(row.getRow()));
		model.fireTableDataChanged();
	}
	
	/**
	 * execute a serach
	 */
	private void search() {
		String keyword = fldSearch.getText().trim();
		try {
			if(tabs.getSelectedIndex() == 1 && !fldSearch.getText().equals("Ingrese busqueda")) {
				if (keyword.chars().allMatch(Character::isDigit)) {
					Long idHuesped = Long.parseLong(keyword);
					huespedes = BusquedaService.searchHuesped(idHuesped);
				} else {
					huespedes = BusquedaService.searchHuesped(keyword);
				}
			} else if (tabs.getSelectedIndex() == 0 && !fldSearch.getText().equals("Ingrese busqueda")) {
				if (keyword.chars().allMatch(Character::isDigit)) {
					Long idReserva = Long.parseLong(keyword);
					reservas = BusquedaService.searchReserva(idReserva);
				} else if (keyword.matches("\\d{2}/\\d{2}/\\d+")) {
					LocalDateTime fechaEntrada = LocalDateTime.parse(keyword + " - 00:00:00", DateTimeFormatter.ofPattern("d/MM/yyyy - HH:mm:ss"));
					reservas = BusquedaService.searchReserva(fechaEntrada);
				}
			} else {
				huespedes = BusquedaService.getHuespedes();
				reservas = BusquedaService.getReservas();
			}
		} catch (NumberFormatException | DateTimeParseException e) {
			JOptionPane.showMessageDialog(this, "El Término ingresado es incorrecto", "Hotel Alura Error", JOptionPane.ERROR_MESSAGE);
		}
		fillTable(mdlHuespedes, huespedes, tbhdHuespedes);
		fillTable(mdlReservas, reservas, tbhdReservas);
	}

}
