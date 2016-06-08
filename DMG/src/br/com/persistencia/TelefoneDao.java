package br.com.persistencia;

import br.com.bancodedados.Dao;
import br.com.entity.Telefone;

public class TelefoneDao extends Dao<Telefone>{
	private static final long serialVersionUID = -6605487745187271707L;

	public TelefoneDao() {
		super(Telefone.class);
	}
}
