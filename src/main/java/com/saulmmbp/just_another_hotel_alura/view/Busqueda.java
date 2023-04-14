package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.saulmmbp.just_another_hotel_alura.business.BusquedaService;
import com.saulmmbp.just_another_hotel_alura.business.dto.*;

public class Busqueda extends JPanel {

	private static final long serialVersionUID = -7534314046879769085L;

	private MainFrame gui;
	private Image imgLogo;
	private JLabel lblHeader;
	private JTextField fldSearch;
	private JButton btnSearch;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnDetails;
	private JTabbedPane tabbedPane;
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
		/* panel configs */
		setBackground(Color.WHITE);
		setLayout(null);

		init();
	}

	/**
	 * Initialize components
	 */
	public void init() {
		huespedes = BusquedaService.getHuespedes();
		reservas = BusquedaService.getReservas();

		/* add header */
		lblHeader = new JLabel("SISTEMA DE BÚSQUEDA");
		lblHeader.setFont(getFont().deriveFont(Font.BOLD, 20f));
		lblHeader.setForeground(new Color(12, 138, 199));
		lblHeader.setBounds(0, 62, gui.getWidth(), 24);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblHeader);

		/* add search field */
		fldSearch = new JTextField();
		fldSearch.setFont(getFont().deriveFont(12f));
		fldSearch.setBounds(536, 127, 256, 32);
		fldSearch.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 138, 199)));
		fldSearch.setForeground(Color.LIGHT_GRAY);
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
		add(fldSearch);

		/* add search button */
		btnSearch = new JButton("BUSCAR");
		btnSearch.setBackground(new Color(12, 138, 199));
		btnSearch.setBounds(gui.getWidth() - 160, 125, 128, 32);
		btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSearch.addActionListener(e -> {
			search();
		});
		add(btnSearch);

		/* add tabbed pane */
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(32, gui.getHeight() - 384, gui.getWidth() - 64, 288);
		tabbedPane.setOpaque(false);
		add(tabbedPane);

		/* add reservas table */
		tbReservas = new JTable();
		mdlReservas = (DefaultTableModel) tbReservas.getModel();
		fillTable(mdlReservas, reservas, tbhdReservas);
		scrtbReservas = new JScrollPane(tbReservas);
		tabbedPane.addTab("Reservas", new ImageIcon(getClass().getResource("/images/reservado.png")), scrtbReservas);

		/* add huespedes table */
		tbHuespedes = new JTable();
		mdlHuespedes = (DefaultTableModel) tbHuespedes.getModel();
		fillTable(mdlHuespedes, huespedes, tbhdHuespedes);
		scrtbHuespedes = new JScrollPane(tbHuespedes);
		tabbedPane.addTab("Huespedes", new ImageIcon(getClass().getResource("/images/pessoas-black.png")), scrtbHuespedes);

		/* add editar button */
		btnEdit = new JButton("EDITAR");
		btnEdit.setBackground(new Color(12, 138, 199));
		btnEdit.setBounds(gui.getWidth() - 320, gui.getHeight() - 64, 128, 32);
		btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnEdit.addActionListener(e -> { /* TODO implementar acción */});
		add(btnEdit);

		/* add eliminar button */
		btnDelete = new JButton("ELIMINAR");
		btnDelete.setBackground(new Color(12, 138, 199));
		btnDelete.setBounds(gui.getWidth() - 160, gui.getHeight() - 64, 128, 32);
		btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnDelete.addActionListener(e -> {/* TODO implementar acción */});
		add(btnDelete);
		
		/* add detalles button */
		btnDetails = new JButton("DETALLES");
		btnDetails.setBackground(new Color(12, 138, 199));
		btnDetails.setBounds(gui.getWidth() - 480, gui.getHeight() - 64, 128, 32);
		btnDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnDetails.addActionListener(e -> {/* TODO implementar acción */});
		add(btnDetails);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		imgLogo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/Ha-100px.png"));
		g.drawImage(imgLogo, 64, 32, this);

	}

	private void fillTable(DefaultTableModel model, List<? extends Dto> rows, String[] columns) {
		model.setDataVector(null, columns);
		rows.forEach(row -> model.addRow(row.getRow()));
		model.fireTableDataChanged();
	}
	
	private void search() {
		String keyword = fldSearch.getText().trim();
		if(tabbedPane.getSelectedIndex() == 1 && !fldSearch.getText().equals("Ingrese busqueda")) {
			huespedes = BusquedaService.searchHuesped(keyword);
		} else if (tabbedPane.getSelectedIndex() == 0 && !fldSearch.getText().equals("Ingrese busqueda")) {
			reservas = BusquedaService.searchReserva(keyword);
		} else {
			huespedes = BusquedaService.getHuespedes();
			reservas = BusquedaService.getReservas();
		}
		fillTable(mdlHuespedes, huespedes, tbhdHuespedes);
		fillTable(mdlReservas, reservas, tbhdReservas);
	}

}
