package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;
import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

import com.saulmmbp.just_another_hotel_alura.business.dto.ReservaDTO;
import com.saulmmbp.just_another_hotel_alura.util.Resources;
import com.toedter.calendar.JDateChooser;

public class ReservaForm extends JPanel {

	private static final long serialVersionUID = 927916308390769110L;

	private Box decoration;
	private Box fields;
	private JPanel form;
	private JLabel logoImg;
	private JLabel reservaImg;
	private JLabel header;
	private JLabel lblCheckIn;
	private JLabel lblCheckOut;
	private JLabel lblValue;
	private JLabel lblPayment;
	private JDateChooser fldCheckIn;
	private JDateChooser fldCheckOut;
	private JTextField fldValue;
	private JComboBox<String> fldPayment;
	private BigDecimal totalValue;
	
	private ReservaDTO reserva;
	private final BigDecimal value = new BigDecimal("1540.00");
	private final String[] pmntMethods = new String[] { "Tarjeta de Crédito", "Tarjeta de Débito",
			"Dinero en efectivo" }; // TODO cambiar por enum

	public ReservaForm() {
		/* configs */
		addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorAdded(AncestorEvent event) {}

			@Override
			public void ancestorMoved(AncestorEvent event) {}
			
			@Override
			public void ancestorRemoved(AncestorEvent event) {
				setReserva();
			}
		});
		
		/* Initialize components */
		init();
	}

	/**
	 * Initialize components
	 */
	public void init() {
		form = new JPanel(new BorderLayout());
		form.setBorder(BorderFactory.createEmptyBorder(16, 64, 16, 64));
		add(form);
		
		/* add image panel (Decoration) */
		decoration = new Box(BoxLayout.Y_AXIS);
		decoration.setOpaque(true);
		decoration.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		decoration.setBackground(new Color(12, 138, 199));
		add(decoration);
		
		/* add logo */
		logoImg = new JLabel(Resources.getImageIcon("/images/Ha-100px.png", this));
		logoImg.setBorder(BorderFactory.createEmptyBorder(0, 0, 32, 0));
		logoImg.setAlignmentX(Component.CENTER_ALIGNMENT);
		decoration.add(logoImg);
		
		/* add img */
		reservaImg = new JLabel(Resources.getImageIcon("/images/reservas-img-3.png", this));
		reservaImg.setAlignmentX(Component.CENTER_ALIGNMENT);
		decoration.add(reservaImg);
		
		/* add header */
		header = new JLabel("SISTEMA DE RESERVAS");
		header.setForeground(new Color(12, 138, 199));
		header.setFont(getFont().deriveFont(Font.BOLD, 32f));
		header.setBorder(BorderFactory.createEmptyBorder(0, 0, 64, 0));
		form.add(header, BorderLayout.NORTH);

		/* add field panel */
		fields = new Box(BoxLayout.Y_AXIS);
		form.add(fields, BorderLayout.CENTER);
		
		/* add check in label */
		lblCheckIn = new JLabel("FECHA DE ENTRADA");
		lblCheckIn.setForeground(Color.GRAY);
		lblCheckIn.setFont(getFont().deriveFont(20f));
		lblCheckIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblCheckIn.setBorder(BorderFactory.createEmptyBorder(16, 0, 8, 0));
		fields.add(lblCheckIn);

		/* add check in field */
		fldCheckIn = new JDateChooser();
		fldCheckIn.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 138, 199)));
		fldCheckIn.getCalendarButton().setBackground(new Color(12, 138, 199));
		fldCheckIn.getCalendarButton().setIcon(Resources.getImageIcon("/images/icon-reservas.png", this));
		fldCheckIn.getCalendarButton().setBounds(312, 0, 32, 32);
		fldCheckIn.setDateFormatString("E MMM dd yyyy");
		fldCheckIn.setMinSelectableDate(new Date());
		fldCheckIn.setAlignmentX(Component.LEFT_ALIGNMENT);
		fldCheckIn.addPropertyChangeListener("date", e -> {
			fldCheckOut.setMinSelectableDate(((JDateChooser) e.getSource()).getDate());
		});
		fields.add(fldCheckIn);

		/* add check out label */
		lblCheckOut = new JLabel("FECHA DE SALIDA");
		lblCheckOut.setForeground(Color.GRAY);
		lblCheckOut.setFont(getFont().deriveFont(20f));
		lblCheckOut.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblCheckOut.setBorder(BorderFactory.createEmptyBorder(16, 0, 8, 0));
		fields.add(lblCheckOut);

		/* add check out field */
		fldCheckOut = new JDateChooser();
		fldCheckOut.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 138, 199)));
		fldCheckOut.getCalendarButton().setBackground(new Color(12, 138, 199));
		fldCheckOut.getCalendarButton().setIcon(Resources.getImageIcon("/images/icon-reservas.png", this));
		fldCheckOut.getCalendarButton().setBounds(268, 0, 21, 33);
		fldCheckOut.setDateFormatString("E MMM dd yyyy");
		fldCheckOut.setAlignmentX(Component.LEFT_ALIGNMENT);
		fldCheckOut.addPropertyChangeListener("date", e -> {
			calculateTotalValue(fldCheckIn.getDate(), fldCheckOut.getDate());
			fldValue.setText(" $" + this.totalValue.toPlainString());
		});
		fields.add(fldCheckOut);

		/* add value label */
		lblValue = new JLabel("VALUE");
		lblValue.setForeground(Color.GRAY);
		lblValue.setFont(getFont().deriveFont(20f));
		lblValue.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblValue.setBorder(BorderFactory.createEmptyBorder(16, 0, 8, 0));
		fields.add(lblValue);

		/* add value field */
		fldValue = new JTextField();
		fldValue.setEditable(false);
		fldValue.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 138, 199)));
		fldValue.setText(" $" + this.value.toPlainString());
		fldValue.setAlignmentX(Component.LEFT_ALIGNMENT);
		fields.add(fldValue);

		/* add payment method label */
		lblPayment = new JLabel("FORMA DE PAGO");
		lblPayment.setForeground(Color.GRAY);
		lblPayment.setFont(getFont().deriveFont(20f));
		lblPayment.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblPayment.setBorder(BorderFactory.createEmptyBorder(16, 0, 8, 0));
		fields.add(lblPayment);

		/* add payment method field */
		fldPayment = new JComboBox<>();
		Arrays.stream(pmntMethods).forEach(method -> fldPayment.addItem(method));
		fldPayment.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 138, 199)));
		fldPayment.setEditable(false);
		fldPayment.setEnabled(true);
		fldPayment.setAlignmentX(Component.LEFT_ALIGNMENT);
		fields.add(fldPayment);
	}	

	/**
	 * Calcule the total reservation value
	 * @param dateIn
	 * @param dateOut
	 */
	public void calculateTotalValue(Date dateIn, Date dateOut) {
		LocalDate localDateIn = LocalDate.ofInstant(dateIn.toInstant(), ZoneId.systemDefault());
		LocalDate localDateOut = LocalDate.ofInstant(dateOut.toInstant(), ZoneId.systemDefault());
		Long days = ChronoUnit.DAYS.between(localDateIn, localDateOut);
		this.totalValue = this.value.multiply(new BigDecimal(days));
	}
	
	/**
	 * Set the reservation info in ReservaDTO
	 */
	public void setReserva() {
		if (nullValidation()) {
			this.reserva = new ReservaDTO(null,
					LocalDate.ofInstant(fldCheckIn.getDate().toInstant(), ZoneId.systemDefault()),
					LocalDate.ofInstant(fldCheckOut.getDate().toInstant(), ZoneId.systemDefault()), this.totalValue,
					fldPayment.getSelectedItem().toString(), null);
		}
	}
	
	/**
	 * Return the reservaDTO instance
	 * @return
	 */
	public ReservaDTO getReserva() {
		return this.reserva;
	}
	
	/**
	 * Validate the fields to be not null
	 * @return
	 */
	private boolean nullValidation() {
		return fldCheckIn.getDate() != null && fldCheckOut.getDate() != null;
	}
}
