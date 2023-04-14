package com.saulmmbp.just_another_hotel_alura;

import java.awt.EventQueue;

import javax.swing.*;

import com.formdev.flatlaf.*;
import com.saulmmbp.just_another_hotel_alura.view.MainFrame;

public class App {
	public static void main(String[] args) {
		/* Config Look and Feel */
		try {
			FlatLaf.registerCustomDefaultsSource( "com.saulmmbp.just_another_hotel_alura.themes" );
			FlatLightLaf.setup();
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(null,
					"No fue posible cargar el Tema, se utilizará el tema predeterminado del sistema");
		}
		EventQueue.invokeLater(MainFrame::new); // Launch
	}
}
