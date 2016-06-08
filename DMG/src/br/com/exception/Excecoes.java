package br.com.exception;

public class Excecoes  extends Exception {
	private static final long serialVersionUID = 1362552504616368266L;

	/** 
	 * Adicionar uma mensagem tratada que vai para o usuário
	 * @param mensagem
	 */
	public Excecoes(String mensagem) {
    	super(mensagem);
    }
	
	/** 
	 * Adicionar uma exceção que vai para o usuário
	 * @param excecao
	 */
	public Excecoes(Exception excecao) {
    	super(excecao);
    	excecao.printStackTrace();
    }
	
	/**
	 * Adicionar uma exceção que vai para o usuário com mensagem
	 * @param excecao
	 * @param mensagem
	 */
	public Excecoes(Exception excecao, String mensagem) {
    	super(mensagem, excecao);
    	excecao.printStackTrace();
    }
}