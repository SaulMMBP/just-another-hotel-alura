package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;
import java.time.*;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.*;

import com.saulmmbp.just_another_hotel_alura.business.dto.HuespedDTO;
import com.saulmmbp.just_another_hotel_alura.model.Nacionalidad;
import com.saulmmbp.just_another_hotel_alura.util.Resources;
import com.toedter.calendar.JDateChooser;

public class HuespedForm extends JPanel {

	private static final long serialVersionUID = -1150544591024789196L;
	private Box decorations;
	private JLabel logo;
	private JLabel image;
	private JLabel header;
	private Box fields;
	private JLabel lblnombre;
	private JTextField fldnombre;
	private JLabel lblapellido;
	private JTextField fldapellido;
	private JLabel lblfechaNacimiento;
	private JDateChooser fldFechaNacimiento;
	private JLabel lblnacionalidad;
	private JComboBox<Nacionalidad> fldNacionalidad;
	private JLabel lbltelefono;
	private JTextField fldTelefono;
	
	private HuespedDTO huespedDto;

	public HuespedForm() {
		/* configs */
		addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorAdded(AncestorEvent event) {}

			@Override
			public void ancestorMoved(AncestorEvent event) {}
			
			@Override
			public void ancestorRemoved(AncestorEvent event) {
				setHuesped();
			}
		});
		
		/* initialize components */
		init();
	}

	/**
	 * Initialize components
	 */
	private void init() {
		/* add images panel */
		decorations = new Box(BoxLayout.Y_AXIS);
		decorations.setOpaque(true);
		decorations.setBackground(new Color(12, 138, 199));
		decorations.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		add(decorations);
		
		/* add logo */
		logo = new JLabel(Resources.getImageIcon("/images/Ha-100PX.png", this));
		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		decorations.add(logo);
		
		/* add image */
		image = new JLabel(Resources.getImageIcon("/images/registro.png", this));
		image.setAlignmentX(Component.CENTER_ALIGNMENT);
		decorations.add(image);
		
		/* add form panel */
		JPanel form = new JPanel(new BorderLayout());
		form.setBorder(BorderFactory.createEmptyBorder(16, 64, 16, 64));
		add(form);
		
		/* add header */
		header = new JLabel("REGISTRAR HUESPED");
		header.setFont(getFont().deriveFont(Font.BOLD, 32f));
		header.setForeground(new Color(12, 138, 199));
		form.add(header, BorderLayout.NORTH);
		
		/* add fields panel */
		fields = new Box(BoxLayout.Y_AXIS);
		form.add(fields);
		
		/* add nombre label */
		lblnombre = new JLabel("NOMBRE");
		lblnombre.setForeground(Color.GRAY);
		lblnombre.setFont(getFont().deriveFont(20f));
		lblnombre.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblnombre.setBorder(BorderFactory.createEmptyBorder(16, 0, 8, 0));
		fields.add(lblnombre);
		
		/* add nombre field */
		fldnombre = new JTextField();
		fldnombre.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 138, 199)));
		fldnombre.setAlignmentX(Component.LEFT_ALIGNMENT);
		fields.add(fldnombre);
		
		/* add apellido label */
		lblapellido = new JLabel("APELLIDO");
		lblapellido.setForeground(Color.GRAY);
		lblapellido.setFont(getFont().deriveFont(20f));
		lblapellido.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblapellido.setBorder(BorderFactory.createEmptyBorder(16, 0, 8, 0));
		fields.add(lblapellido);
		
		/* add apellido field */
		fldapellido = new JTextField();
		fldapellido.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 138, 199)));
		fldapellido.setAlignmentX(Component.LEFT_ALIGNMENT);
		fields.add(fldapellido);
		
		/* add fechaNacimiento label */
		lblfechaNacimiento = new JLabel("FECHA DE NACIMIENTO");
		lblfechaNacimiento.setForeground(Color.GRAY);
		lblfechaNacimiento.setFont(getFont().deriveFont(20f));
		lblfechaNacimiento.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblfechaNacimiento.setBorder(BorderFactory.createEmptyBorder(16, 0, 8, 0));
		fields.add(lblfechaNacimiento);
		
		/* add fechaNacimiento field */
		fldFechaNacimiento = new JDateChooser();
		fldFechaNacimiento.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 138, 199)));
		fldFechaNacimiento.getCalendarButton().setBackground(new Color(12, 138, 199));
		fldFechaNacimiento.getCalendarButton().setCursor(new Cursor(Cursor.HAND_CURSOR));
		fldFechaNacimiento.getCalendarButton().setIcon(Resources.getImageIcon("/images/icon-reservas.png", this));
		fldFechaNacimiento.setAlignmentX(Component.LEFT_ALIGNMENT);
		fields.add(fldFechaNacimiento);
		
		/* add nacionalidad label */
		lblnacionalidad = new JLabel("NACIONALIDAD");
		lblnacionalidad.setForeground(Color.GRAY);
		lblnacionalidad.setFont(getFont().deriveFont(20f));
		lblnacionalidad.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblnacionalidad.setBorder(BorderFactory.createEmptyBorder(16, 0, 8, 0));
		fields.add(lblnacionalidad);
		
		/* add nacionalidad drop down list */
		fldNacionalidad = new JComboBox<>();
		Arrays.asList(Nacionalidad.values()).forEach(country -> fldNacionalidad.addItem(country));
		fldNacionalidad.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 138, 199)));
		fldNacionalidad.setAlignmentX(Component.LEFT_ALIGNMENT);
		fields.add(fldNacionalidad);
		
		/* add telefono label */
		lbltelefono = new JLabel("TELEFONO");
		lbltelefono.setForeground(Color.GRAY);
		lbltelefono.setFont(getFont().deriveFont(20f));
		lbltelefono.setAlignmentX(Component.LEFT_ALIGNMENT);
		lbltelefono.setBorder(BorderFactory.createEmptyBorder(16, 0, 8, 0));
		fields.add(lbltelefono);
		
		/* add Telefono field */
		fldTelefono = new JTextField();
		fldTelefono.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 138, 199)));
		fldTelefono.setAlignmentX(Component.LEFT_ALIGNMENT);
		fields.add(fldTelefono);
	}
	
	/**
	 * Set a huespedDTO from fields
	 */
	public void setHuesped() {
		if(nullValidation()) {
			this.huespedDto = new HuespedDTO(null, fldnombre.getText(), fldapellido.getText(), 
					LocalDate.ofInstant(fldFechaNacimiento.getDate().toInstant(), ZoneId.systemDefault()), 
					(Nacionalidad) fldNacionalidad.getSelectedItem(),
					fldTelefono.getText(), null);
		}
	}
	
	/**
	 * Return a huespedDTO with data obtained
	 */
	public HuespedDTO getHuesped() {
		return this.huespedDto;
	}
	
	private boolean nullValidation() {
		return fldnombre.getText() != null && 
				fldapellido.getText() != null &&
				fldFechaNacimiento.getDate() != null &&
				fldTelefono.getText() != null;
	}
}
