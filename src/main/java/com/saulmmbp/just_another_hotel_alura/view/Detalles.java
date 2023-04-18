package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import com.saulmmbp.just_another_hotel_alura.business.dto.*;
import com.saulmmbp.just_another_hotel_alura.model.Nacionalidad;
import com.saulmmbp.just_another_hotel_alura.util.Resources;
import com.toedter.calendar.JDateChooser;

public class Detalles extends JPanel {

	private static final long serialVersionUID = 3860019692669067245L;
	private boolean updateMode;
	private HuespedDTO huespedDto;
	private final String[] tbhdReservas = new String[] { "Número", "Fecha de entrada", "Fecha de salida", "Valor",
			"Forma de pago", "Huesped Id" };
	private JPanel header;
	private JPanel content;
	private JPanel pnlhuesped;
	private JPanel huespedData;
	private JPanel pnlReserva;
	private JLabel logo;
	private JLabel lblHuesped;
	private JLabel lblNombre;
	private JTextField fldnombre;
	private JLabel lblApellido;
	private JTextField fldapellido;
	private JLabel lblFechaNacimiento;
	private JDateChooser fldfechaNacimiento;
	private JLabel lblNacionalidad;
	private JComboBox<Nacionalidad> fldnacionalidad;
	private JLabel lblTelefono;
	private JTextField fldtelefono;
	private JLabel lblReserva;
	private JScrollPane reservaData;
	private JTable tbReserva;
	private DefaultTableModel mdlReserva;
	
	public Detalles(HuespedDTO huespedDto, boolean updateMode) {
		this.huespedDto = huespedDto;
		this.updateMode = updateMode;
		
		/* configs */
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(0, 64, 0, 64));
		addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorAdded(AncestorEvent event) {}

			@Override
			public void ancestorRemoved(AncestorEvent event) {
				setHuespedDto();
			}

