package br.com.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.*;   
public class SomenteNumeros extends PlainDocument {   
  
public static int NUMERO_DIGITOS_MAXIMO = 6;   
  
public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {   
  
  String texto = getText(0, getLength());   
  for (int i = 0; i < str.length(); i++) {   
      char c = str.charAt(i);   
      if (!Character.isDigit(c)) {   
          return;   
      }   
  }   
  
   if(texto.length() < this.NUMERO_DIGITOS_MAXIMO){   
          super.remove(0, getLength());   
      texto = texto.replace(".", "").replace(",", "");   
      StringBuffer s = new StringBuffer(texto + str);   
  
   if (s.length() > 0 && s.charAt(0) == '0') {   
       //s.deleteCharAt(0);   
   }   
  if(s.length() > 6) {   
    s.insert(s.length()-6, "");   
  }   
  
  if(s.length() > 10) {   
    s.insert(s.length()-10, "");   
  }   
  
  super.insertString(0, s.toString(), a);   
  }   
}   
  
  public void remove(int offset, int length) throws BadLocationException {   
    super.remove(offset, length);   
      String texto = getText(0, getLength());   
      texto = texto.replace(",", "");   
      texto = texto.replace(".", "");   
    super.remove(0, getLength());   
    insertString(0, texto, null);   
  }   
  
}  