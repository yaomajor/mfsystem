package br.com.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang.StringUtils;

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
            	em.persist(pessoa);
            }else{
            	em.merge(pessoa);
            }
            
            if(pessoaJuridica.getId() == null){
            	pessoaJuridica.setPessoa(pessoa);
            	em.persist(pessoaJuridica);
            }else{
            	em.merge(pessoaJuridica);
            }
            
            if(produtorRural.getId() == null){
            	produtorRural.setPessoaJuridica(pessoaJuridica);
            	em.persist(produtorRural);
            }else{
            	em.merge(produtorRural);
            }
            
            for(Telefone telefone : listaTelefone){
            	telefone.setPessoa(pessoa);
            	if(telefone.getId() == null && telefone.getNumero() != null){
            		em.persist(telefone);
            	}else{
	            	em.merge(telefone);
	            }
            }

            if(endereco.getId() == null && StringUtils.isNotBlank(endereco.getLogradouro())){
            	endereco.setPessoa(pessoa);
            	em.persist(endereco);
            }else{
            	if(endereco.getId() != null && StringUtils.isBlank(endereco.getLogradouro()) && StringUtils.isBlank(endereco.getBairro()) 
            			&& StringUtils.isBlank(endereco.getNumero())){
            		em.remove(em.getReference(Endereco.class, endereco.getId()));
            	}else if(endereco.getId() != null && StringUtils.isNotBlank(endereco.getLogradouro()) && StringUtils.isNotBlank(endereco.getBairro()) 
            			&& StringUtils.isNotBlank(endereco.getNumero())){
            		em.merge(endereco);
            	}
            }
            
            em.getTransaction().commit();
        } catch (OptimisticLockException eO) {
        	retorno = false;
            throw new Excecoes(Mensagem.getMensagemProperties("jVersionErro"));
        }catch (RollbackException ex) {
        	ex.printStackTrace(System.out);
			Throwable tr = ex.getCause(); 
			if(tr.getCause() instanceof ConstraintViolationException) {
				new Excecoes(ex, "constraintViolationException");
			}
			new Excecoes(ex, "alterarErro");
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
	
	public Pessoa getPessoaObjeto(Pessoa pessoa) throws Excecoes{
		
		EntityManager em = Conexao.getConexaoEM();
		try {
			Query query = em.createQuery("select p from Pessoa p "
										+ "WHERE p = :pessoa ").setParameter("pessoa", pessoa);
			
			return (Pessoa) query.getSingleResult();
						
		}catch(NoResultException e){	
			return null;
			
		} catch(Exception e) {
			throw new Excecoes(e,"pesquisarErro");
		} finally {
			Conexao.fechaConexaoEM(em);
		}
	}
}
