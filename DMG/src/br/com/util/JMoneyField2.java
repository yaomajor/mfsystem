package br.com.util;

import java.awt.Color; 
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.JTextComponent;
import javax.swing.text.NumberFormatter;

public class JMoneyField2 extends JFormattedTextField {

	public JMoneyField2() {
		DecimalFormat decimal = new DecimalFormat("#,###.00");
		NumberFormatter numFormatter = new NumberFormatter(decimal);
		numFormatter.setFormat(decimal);
		numFormatter.setAllowsInvalid(false);
		DefaultFormatterFactory dfFactory = new DefaultFormatterFactory(numFormatter);
		((JFormattedTextField) this).setFormatterFactory(dfFactory);
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
				
			}
			public void focusLost(FocusEvent e) {
				
			}
		});
	}
}
