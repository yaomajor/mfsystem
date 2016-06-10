package br.com.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.bancodedados.Conexao;
import br.com.bancodedados.Dao;
import br.com.entity.Endereco;
import br.com.entity.Pessoa;
import br.com.exception.Excecoes;

public class EnderecoDao extends Dao<Endereco>{
	private static final long serialVersionUID = 4196826047023077873L;
	
	public EnderecoDao() {
		super(Endereco.class);
	}
	
	public Endereco getEnderecoPorPessoa(Pessoa pessoa) throws Excecoes{
		
		EntityManager em = Conexao.getConexaoEM();
		try {
			Query query = em.createQuery("select object(endereco) from Endereco AS endereco "
										+ "LEFT JOIN FETCH endereco.pessoa "
										+ "WHERE endereco.pessoa = :pessoa "
										+ "ORDER BY endereco.id").setParameter("pessoa", pessoa);
			
			return (Endereco) query.getSingleResult();
						
		}catch(NoResultException e){	
			return null;
			
		} catch(Exception e) {
			throw new Excecoes(e,"pesquisarErro");
		} finally {
			Conexao.fechaConexaoEM(em);
		}
	}
}
