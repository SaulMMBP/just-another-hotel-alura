package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

import com.saulmmbp.just_another_hotel_alura.business.dto.ReservaDTO;
import com.saulmmbp.just_another_hotel_alura.util.Resources;
import com.toedter.calendar.JDateChooser;

public class DetallesReserva extends JPanel {

	private static final long serialVersionUID = 3860019692669067245L;
	private boolean updateMode;
	private ReservaDTO reservaDto;
	private JPanel header;
	private JPanel content;
	private JPanel pnlhuesped;
	private JPanel huespedData;
	private JPanel pnlReserva;
	private JPanel reservaData;
	private JLabel logo;
	private JLabel lblHuesped;
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
	private JLabel lblReserva;
	private JLabel lblReservaId;
	private JLabel reservaId;
	private JLabel lblCheckIn;
	private JDateChooser fldCheckIn;
	private JLabel lblCheckOut;
	private JDateChooser fldCheckOut;
	private JLabel lblValue;
	private JTextField fldValue;
	private JLabel lblPayment;
	private JComboBox<String> fldPayment;
	private JLabel lblHuespedId;
	private JLabel huespedId;
	private final BigDecimal value = new BigDecimal("1540.00");
	private final String[] pmntMethods = new String[] { "Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo" };
	private BigDecimal totalValue;

	public DetallesReserva(ReservaDTO reservaDto, boolean updateMode) {
		this.reservaDto = reservaDto;
		this.updateMode = updateMode;

		/* configs */
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(0, 64, 0, 64));
		addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorAdded(AncestorEvent event) {
			}

			@Override
			public void ancestorRemoved(AncestorEvent event) {
				setReservaDto();
			}

			@Override
			public void ancestorMoved(AncestorEvent event) {
			}
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
		logo = new JLabel("DETALLES DE RESERVACIÓN", Resources.getImageIcon("/images/aH-40px.png", this),
				SwingConstants.CENTER);
		logo.setFont(getFont().deriveFont(Font.BOLD, 32f));
		logo.setForeground(new Color(12, 138, 199));
		logo.setIconTextGap(10);
		header.add(logo);

		/* add content panel */
		content = new JPanel(new BorderLayout());
		add(content, BorderLayout.CENTER);

		/* add reservation panel */
		pnlReserva = new JPanel(new BorderLayout());
		content.add(pnlReserva, BorderLayout.NORTH);

		/* add reservation label */
		lblReserva = new JLabel("Reservación", SwingConstants.LEFT);
		lblReserva.setForeground(new Color(12, 138, 199));
		lblReserva.setFont(getFont().deriveFont(24f));
		pnlReserva.add(lblReserva, BorderLayout.NORTH);

		/* add reserva data panel */
		reservaData = new JPanel(new GridLayout(3, 4, 10, 10));
		reservaData.setBorder(BorderFactory.createEmptyBorder(16, 0, 32, 0));
		pnlReserva.add(reservaData, BorderLayout.CENTER);

		/* add id data */
		lblReservaId = new JLabel("Id");
		lblReservaId.setFont(getFont().deriveFont(Font.BOLD));
		reservaData.add(lblReservaId);

		reservaId = new JLabel(reservaDto.id().toString());
		reservaData.add(reservaId);

		/* add check in data */
		lblCheckIn = new JLabel("Fecha de entrada: ");
		lblCheckIn.setFont(getFont().deriveFont(Font.BOLD));
		reservaData.add(lblCheckIn);

