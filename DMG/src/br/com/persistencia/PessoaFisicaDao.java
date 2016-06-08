package br.com.persistencia;

import br.com.bancodedados.Dao;
import br.com.entity.PessoaFisica;

public class PessoaFisicaDao extends Dao<PessoaFisica>{
	private static final long serialVersionUID = -2927295221673391978L;

	public PessoaFisicaDao() {
		super(PessoaFisica.class);
	}

}
