package com.saulmmbp.just_another_hotel_alura.view;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.text.*;

public class MenuUsuario extends JPanel {

	private static final long serialVersionUID = -1820063700734725906L;

	private HotelAluraGui gui;
	private JSeparator separator;
	private JButton btnReg;
	private JButton btnSearch;
	private JTextPane txtHeader;
	private JTextPane txtBody;
	private Image logoImg;

	public MenuUsuario(HotelAluraGui gui) {
		this.gui = gui;
		
		/* Panel configs */
		setBackground(Color.WHITE);
		setLayout(null);
	
		init();
	}

	/**
	 * Initialize components
	 */
	private void init() {
		/* add separator */
		separator = new JSeparator();
		separator.setBounds(26, 219, 201, 2);
		add(separator);
		
		/* add button for registries view */
		btnReg = new JButton();
		btnReg.setText("Registro de reservas");
		btnReg.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass()
				.getResource("/images/reservado.png"))));
		btnReg.setIconTextGap(10);
		btnReg.setOpaque(false);
		btnReg.setContentAreaFilled(false);
		btnReg.setBorderPainted(false);
		btnReg.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnReg.setForeground(Color.WHITE);
		btnReg.setFont(getFont().deriveFont(18f));
		btnReg.setBounds(0, 255, 257, 56);
		btnReg.setHorizontalAlignment(SwingConstants.LEFT);
		btnReg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				JButton btn = (JButton) e.getComponent();
				btn.setOpaque(true);
				btn.setContentAreaFilled(true);
				btn.setBackground(new Color(118, 187, 223));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				JButton btn = (JButton) e.getComponent();
				btn.setOpaque(false);
				btn.setContentAreaFilled(false);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO ir a Reservas View
			}
		});
		add(btnReg);
		
		/* add button for search */
		btnSearch = new JButton();
		btnSearch.setText("Búsqueda");
		btnSearch.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass()
				.getResource("/images/pessoas.png"))));
		btnSearch.setIconTextGap(10);
		btnSearch.setOpaque(false);
		btnSearch.setContentAreaFilled(false);
		btnSearch.setBorderPainted(false);
		btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(getFont().deriveFont(18f));
		btnSearch.setBounds(0, 312, 257, 56);
		btnSearch.setHorizontalAlignment(SwingConstants.LEFT);
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				JButton btn = (JButton) e.getComponent();
				btn.setOpaque(true);
				btn.setContentAreaFilled(true);
				btn.setBackground(new Color(118, 187, 223));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				JButton btn = (JButton) e.getComponent();
				btn.setOpaque(false);
				btn.setContentAreaFilled(false);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO ir a Search View
			}
		});
		add(btnSearch);
		
		/* Add header */
		txtHeader = new JTextPane();
		txtHeader.setOpaque(false);
		txtHeader.setFont(getFont().deriveFont(24f));
		txtHeader.setForeground(Color.WHITE);
		txtHeader.setBounds(257, 84, gui.getWidth() - 257, 121);
		txtHeader.setContentType("text/html");
		txtHeader.setText("<html>"
				+ "<body style='text-align: center; color: white'>"
				+ "<h1>Sistema de reservas Hotel Alura</h1>"
				+ "<h2>" + new SimpleDateFormat("MMM dd yyyy").format(new Date()) + "</h2>"
				+ "</body>"
				+ "</html>");
		add(txtHeader);
		
		/* Add body */
		txtBody = new JTextPane();
		txtBody.setBounds(302, 234, gui.getWidth() - 334, gui.getHeight() - 266);
		txtBody.setContentType("text/html");
		txtBody.setText("<html>"
				+ "<body>"
				+ "<h2>Bienvenido</h2>"
				+ "<p>Sistema de reserva de hotel. Controle y administre de forma óptima y fácil el "
				+ "flujo de reservas y de huespédes del hotel.</p>"
				+ "<p>Esta herramienta le permitirá llevar un control completo y detallado de sus "
				+ "reservas y huéspedes, tendrá acceso a heramientas especiales para tareas "
				+ "específicas como lo son:</p>"
				+ "<ul>"
				+ "<li>Registro de Reservas y Huéspedes</li>"
				+ "<li>Edición de Reservas y Huéspedes existentes</li>"
				+ "<li>Eliminar todo tipo de registros</li>"
				+ "</ul>"
				+ "</body>"
				+ "</html>");
		add(txtBody);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		/* Draw light blue rectangle for the header background*/
		g.setColor(new Color(118, 187, 223));
		g.fillRect(0, 84, gui.getWidth(), 121);
		
		/* Draw blue rectangle for the menu background */
		g.setColor(new Color(12, 138, 199));
		g.fillRect(0, 0, 257, gui.getHeight());
		
		/* Draw logo */
		logoImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/aH-150px.png"));
		g.drawImage(logoImg, 50, 58, this);
	}
	
}
