package br.com.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.bancodedados.Conexao;
import br.com.bancodedados.Dao;
import br.com.entity.Cidades;
import br.com.exception.Excecoes;

public class CidadesDao extends Dao<Cidades>{
	private static final long serialVersionUID = -2132491871176771305L;

	public CidadesDao() {
		super(Cidades.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cidades> getListaCidadePorUf(String uf) throws Excecoes {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cidade FROM Cidades cidade ")
		.append(" LEFT JOIN FETCH cidade.uf as uf")
		.append(" WHERE LOWER(uf.sigla) = LOWER(:uf) ");

		EntityManager em = Conexao.getConexaoEM();
		try {
			
			Query query = em.createQuery(sql.toString());
			query.setParameter("uf", uf);
			
		return query.getResultList();
		} catch(Exception e) {
			throw new Excecoes(e, "pesquisarErro");
		} finally {
			Conexao.fechaConexaoEM(em);
		}
	}
}
