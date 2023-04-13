package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.saulmmbp.just_another_hotel_alura.business.dto.*;
import com.saulmmbp.just_another_hotel_alura.controller.ServiceController;

public class Busqueda extends JPanel {

	private static final long serialVersionUID = -7534314046879769085L;

	private HotelAluraGui gui;
	private Image imgLogo;
	private JLabel lblHeader;
	private JTextField fldSearch;
	private JButton btnSearch;
	private JButton btnEdit;
	private JButton btnDelete;
	private JTabbedPane tabbedPane;
	private JScrollPane scrtbReservas;
	private JScrollPane scrtbHuespedes;
	private JTable tbReservas;
	private JTable tbHuespedes;
	private DefaultTableModel mdlReservas;
	private DefaultTableModel mdlHuespedes;
	private final String[] tbhdReservas = new String[] {"Id", "Fecha de entrada", "Fecha de salida", "Valor", "Forma de pago", "Huesped Id"};
	private final String[] tbhdHuespedes = new String[] {"Id", "Nombre", "Apellido", "Fecha de nacimiento", "Nacionalidad", "telefono"};
	
	private List<HuespedDTO> huespedes;
	private List<ReservaDTO> reservas;

	public Busqueda(HotelAluraGui gui) {
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
		/* Get data */
		huespedes = ServiceController.getHuespedes();
		reservas = ServiceController.getReservas();
		
		/* add header */
		lblHeader = new JLabel("SISTEMA DE BÚSQUEDA");
		lblHeader.setForeground(new Color(12, 138, 199));
		lblHeader.setFont(gui.getFont().deriveFont(Font.BOLD, 24f));
		lblHeader.setBounds(0, 62, gui.getWidth(), 24);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblHeader);
		
		/* add search field */
		fldSearch = new JTextField();
		fldSearch.setBounds(536, 127, 256, 32);
		fldSearch.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 138, 199)));
		fldSearch.setFont(gui.getFont().deriveFont(12f));
		fldSearch.setForeground(Color.LIGHT_GRAY);
		fldSearch.setText("Ingrese busqueda");
		fldSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				JTextField fld = (JTextField) e.getComponent();
				if(fld.getText().equals("Ingrese busqueda")) {
					fld.setText("");
					fld.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				JTextField fld = (JTextField) e.getComponent();
				if(fld.getText().isBlank()) {
					fld.setText("Ingrese busqueda");
					fld.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		add(fldSearch);
		
		/* add search button */
		btnSearch = new JButton("BUSCAR");
		btnSearch.setBorderPainted(false);
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBackground(new Color(12, 138, 199));
		btnSearch.setBounds(gui.getWidth() - 160, 125, 128, 32);
		btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				huespedes = ServiceController.searchHuesped(fldSearch.getText());
				fillTable(mdlHuespedes, huespedes, tbhdHuespedes);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				JButton btn = (JButton) e.getComponent();
				btn.setBackground(new Color(118, 187, 223));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				JButton btn = (JButton) e.getComponent();
				btn.setBackground(Color.decode("0x0d8ac7"));
			}
		});
		add(btnSearch);
		
		/* add tabbed pane */
		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(gui.getFont());
		tabbedPane.setBounds(32, gui.getHeight() - 384, gui.getWidth() - 64, 288);
		tabbedPane.setBackground(new Color(12, 138, 199));
		tabbedPane.setOpaque(false);
		add(tabbedPane);
		
		/* add reservas table */
		tbReservas = new JTable();
		tbReservas.setFont(gui.getFont());
		mdlReservas = (DefaultTableModel) tbReservas.getModel();
		mdlReservas.addColumn("Id");
		mdlReservas.addColumn("Entrada");
		mdlReservas.addColumn("Salida");
		mdlReservas.addColumn("Valor");
		mdlReservas.addColumn("Forma de pago");
		mdlReservas.addColumn("Huesped id");
		fillTable(mdlReservas, reservas, tbhdReservas);
		scrtbReservas = new JScrollPane(tbReservas);
		tabbedPane.addTab("Reservas", new ImageIcon(getClass().getResource("/images/reservado.png")), scrtbReservas);
		
		
		/* add huespedes table */
		tbHuespedes = new JTable();
		tbHuespedes.setFont(gui.getFont());
		mdlHuespedes = (DefaultTableModel) tbHuespedes.getModel();
		fillTable(mdlHuespedes, huespedes, tbhdHuespedes);
		scrtbHuespedes = new JScrollPane(tbHuespedes);
		tabbedPane.addTab("Huespedes", new ImageIcon(getClass().getResource("/images/pessoas.png")), scrtbHuespedes);
		
		/* add editar button */
		btnEdit = new JButton("EDITAR");
		btnEdit.setBorderPainted(false);
		btnEdit.setBackground(new Color(12, 138, 199));
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setBounds(gui.getWidth() - 320, gui.getHeight() - 64, 128, 32);
		btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnEdit.setFont(gui.getFont().deriveFont(18f));
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Código para editar
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				JButton btn = (JButton) e.getComponent();
				btn.setBackground(new Color(118, 187, 223));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				JButton btn = (JButton) e.getComponent();
				btn.setBackground(Color.decode("0x0d8ac7"));
			}
		});
		add(btnEdit);
		
		/* add eliminar button */
		btnDelete = new JButton("ELIMINAR");
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(new Color(12, 138, 199));
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBounds(gui.getWidth() - 160, gui.getHeight() - 64, 128, 32);
		btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnDelete.setFont(gui.getFont().deriveFont(18f));
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Código para editar
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				JButton btn = (JButton) e.getComponent();
				btn.setBackground(new Color(118, 187, 223));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				JButton btn = (JButton) e.getComponent();
				btn.setBackground(Color.decode("0x0d8ac7"));
			}
		});
		add(btnDelete);
		
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
	
	
}
