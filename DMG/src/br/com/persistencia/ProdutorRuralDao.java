package br.com.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import br.com.bancodedados.Conexao;
import br.com.bancodedados.Dao;
import br.com.entity.PessoaJuridica;
import br.com.entity.ProdutorRural;
import br.com.exception.Excecoes;

public class ProdutorRuralDao extends Dao<ProdutorRural>{
	private static final long serialVersionUID = -4094989030654723218L;
	
	private PessoaDao pessoaDao = new PessoaDao();
	
	public ProdutorRuralDao() {
		super(ProdutorRural.class);
	}

	@SuppressWarnings("unchecked")
	public List<ProdutorRural> getListaProdutoRural(ProdutorRural produtorRural, PessoaJuridica pessoaJuridica) throws Excecoes {
		List<ProdutorRural> retorno = new ArrayList<ProdutorRural>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT produtor FROM ProdutorRural produtor ")
		.append(" LEFT JOIN FETCH produtor.pessoaJuridica as juridica")
		.append(" WHERE 1 = 1");
		
		if(StringUtils.isNotBlank(pessoaJuridica.getCnpj())) {
			sql.append(" AND juridica.cnpj = (:cnpj)");
		}
		
		if(StringUtils.isNotBlank(pessoaJuridica.getInscricaoEstadual())) {
			sql.append(" AND juridica.inscricaoEstadual = (:inscricaoEstadual)");
		}
		
		if(StringUtils.isNotBlank(produtorRural.getCodigoPropriedade())) {
			sql.append(" AND produtor.codigoPropriedade = (:codigoPropriedade)");
		}
		
		if(StringUtils.isNotBlank(pessoaJuridica.getRazaoSocial())) {
			sql.append(" AND LOWER(juridica.razaoSocial) like LOWER(:razaoSocial)");
		}
		
		if(StringUtils.isNotBlank(pessoaJuridica.getNomeFantasia())) {
			sql.append(" AND LOWER(juridica.nomeFantasia) like LOWER(:nomeFantasia)");
		}
		
		if(StringUtils.isNotBlank(produtorRural.getCodigo())) {
			sql.append(" AND produtor.codigo = (:codigo)");
		}
		
		if(StringUtils.isNotBlank(produtorRural.getNomePropriedade())) {
			sql.append(" AND LOWER(produtor.nomePropriedade) like LOWER(:nomePropriedade)");
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
			if(StringUtils.isNotBlank(produtorRural.getCodigoPropriedade())) {
				query.setParameter("codigoPropriedade", produtorRural.getCodigoPropriedade());
			}
			if(StringUtils.isNotBlank(pessoaJuridica.getRazaoSocial())) {
				query.setParameter("razaoSocial","%".concat(pessoaJuridica.getRazaoSocial()).concat("%"));
			}
			if(StringUtils.isNotBlank(pessoaJuridica.getNomeFantasia())) {
				query.setParameter("nomeFantasia", "%".concat(pessoaJuridica.getNomeFantasia()).concat("%"));
			}
			if(StringUtils.isNotBlank(produtorRural.getCodigo())) {
				query.setParameter("codigo", produtorRural.getCodigo());
			}
			if(StringUtils.isNotBlank(produtorRural.getNomePropriedade())) {
				query.setParameter("nomePropriedade", "%".concat(produtorRural.getNomePropriedade()).concat("%"));
			}
			
			retorno = query.getResultList();
			
			for(ProdutorRural produtor : retorno){
				produtor.getPessoaJuridica().setPessoa(pessoaDao.getPessoaObjeto(produtor.getPessoaJuridica().getPessoa()));
			}
		} catch(Exception e) {
			throw new Excecoes(e, "pesquisarErro");
		} finally {
			Conexao.fechaConexaoEM(em);
		}
		return retorno;
	}
	
	public ProdutorRural getProdutoRural(PessoaJuridica pessoaJuridica) throws Excecoes {
		ProdutorRural retorno = new ProdutorRural();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT produtor FROM ProdutorRural produtor ")
		.append(" LEFT JOIN FETCH produtor.pessoaJuridica as juridica")
		.append(" WHERE juridica.cnpj = (:cnpj)");
		
		EntityManager em = Conexao.getConexaoEM();
		try {
			
			Query query = em.createQuery(sql.toString());
			query.setParameter("cnpj", pessoaJuridica.getCnpj());
			
			retorno = (ProdutorRural) query.getSingleResult();
			
			retorno.getPessoaJuridica().setPessoa(pessoaDao.getPessoaObjeto(retorno.getPessoaJuridica().getPessoa()));
		} catch (NoResultException e) {
			return null;
		} catch(Exception e) {
			throw new Excecoes(e, "pesquisarErro");
		} finally {
			Conexao.fechaConexaoEM(em);
		}
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProdutorRural> getListaProdutoRuralContabilidade() throws Excecoes {
		List<ProdutorRural> retorno = new ArrayList<ProdutorRural>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT produtor FROM ProdutorRural produtor ")
		.append(" LEFT JOIN FETCH produtor.pessoaJuridica as juridica")
		.append(" WHERE produtor.clienteContalidade = 'S' ");

		EntityManager em = Conexao.getConexaoEM();
		try {
			
			Query query = em.createQuery(sql.toString());
			
			retorno = query.getResultList();
			
			for(ProdutorRural produtor : retorno){
				produtor.getPessoaJuridica().setPessoa(pessoaDao.getPessoaObjeto(produtor.getPessoaJuridica().getPessoa()));
			}
		} catch(Exception e) {
			throw new Excecoes(e, "pesquisarErro");
		} finally {
			Conexao.fechaConexaoEM(em);
		}
		return retorno;
	}
}