			@Override
			public void ancestorMoved(AncestorEvent event) {}
		});
		
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
		huespedData = new JPanel(new GridLayout(3, 4, 10, 10));
		huespedData.setBorder(BorderFactory.createEmptyBorder(16, 0,  32, 0));
		pnlhuesped.add(huespedData, BorderLayout.CENTER);
		
		/* add nombre data */
		lblNombre = new JLabel("Nombre: ");
		lblNombre.setFont(getFont().deriveFont(Font.BOLD));
		huespedData.add(lblNombre);
		fldnombre = new JTextField(getHuespedDto().nombre());
		fldnombre.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 138, 199)));
		if(!this.updateMode) {
			fldnombre.setEditable(false);
		}
		fldnombre.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				JTextField fld = (JTextField) e.getComponent();
				if(fld.getText().equals(getHuespedDto().nombre()) && updateMode) {
					fld.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				JTextField fld = (JTextField) e.getComponent();
				if(fld.getText().isBlank()) {
					fld.setText(getHuespedDto().nombre());
				}
			}
		});
		huespedData.add(fldnombre);
		
		/* add apellido data */
		lblApellido = new JLabel("Apellido: ");
		lblApellido.setFont(getFont().deriveFont(Font.BOLD));
		huespedData.add(lblApellido);
		fldapellido = new JTextField(getHuespedDto().apellido());
		fldapellido.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 138, 199)));
		if(!this.updateMode) {
			fldapellido.setEditable(false);
		}
		fldapellido.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				JTextField fld = (JTextField) e.getComponent();
				if(fld.getText().equals(getHuespedDto().apellido()) && updateMode) {
					fld.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				JTextField fld = (JTextField) e.getComponent();
				if(fld.getText().isBlank()) {
					fld.setText(getHuespedDto().apellido());
				}
			}
		});
		huespedData.add(fldapellido);
		
		/* add fechaNacimiento data */
		lblFechaNacimiento = new JLabel("Fecha de nacimiento: ");
		lblFechaNacimiento.setFont(getFont().deriveFont(Font.BOLD));
		huespedData.add(lblFechaNacimiento);
		fldfechaNacimiento = new JDateChooser();
		Date fechaNacimiento = Date.from(getHuespedDto().fechaNacimiento().atStartOfDay(ZoneId.systemDefault()).toInstant());
		fldfechaNacimiento.setDate(fechaNacimiento);
		fldfechaNacimiento.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 138, 199)));
		if(!this.updateMode) {
			fldfechaNacimiento.setEnabled(false);
		}
		fldfechaNacimiento.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				JDateChooser fld = (JDateChooser) e.getComponent();
				if(fld.getDate().equals(fechaNacimiento) && updateMode) {
					fld.setDate(null);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				JDateChooser fld = (JDateChooser) e.getComponent();
				if(fld.getDate() == null) {
					fld.setDate(fechaNacimiento);
				}
			}
		});
		huespedData.add(fldfechaNacimiento);
		
		/* add nacionalidad data */
		lblNacionalidad = new JLabel("Nacionalidad: ");
		lblNacionalidad.setFont(getFont().deriveFont(Font.BOLD));
		huespedData.add(lblNacionalidad);
		fldnacionalidad = new JComboBox<>();
		Arrays.asList(Nacionalidad.values()).forEach(country -> fldnacionalidad.addItem(country));
		fldnacionalidad.setSelectedIndex(Arrays.asList(Nacionalidad.values()).indexOf(getHuespedDto().nacionalidad()));
		fldnacionalidad.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 138, 199)));
		fldnacionalidad.setEditable(false);
		if(!this.updateMode) {
			fldnacionalidad.setEnabled(false);
		}
		huespedData.add(fldnacionalidad);
		
		/* add telefono data */
		lblTelefono = new JLabel("Telefono: ");
		lblTelefono.setFont(getFont().deriveFont(Font.BOLD));
		huespedData.add(lblTelefono);
		fldtelefono = new JTextField(getHuespedDto().telefono().toString());
		fldtelefono.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 138, 199)));
		if(!this.updateMode) {
			fldtelefono.setEditable(false);
		}
		fldtelefono.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				JTextField fld = (JTextField) e.getComponent();
				if(fld.getText().equals(getHuespedDto().telefono()) && updateMode) {
					fld.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				JTextField fld = (JTextField) e.getComponent();
				if(fld.getText().isBlank()) {
					fld.setText(getHuespedDto().telefono());
				}
			}
		});
		huespedData.add(fldtelefono);
		
		/* add reservation data panel */
		pnlReserva = new JPanel(new BorderLayout());
		content.add(pnlReserva);
		
		/* add reserva label */
		lblReserva = new JLabel("Reservaciones", SwingConstants.LEFT);
		lblReserva.setForeground(new Color(12, 138, 199));
		lblReserva.setFont(getFont().deriveFont(24f));
		pnlReserva.add(lblReserva, BorderLayout.NORTH);
		
		/* add reserva table */
		tbReserva = new JTable();
		tbReserva.setDefaultEditor(Object.class, null);
		mdlReserva = (DefaultTableModel) tbReserva.getModel();
		fillTable(mdlReserva, getHuespedDto().getReservasDTO(), tbhdReservas);

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

	private void setHuespedDto() {
		HuespedDTO editedHuesped = new HuespedDTO(getHuespedDto().id(), fldnombre.getText(), fldapellido.getText(), 
				LocalDate.ofInstant(fldfechaNacimiento.getDate().toInstant(), ZoneId.systemDefault()), 
				(Nacionalidad) fldnacionalidad.getSelectedItem(), fldtelefono.getText(), null);
		
		if(!this.huespedDto.equals(editedHuesped)) {
			this.huespedDto = editedHuesped;
		} else {
			this.huespedDto = null;
		}
	}

	public HuespedDTO getHuespedDto() {
		return huespedDto;
	}
}
