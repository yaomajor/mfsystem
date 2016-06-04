package br.com.util;

import java.awt.Color; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

public class JTextAreaFocu extends JTextArea{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JTextAreaFocu(){
		
		this.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				selectAll();
				setBackground(new Color(219, 237, 254));			
			}
			public void focusLost(FocusEvent e) {
				setBackground(Color.WHITE);
				setText(getText().toUpperCase());
			}
			
		});
		
		
	
	}
	
}
