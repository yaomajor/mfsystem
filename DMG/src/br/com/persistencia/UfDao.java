package br.com.persistencia;

import br.com.bancodedados.Dao;
import br.com.entity.Uf;

public class UfDao extends Dao<Uf>{
	private static final long serialVersionUID = 9000085043878672038L;

	public UfDao() {
		super(Uf.class);
	}
}
