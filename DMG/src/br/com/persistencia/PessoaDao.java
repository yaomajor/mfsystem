package br.com.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;

import br.com.bancodedados.Conexao;
import br.com.bancodedados.Dao;
import br.com.entity.Endereco;
import br.com.entity.Pessoa;
import br.com.entity.PessoaJuridica;
import br.com.entity.ProdutorRural;
import br.com.entity.Telefone;
import br.com.exception.Excecoes;
import br.com.util.Mensagem;

public class PessoaDao extends Dao<Pessoa>{
	private static final long serialVersionUID = 4760465531687606004L;

	public PessoaDao() {
		super(Pessoa.class);
	}
	
	 public boolean salvaProdutorRural(Pessoa pessoa, ProdutorRural produtorRural , PessoaJuridica pessoaJuridica ,List<Telefone> listaTelefone, Endereco endereco) throws Exception{
	        boolean retorno = true;
		 	EntityManager em = Conexao.getConexaoEM();
	        try {
	            em.getTransaction().begin();
	            
	            if(pessoa.getId() == null){
	            	pessoa = em.merge(pessoa);
	            }
	            
	            if(pessoaJuridica.getId() == null){
	            	pessoaJuridica.setPessoa(pessoa);
	            	em.persist(pessoaJuridica);
	            }
	            
	            if(produtorRural.getId() == null){
	            	produtorRural.setPessoaJuridica(pessoaJuridica);
	            	em.persist(produtorRural);
	            }
	            
	            for(Telefone telefone : listaTelefone){
	            	telefone.setPessoa(pessoa);
	            	if(pessoaJuridica.getId() == null){
	            		em.merge(telefone);
	            	}
	            }
	            
	            if(endereco.getId() == null){
	            	endereco.setPessoa(pessoa);
	            	em.merge(endereco);
	            }
	            
	            em.getTransaction().commit();
	        } catch (OptimisticLockException eO) {
	        	retorno = false;
	            throw new Excecoes(Mensagem.getMensagemProperties("jVersionErro"));
	        } catch (Exception ex) {
	        	ex.printStackTrace(System.out);
	        	retorno = false;
	            throw new Excecoes(ex, "pesquisarErro");
	        } finally {
	            if (em.getTransaction().isActive()) {
	                em.getTransaction().rollback();
	            }
	            Conexao.fechaConexaoEM(em);
	        }
	        return retorno;
	    }
}
