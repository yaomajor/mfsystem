package br.com.util;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JSpinner;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

public class JSpinnerMHM extends JSpinner {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JSpinnerMHM() {
		((JSpinner.DefaultEditor) this.getEditor()).getTextField().addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				
				if (e.getSource() instanceof JTextComponent) {
					JTextComponent textComponent = ((JTextComponent) e.getSource());
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							// textComponent.select(0,3);
							textComponent.selectAll();
						}
					});

				}
			}
		});
		 
		
	}

}