		fldCheckIn = new JDateChooser();
		Date checkIn = Date.from(reservaDto.fechaEntrada().atStartOfDay(ZoneId.systemDefault()).toInstant());
		fldCheckIn.setDate(checkIn);
		fldCheckIn.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 138, 199)));
		fldCheckIn.addPropertyChangeListener("date", e -> {
			fldCheckOut.setMinSelectableDate(((JDateChooser) e.getSource()).getDate());
			fldCheckOut.setDate(((JDateChooser) e.getSource()).getDate());
			calculateTotalValue(fldCheckIn.getDate(), fldCheckOut.getDate());
			fldValue.setText(" $" + this.totalValue.toPlainString());
		});
		if (!updateMode) {
			fldCheckIn.setEnabled(false);
		}
		fldCheckIn.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				JDateChooser fld = (JDateChooser) e.getComponent();
				if (fld.getDate().equals(checkIn) && updateMode) {
					fld.setDate(null);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				JDateChooser fld = (JDateChooser) e.getComponent();
				if (fld.getDate() == null) {
					fld.setDate(checkIn);
				}
			}
		});
		reservaData.add(fldCheckIn);

		/* add check out data */
		lblCheckOut = new JLabel("Fecha de salida: ");
		lblCheckOut.setFont(getFont().deriveFont(Font.BOLD));
		reservaData.add(lblCheckOut);

		fldCheckOut = new JDateChooser();
		Date checkout = Date.from(reservaDto.fechaSalida().atStartOfDay(ZoneId.systemDefault()).toInstant());
		fldCheckOut.setDate(checkout);
		fldCheckOut.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 138, 199)));
		fldCheckOut.addPropertyChangeListener("date", e -> {
			calculateTotalValue(fldCheckIn.getDate(), fldCheckOut.getDate());
			fldValue.setText(" $" + this.totalValue.toPlainString());
		});
		if (!updateMode) {
			fldCheckOut.setEnabled(false);
		}
		fldCheckOut.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				JDateChooser fld = (JDateChooser) e.getComponent();
				if (fld.getDate().equals(checkout) && updateMode) {
					fld.setDate(null);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				JDateChooser fld = (JDateChooser) e.getComponent();
				if (fld.getDate() == null) {
					fld.setDate(checkout);
				}
			}
		});
		reservaData.add(fldCheckOut);

		/* add value data */
		lblValue = new JLabel("Valor: ");
		lblValue.setFont(getFont().deriveFont(Font.BOLD));
		reservaData.add(lblValue);

		fldValue = new JTextField();
		fldValue.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 138, 199)));
		fldValue.setEditable(false);

		fldValue.setText("$" + reservaDto.valor().toPlainString());
		reservaData.add(fldValue);

		/* add payment method data */
		lblPayment = new JLabel("Forma de pago: ");
		lblPayment.setFont(getFont().deriveFont(Font.BOLD));
		reservaData.add(lblPayment);

		fldPayment = new JComboBox<>(pmntMethods);
		fldPayment.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 138, 199)));
		fldPayment.setSelectedIndex(Arrays.asList(pmntMethods).indexOf(reservaDto.formaPago()));
		fldPayment.setEditable(false);
		if (!updateMode) {
			fldPayment.setEnabled(false);
		}
		reservaData.add(fldPayment);

		/* add huesped panel */
		pnlhuesped = new JPanel(new BorderLayout());
		content.add(pnlhuesped, BorderLayout.CENTER);

		/* add huesped label */
		lblHuesped = new JLabel("Huesped", SwingConstants.LEFT);
		lblHuesped.setForeground(new Color(12, 138, 199));
		lblHuesped.setFont(getFont().deriveFont(24f));
		pnlhuesped.add(lblHuesped, BorderLayout.NORTH);

		/* add huesped data panel */
		huespedData = new JPanel(new GridLayout(3, 4, 10, 10));
		pnlhuesped.add(huespedData, BorderLayout.CENTER);

		/* add id data */
		lblHuespedId = new JLabel("Id: ");
		lblHuespedId.setFont(getFont().deriveFont(Font.BOLD));
		huespedData.add(lblHuespedId);

		huespedId = new JLabel(reservaDto.huesped().getId().toString());
		huespedData.add(huespedId);

		/* add nombre data */
		lblNombre = new JLabel("Nombre: ");
		lblNombre.setFont(getFont().deriveFont(Font.BOLD));
		huespedData.add(lblNombre);

		nombre = new JLabel(reservaDto.huesped().getNombre());
		huespedData.add(nombre);

		/* add apellido data */
		lblApellido = new JLabel("Apellido: ");
		lblApellido.setFont(getFont().deriveFont(Font.BOLD));
		huespedData.add(lblApellido);

		apellido = new JLabel(reservaDto.huesped().getApellido());
		huespedData.add(apellido);

		/* add fechaNacimiento data */
		lblFechaNacimiento = new JLabel("Fecha de nacimiento: ");
		lblFechaNacimiento.setFont(getFont().deriveFont(Font.BOLD));
		huespedData.add(lblFechaNacimiento);

		fechaNacimiento = new JLabel(
				reservaDto.huesped().getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		huespedData.add(fechaNacimiento);

		/* add nacionalidad data */
		lblNacionalidad = new JLabel("Nacionalidad: ");
		lblNacionalidad.setFont(getFont().deriveFont(Font.BOLD));
		huespedData.add(lblNacionalidad);

		nacionalidad = new JLabel(reservaDto.huesped().getNacionalidad().toString());
		huespedData.add(nacionalidad);

		/* add telefono data */
		lblTelefono = new JLabel("Telefono: ");
		lblTelefono.setFont(getFont().deriveFont(Font.BOLD));
		huespedData.add(lblTelefono);

		telefono = new JLabel(reservaDto.huesped().getTelefono().toString());
		huespedData.add(telefono);

	}

	private void setReservaDto() {
		ReservaDTO editedReserva = new ReservaDTO(reservaDto.id(),
				LocalDate.ofInstant(fldCheckIn.getDate().toInstant(), ZoneId.systemDefault()), 
				LocalDate.ofInstant(fldCheckOut.getDate().toInstant(), ZoneId.systemDefault()), 
				this.totalValue, fldPayment.getSelectedItem().toString(), this.reservaDto.huesped());
		
		if(!this.reservaDto.equals(editedReserva)) {
			this.reservaDto = editedReserva;
		} else {
			this.reservaDto = null;
		}
	}

	public ReservaDTO getReservaDto() {
		return this.reservaDto;
	}

	/**
	 * Calcule the total reservation value
	 * 
	 * @param dateIn
	 * @param dateOut
	 */
	public void calculateTotalValue(Date dateIn, Date dateOut) {
		LocalDate localDateIn = LocalDate.ofInstant(dateIn.toInstant(), ZoneId.systemDefault());
		LocalDate localDateOut = LocalDate.ofInstant(dateOut.toInstant(), ZoneId.systemDefault());
		Long days = ChronoUnit.DAYS.between(localDateIn, localDateOut);
		this.totalValue = this.value.multiply(new BigDecimal(days));
	}
}
