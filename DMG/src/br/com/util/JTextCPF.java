package br.com.util;

import java.awt.Color;   
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;

import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;



public class JTextCPF extends JFormattedTextField{
	
	public JTextCPF() {
		MaskFormatter mask = new MaskFormatter();
		try {
			mask.setMask("###.###.###-##");
		} catch (Exception e) {
		}		
		((JFormattedTextField) this).setFocusLostBehavior(JFormattedTextField.COMMIT);// Zerar o text ao perder o foco
		mask.install(this);
		this.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {

				if (e.getSource() instanceof JTextComponent) {
					final JTextComponent textComponent = ((JTextComponent) e.getSource());					
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							// textComponent.select(0,3);
							textComponent.selectAll();
						}
					});

				}
				setBackground(new Color(219, 237, 254));
			}
			public void focusLost(FocusEvent e) {
				setBackground(Color.WHITE);
			}
			
		});
		
	}

}
