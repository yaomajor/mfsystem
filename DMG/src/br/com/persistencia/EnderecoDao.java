package br.com.persistencia;

import br.com.bancodedados.Dao;
import br.com.entity.Endereco;

public class EnderecoDao extends Dao<Endereco>{
	private static final long serialVersionUID = 4196826047023077873L;
	
	public EnderecoDao() {
		super(Endereco.class);
	}
}
