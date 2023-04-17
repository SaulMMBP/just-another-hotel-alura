package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.saulmmbp.just_another_hotel_alura.business.dto.*;
import com.saulmmbp.just_another_hotel_alura.util.Resources;

public class Detalles extends JPanel {

	private static final long serialVersionUID = 3860019692669067245L;
	private HuespedDTO huespedDto;
	private final String[] tbhdReservas = new String[] { "Número", "Fecha de entrada", "Fecha de salida", "Valor",
			"Forma de pago", "Huesped Id" };
	private JPanel header;
	private JLabel logo;
	private JPanel content;
	private JPanel pnlhuesped;
	private JLabel lblHuesped;
	private JPanel huespedData;
	private JLabel lblNombre;
	private JLabel nombre;
	private JLabel lblApellido;
	private JLabel apellido;
	private JLabel lblFechaNacimiento;
	private JLabel fechaNacimiento;
	private JLabel lblNacionalidad;
	private JLabel nacionalidad;
	private JLabel lblTelefono;
	private JLabel telefono;
	private JPanel pnlReserva;
	private JLabel lblReserva;
	private JScrollPane reservaData;
	private JTable tbReserva;
	private DefaultTableModel mdlReserva;
	
	public Detalles(HuespedDTO huespedDto) {
		this.huespedDto = huespedDto;
		
		/* configs */
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(0, 64, 0, 64));
		
		/* initialize components */
		init();
	}
	
	/**
	 * Initialize components 
	 */
	public void init() {
		/* add header panel */
		header = new JPanel();
		header.setBorder(BorderFactory.createEmptyBorder(0, 0, 32, 0));
		add(header, BorderLayout.NORTH);
		
		/* add logo */
		logo = new JLabel("DETALLES DE RESERVACIÓN", Resources.getImageIcon("/images/aH-40px.png", this), SwingConstants.CENTER);
		logo.setFont(getFont().deriveFont(Font.BOLD, 32f));
		logo.setForeground(new Color(12, 138, 199));
		logo.setIconTextGap(10);
		header.add(logo);
		
		/* add content panel */
		content = new JPanel(new BorderLayout());
		add(content, BorderLayout.CENTER);
		
		/* add huesped data panel */
		pnlhuesped = new JPanel(new BorderLayout());
		content.add(pnlhuesped, BorderLayout.NORTH);
		
		/* add huesped label */
		lblHuesped = new JLabel("Huesped", SwingConstants.LEFT);
		lblHuesped.setForeground(new Color(12, 138, 199));
		lblHuesped.setFont(getFont().deriveFont(24f));
		pnlhuesped.add(lblHuesped, BorderLayout.NORTH);
		
		/* add huesped panel */
		huespedData = new JPanel(new GridLayout(3, 4));
		huespedData.setBorder(BorderFactory.createEmptyBorder(16, 0,  32, 0));
		pnlhuesped.add(huespedData, BorderLayout.CENTER);
		
		/* add nombre data */
		lblNombre = new JLabel("Nombre: ");
		lblNombre.setFont(getFont().deriveFont(Font.BOLD));
		huespedData.add(lblNombre);
		nombre = new JLabel(huespedDto.nombre());
		huespedData.add(nombre);
		
		/* add apellido data */
		lblApellido = new JLabel("Apellido: ");
		lblApellido.setFont(getFont().deriveFont(Font.BOLD));
		huespedData.add(lblApellido);
		apellido = new JLabel(huespedDto.apellido());
		huespedData.add(apellido);
		
		/* add fechaNacimiento data */
		lblFechaNacimiento = new JLabel("FechaNacimiento: ");
		lblFechaNacimiento.setFont(getFont().deriveFont(Font.BOLD));
		huespedData.add(lblFechaNacimiento);
		fechaNacimiento = new JLabel(huespedDto.fechaNacimiento().format(DateTimeFormatter.ofPattern("d/MM/yyyy")));
		huespedData.add(fechaNacimiento);
		
		/* add nacionalidad data */
		lblNacionalidad = new JLabel("Nacionalidad: ");
		lblNacionalidad.setFont(getFont().deriveFont(Font.BOLD));
		huespedData.add(lblNacionalidad);
		nacionalidad = new JLabel(huespedDto.nacionalidad().toString());
		huespedData.add(nacionalidad);
		
		/* add telefono data */
		lblTelefono = new JLabel("Telefono: ");
		lblTelefono.setFont(getFont().deriveFont(Font.BOLD));
		huespedData.add(lblTelefono);
		telefono = new JLabel(huespedDto.telefono().toString());
		huespedData.add(telefono);
		
		/* add reservation data panel */
		pnlReserva = new JPanel(new BorderLayout());
		content.add(pnlReserva);
		
		/* add reserva label */
		lblReserva = new JLabel("Reservación", SwingConstants.LEFT);
		lblReserva.setForeground(new Color(12, 138, 199));
		lblReserva.setFont(getFont().deriveFont(24f));
		pnlReserva.add(lblReserva, BorderLayout.NORTH);
		
		/* add reserva table */
		tbReserva = new JTable();
		mdlReserva = (DefaultTableModel) tbReserva.getModel();
		fillTable(mdlReserva, huespedDto.getReservasDTO(), tbhdReservas);

		/* add scrollPane for table */
		reservaData = new JScrollPane(tbReserva);
		reservaData.setPreferredSize(new Dimension(reservaData.getWidth(), 256));
		reservaData.setBorder(BorderFactory.createEmptyBorder(16, 0,  0, 0));
		pnlReserva.add(reservaData, BorderLayout.CENTER);
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
}
