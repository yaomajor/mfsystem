package br.com.util;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

public class JTextCel extends JFormattedTextField{

	public JTextCel(){
		MaskFormatter mask = new MaskFormatter();
		try {
			mask.setMask("#####-####");
		} catch (Exception e) {
		}		
		((JFormattedTextField) this).setFocusLostBehavior(JFormattedTextField.COMMIT);// Zerar o text ao perder o foco
		mask.install(this);
		this.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				setBackground(new Color(219, 237, 254));
				if (e.getSource() instanceof JTextComponent) {
					final JTextComponent textComponent = ((JTextComponent) e.getSource());					
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							// textComponent.select(0,3);
							textComponent.selectAll();
						}
					});

				}
			}
			public void focusLost(FocusEvent e) {
				setBackground(Color.WHITE);
			}
		
			
		});
		
	
	}
}

