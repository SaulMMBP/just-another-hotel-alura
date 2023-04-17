package com.saulmmbp.just_another_hotel_alura.util;

import java.awt.*;

import javax.swing.*;

public class Resources {
	
	public static Image getImage(String path, Container panel) {
		return Toolkit.getDefaultToolkit().getImage(panel.getClass().getResource(path));
	}
	
	public static ImageIcon getImageIcon(String path, Container panel) {
		return new ImageIcon(getImage(path, panel));
	}

}
