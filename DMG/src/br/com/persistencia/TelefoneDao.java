package br.com.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.bancodedados.Conexao;
import br.com.bancodedados.Dao;
import br.com.entity.Pessoa;
import br.com.entity.Telefone;
import br.com.exception.Excecoes;

public class TelefoneDao extends Dao<Telefone>{
	private static final long serialVersionUID = -6605487745187271707L;

	public TelefoneDao() {
		super(Telefone.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Telefone> getTelefonePorPessoa(Pessoa pessoa) throws Excecoes{
		
		List<Telefone> listaRetorno = new ArrayList<Telefone>();
		
		EntityManager em = Conexao.getConexaoEM();
		try {
			Query query = em.createQuery("select object(telefone) from Telefone AS telefone "
										//+ "LEFT JOIN FETCH telefone.pessoa "
										+ "WHERE telefone.pessoa = :pessoa "
										+ "ORDER BY telefone.id").setParameter("pessoa", pessoa);
			System.out.println("pessoa.getId() : " + pessoa.getId());
			
			listaRetorno = query.getResultList();
						
		}catch(NoResultException e){	
			listaRetorno = new ArrayList<Telefone>();
			
		} catch(Exception e) {
			throw new Excecoes(e,"pesquisarErro");
		} finally {
			Conexao.fechaConexaoEM(em);
		}
		
		return listaRetorno;
	}
}
