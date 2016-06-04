package br.com.util;
import java.awt.Color;
import java.awt.event.FocusAdapter;   
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;

import javax.swing.JFormattedTextField;  
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;  
import javax.swing.event.CaretListener;  
import javax.swing.text.AttributeSet;  
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.JTextComponent;
import javax.swing.text.NumberFormatter;
import javax.swing.text.PlainDocument;  
import javax.swing.text.SimpleAttributeSet;  
  
/** 
* Component JMoneyField 
* @author Dyorgio da Silva Nascimento 
*/  
public class JMoneyField4zero extends JFormattedTextField {  
      
    private static final long serialVersionUID = -5712106034509737967L;  
    private static final SimpleAttributeSet nullAttribute = new SimpleAttributeSet();  
      
    /** 
     * Creates a new instance of JMoneyField 
     */  
    public JMoneyField4zero() {  
    	DecimalFormat decimal = new DecimalFormat("#,###.0000");
        NumberFormatter numFormatter = new NumberFormatter(decimal);
        numFormatter.setFormat(decimal);
        numFormatter.setAllowsInvalid(false);
        DefaultFormatterFactory dfFactory = new DefaultFormatterFactory(numFormatter);
        ((JFormattedTextField) this).setFormatterFactory(dfFactory);
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