package br.com.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import br.com.bancodedados.Conexao;
import br.com.bancodedados.Dao;
import br.com.entity.PessoaJuridica;
import br.com.entity.ProdutorRural;
import br.com.exception.Excecoes;

public class ProdutorRuralDao extends Dao<ProdutorRural>{
	private static final long serialVersionUID = -4094989030654723218L;
	
	public ProdutorRuralDao() {
		super(ProdutorRural.class);
	}

	@SuppressWarnings("unchecked")
	public List<ProdutorRural> getProdutoRural(ProdutorRural produtorRural, PessoaJuridica pessoaJuridica) throws Excecoes {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT produtor FROM ProdutorRural produtor ")
		.append(" LEFT JOIN FETCH produtor.pessoaJuridica juridica")
		.append(" LEFT JOIN FETCH juridica.pessoa pessoa")
		.append(" WHERE 1 = 1");
		
		if(StringUtils.isNotBlank(pessoaJuridica.getCnpj())) {
			sql.append(" AND juridica.cnpj = (:cnpj)");
		}
		
		if(StringUtils.isNotBlank(pessoaJuridica.getInscricaoEstadual())) {
			sql.append(" AND juridica.inscricaoEstadual in (:inscricaoEstadual)");
		}
		
		EntityManager em = Conexao.getConexaoEM();
		try {
			
			Query query = em.createQuery(sql.toString());
			
			if(StringUtils.isNotBlank(pessoaJuridica.getCnpj())) {
				query.setParameter("cnpj", pessoaJuridica.getCnpj());
			}
			
			if(StringUtils.isNotBlank(pessoaJuridica.getInscricaoEstadual())) {
				query.setParameter("inscricaoEstadual", pessoaJuridica.getInscricaoEstadual());
			}
			
			return query.getResultList();
		} catch(Exception e) {
			throw new Excecoes(e, "pesquisarErro");
		} finally {
			Conexao.fechaConexaoEM(em);
		}
	}
}
