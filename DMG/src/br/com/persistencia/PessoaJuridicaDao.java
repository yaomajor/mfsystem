package br.com.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.bancodedados.Conexao;
import br.com.bancodedados.Dao;
import br.com.entity.PessoaJuridica;
import br.com.exception.Excecoes;

public class PessoaJuridicaDao extends Dao<PessoaJuridica>{
	private static final long serialVersionUID = -2133401155548231477L;

	public PessoaJuridicaDao() {
		super(PessoaJuridica.class);
	}
	
	public PessoaJuridica getPessoaJuridica(PessoaJuridica pessoaJuridica) throws Excecoes {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT juridica FROM PessoaJuridica juridica ")
		.append(" WHERE juridica.cnpj = (:cnpj)");
		
		EntityManager em = Conexao.getConexaoEM();
		try {
			
			Query query = em.createQuery(sql.toString());
			query.setParameter("cnpj", pessoaJuridica.getCnpj());
			
			return (PessoaJuridica) query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		} catch(Exception e) {
			throw new Excecoes(e, "pesquisarErro");
		} finally {
			Conexao.fechaConexaoEM(em);
		}
	}
}
