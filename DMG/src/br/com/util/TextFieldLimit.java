package br.com.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TextFieldLimit extends PlainDocument{
	private static final long serialVersionUID = -6003157121081942771L;
	
	private int limit;
    private boolean numeros;

    /**
     * Construtor padrao.
     *
     * @param limit o tamanho maximo em caracteres.
     */
    public TextFieldLimit(int limit) {
        this(limit, false);
    }

    /**
     * Construtor padrao.
     *
     * @param limit o tamanho maximo em caracteres.
     * @param numeros informa se permite somente numeros.
     */
    public TextFieldLimit(int limit, boolean numeros) {
        super();
        this.limit = limit;
        this.numeros = numeros;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        if(numeros){
            str = str.replaceAll("\\D", "");
        }
        
        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}
