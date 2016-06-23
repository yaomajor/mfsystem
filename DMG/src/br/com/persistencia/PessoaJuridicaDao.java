package br.com.persistencia;

import br.com.bancodedados.Dao;
import br.com.entity.PessoaJuridica;

public class PessoaJuridicaDao extends Dao<PessoaJuridica>{
	private static final long serialVersionUID = -2133401155548231477L;

	public PessoaJuridicaDao() {
		super(PessoaJuridica.class);
	}
}
