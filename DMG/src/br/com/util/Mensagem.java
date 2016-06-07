package br.com.util;

import javax.swing.JOptionPane;

public class Mensagem {

	/**
	 * M�todo utilizado para chamar o getMensagemProperties sobreposto
	 * @param textoChave - chave a ser buscada no ApplicationResources
	 * @return Mensagem encontrada no ApplicationResources
	 */
	public static String getMensagemProperties(String textoChave){
            String mensagem = java.util.ResourceBundle.getBundle("br.com.calc.gui.Bundle").getString(textoChave);
            return mensagem;
	}

        /**
	 * M�todo respons�vel por definir o n�vel cr�tico da mensagem como fatal
	 * @param textoChave - chave a ser buscada no ApplicationResources
	 */
	public static void erro(String textoChave) {
		//String mensagem = getMensagemProperties(textoChave);
        JOptionPane.showMessageDialog(null, textoChave, getMensagemProperties("erro"), JOptionPane.ERROR_MESSAGE);
	}
	
	public static void erroChave(String textoChave) {
		String mensagem = getMensagemProperties(textoChave);
        JOptionPane.showMessageDialog(null, mensagem, getMensagemProperties("erro"), JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * M�todo respons�vel por definir o n�vel cr�tico da mensagem como aviso
	 * @param textoChave - chave a ser buscada no ApplicationResources
	 */
	public static void aviso(String textoChave) {
		//String mensagem = getMensagemProperties(textoChave);
        JOptionPane.showMessageDialog(null, textoChave, getMensagemProperties("aviso"), JOptionPane.WARNING_MESSAGE);
	}
	
        /**
	 * M�todo respons�vel por definir o n�vel cr�tico da mensagem como informa��o
	 * @param textoChave - chave a ser buscada no ApplicationResources
	 */
	public static void informacao(String textoChave) {
		//String mensagem = getMensagemProperties(textoChave);
		JOptionPane.showMessageDialog(null, textoChave, getMensagemProperties("informacao"), JOptionPane.INFORMATION_MESSAGE);
	}
}
