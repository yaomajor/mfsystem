package br.com.persistencia;

import br.com.bancodedados.Dao;
import br.com.entity.ProdutorRural;

public class ProdutorRuralDao extends Dao<ProdutorRural>{
	private static final long serialVersionUID = -4094989030654723218L;
	
	public ProdutorRuralDao() {
		super(ProdutorRural.class);
	}

}
