package br.com.util;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;

public class JMoneyTextField extends JFormattedTextField {
	JTextField txtJT = new JTextField();
	public JMoneyTextField() {

		setDocument(new NumberDocument());
		setBounds(16, 24, 185, 25);
		
	}
	boolean ja = false;
	public class NumberDocument extends PlainDocument {

		public NumberDocument() {
			super();

		}

		public void insertString(int offset, String str, AttributeSet attr)
				throws BadLocationException {
			// permite apenas uma virgula
			
			if (str.equals(",")) {
				
				if (ja==true){
					
					return;
				}
				else{
					ja=true;
					super.insertString(offset, str, attr);
				}
			}
			if (str.codePointAt(0) < 48 || str.codePointAt(0) > 57)
				return;
			super.insertString(offset, str, attr);
		}
	}
}