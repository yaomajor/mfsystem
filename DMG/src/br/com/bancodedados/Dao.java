package br.com.bancodedados;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import org.hibernate.exception.ConstraintViolationException;

import br.com.exception.Excecoes;

import org.apache.commons.lang.SerializationUtils;

/**
 * Classe Genérica para a manipulação de objetos
 * @param <T> - Classe Utilizada para o DAO
 * @author Grupo CHQ
 */
public abstract class Dao<T extends Serializable> implements Serializable {
	private static final long serialVersionUID = 6176187069298051614L;

	protected String nomeEntidade;
	protected Class<T> classe;

	/**
	 * Construtor
	 * @param classe - classe utilizada no construtor
	 */
	public Dao(Class<T> classe) {
		this.nomeEntidade = classe.getName();
		this.classe = classe;
	}
	
	/**
	 * @param obj - objeto a ser alterado
	 * @return obj - objeto alterado
	 * @throws Excecoes
	 */
	public T alterar(T obj) throws Excecoes {
		EntityManager em = Conexao.getConexaoEM();
		
		try {
			em.getTransaction().begin();
			obj = em.merge(obj);
			em.getTransaction().commit();
			
			return obj;
		} catch (OptimisticLockException eO) {
			throw new Excecoes("jVersionErro");
		} catch (RollbackException ex) {
			Throwable tr = ex.getCause(); 
			if(tr.getCause() instanceof ConstraintViolationException) {
				throw new Excecoes(ex, "constraintViolationException");
			}
			throw new Excecoes(ex, "alterarErro");
		} catch (Exception ex) {
			throw new Excecoes(ex, "alterarErro");
		} finally {
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			
			Conexao.fechaConexaoEM(em);
		}
	}
	
	/**
	 * @param obj - objeto a ser salvo
	 * @return obj - objeto salvo
	 * @throws Excecoes
	 */
	@SuppressWarnings("unchecked")
	public T salvar(T obj) throws Excecoes {
		EntityManager em = Conexao.getConexaoEM();
		try {
			T objetoClonado = (T) SerializationUtils.clone(obj);
			
			em.getTransaction().begin();
			em.persist(objetoClonado);
			em.getTransaction().commit();
			
			return objetoClonado;
		} catch (RollbackException ex) {
			Throwable tr = ex.getCause(); 
			if(tr.getCause() instanceof ConstraintViolationException) {
				throw new Excecoes(ex, "constraintViolationException");
			}
			
			throw new Excecoes(ex, "salvarErro");
		} catch (Exception ex) {
			throw new Excecoes(ex, "salvarErro");
		} finally {
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			
			Conexao.fechaConexaoEM(em);
		}
	}

	/**
	 * @param obj - objeto a ser apagado
	 * @throws Excecoes
	 */
	public void excluir(Long id) throws Excecoes {
		EntityManager em = Conexao.getConexaoEM();
		try {
			em.getTransaction().begin();
			em.remove(em.find(this.classe, id));
			em.getTransaction().commit();
		} catch (RollbackException ex) {
			Throwable tr = ex.getCause(); 
			if(tr.getCause() instanceof ConstraintViolationException) {
				throw new Excecoes(ex, "constraintViolationExceptionExcluir");
			}
			
			throw new Excecoes(ex, "excluirErro");
		} catch (Exception ex) {
			throw new Excecoes(ex, "excluirErro");
		} finally {
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			
			Conexao.fechaConexaoEM(em);
		}
	}

	/**
	 * @param id - ID a ser buscado na entidade
	 * @return objeto da entidade
	 * @throws Excecoes
	 */
	@SuppressWarnings("unchecked")
	public T getObjetoPorId(Long id) throws Excecoes {
		EntityManager em = Conexao.getConexaoEM();
		try {
			return (T) em.createQuery("select object(c) from " + nomeEntidade + " as c where c.id = :id" )
			.setParameter("id", id)
			.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch(Exception e) {
			throw new Excecoes(e, "pesquisarErro");
		} finally {
			Conexao.fechaConexaoEM(em);
		}
	}

	/**
	 * @param ordem - atributos da entidade a serem ordenados
	 * @return lista contendo os objetos da entidade ordenados
	 * @throws Excecoes
	 */
	@SuppressWarnings("unchecked")
	public List<T> getTodos(String... ordem) throws Excecoes {
		StringBuilder strBuilder = new StringBuilder();
		
		int contador = 0;
		for(String str : ordem) {
			if(contador == 0){
				strBuilder.append(" order by "); 
			}
			strBuilder.append("c.").append(str);
			contador++;
			
			if(contador < ordem.length) {
				strBuilder.append(",");
			}
		}
		
		EntityManager em = Conexao.getConexaoEM();
		try {
			Query listQuery = em.createQuery("select object(c) from "+ nomeEntidade +" as c"+ strBuilder.toString() );
			return listQuery.getResultList();
		} catch(Exception e) {
			throw new Excecoes(e,"pesquisarErro");
		} finally {
			Conexao.fechaConexaoEM(em);
		}
	}
	
	/**
	 * Método responsável por realizar a pesquisa através de um objeto populado sobrecarregado
	 * @param entidade - objeto que contenha os atributos para a pesquisa
	 * @param ordem - nome dos atributos para serem utilizados no order by da query
	 * @return lista do objeto com o retorno da pesquisa
	 * @throws Excecoes
	 */
	public List<T> getPorObjeto(T entidade, String... ordem) throws Excecoes {
		return getPorObjeto(entidade, null, ordem);
	}
	
	/**
	 * Método responsável por realizar a pesquisa através de um objeto populado
	 * @param entidade - objeto que contenha os atributos para a pesquisa
	 * @param classeInterna - array contendo as classes que deverão ser utilizadas no Left Join
	 * @param ordem - nome dos atributos para serem utilizados no order by da query
	 * @return lista do objeto com o retorno da pesquisa
	 * @throws Excecoes
	 */
	@SuppressWarnings("rawtypes")
	public List<T> getPorObjeto(T entidade, Class[] classeInterna, String... ordem) throws Excecoes {
		if(entidade != null) {
			StringBuilder sql = new StringBuilder();
			String nomeEntidade = getNomeEntidade(entidade.getClass());
			sql.append("SELECT ").append(nomeEntidade).append(" FROM ").append(entidade.getClass().getCanonicalName()).append(" ").append(nomeEntidade).append(" ");
			
			if(classeInterna != null) {
				for(Class obj : classeInterna) {
					sql.append(concatenaObjetosRelacionados(entidade.getClass(), obj));
				}
			}
			
			return getPorObjeto(entidade, nomeEntidade, "", sql, 1, new HashMap<Integer, Object>(), ordem);
		} else {
			return null;
		}
	}
	
	/**
	 * Método responsável por controlar a recursividade dos atributos de entidades do objeto
	 * @param entidade - objeto que contenha os atributos para a pesquisa
	 * @param nomeEntidade - nome da entidade
	 * @param chaveEstrangeira - atributo de entidade do objeto
	 * @param sql - Query do sql
	 * @param contadorChamadaMetodo - controlador da recursividade
	 * @param mapaParametros - mapa contendo os atributos que foram preenchidos do objeto a ser pesquisado
	 * @param ordem - nome dos atributos para serem utilizados no order by da query
	 * @return lista do objeto com o retorno da pesquisa
	 * @throws Excecoes
	 */
	@SuppressWarnings("unchecked")
	private List<T> getPorObjeto(T entidade, String nomeEntidade, String chaveEstrangeira, StringBuilder sql, Integer contadorChamadaMetodo, 
			Map<Integer, Object> mapaParametros, String... ordem) throws Excecoes {
		
		try {
			Field atributos[] = entidade.getClass().getDeclaredFields();
			for (int i = 0; i < atributos.length; i++) {
				Field atributo = atributos[i];
				atributo.setAccessible(true);
				if(atributo.get(entidade) != null && !(Modifier.isStatic(atributo.getModifiers()) && Modifier.isFinal(atributo.getModifiers()))) {
					if(atributo.getAnnotation(javax.persistence.ManyToOne.class) == null && atributo.getAnnotation(javax.persistence.Transient.class) == null) {
						if((atributo.getType() == String.class && !((String)atributo.get(entidade)).isEmpty()) || (atributo.getType() == Long.class && ((Long)atributo.get(entidade)).intValue() > 0)) {
							sql.append(mapaParametros.size() > 0 ? "AND " : "WHERE ");
							mapaParametros.put(mapaParametros.size()+1, atributo.get(entidade));
							
		                	if(atributo.getType() == String.class) {
		                		sql.append("LOWER(").append(nomeEntidade).append(".").append(atributo.getName()).append(") LIKE LOWER('%' || ?")
		                		.append(mapaParametros.size()).append(" || '%') ");
		                	} else {
		                		sql.append(nomeEntidade).append(".").append(atributo.getName()).append(" = ?").append(mapaParametros.size()).append(" ");
		                	}
						}
					} else {
						getPorObjeto((T) atributo.get(entidade), nomeEntidade.concat(".").concat(atributo.getName()), atributo.getName(), sql, contadorChamadaMetodo+1, mapaParametros, ordem);
					}
				}
			}
			
			if(contadorChamadaMetodo == 1) {
				if(ordem.length > 0) {
					sql.append("ORDER BY ");
					for(int i = 0 ; i < ordem.length ; i++) {
						sql.append(nomeEntidade).append(".").append(ordem[i]);
						
						if(i < ordem.length - 1) {
							sql.append(", ");
						}
					}
				}
				
				
				EntityManager em = Conexao.getConexaoEM();
				Query query = em.createQuery(sql.toString());

				for(Entry<Integer, Object> entry : mapaParametros.entrySet()) {
					query.setParameter(entry.getKey(), entry.getValue());
				}
				
				List<T> listaRetorno = query.getResultList();
					
				Conexao.fechaConexaoEM(em);
				
				return listaRetorno;
			}
			
			return null;
		} catch (IllegalAccessException e) {
			throw new Excecoes(e, "pesquisarErro");
		}
	}
	
	/**
	 * Método responsável por formatar o nome de entidade para ser utilizado na query de busca
	 * @param entidade - a classe da entidade
	 * @return String contendo o nome da entidade formatada para a pesquisa
	 */
	@SuppressWarnings("rawtypes")
	private String getNomeEntidade(Class entidade) {
		return String.valueOf(entidade.getSimpleName().charAt(0)).toLowerCase().concat(entidade.getSimpleName().substring(1));
	}
	
	/**
	 * @param classe - classe do objeto
	 * @param classeInterna - classe dos atributos de entidade do objeto
	 * @return String contendo a instrução de left join para a query
	 */
	@SuppressWarnings("rawtypes")
	public String concatenaObjetosRelacionados(Class classe, Class... classeInterna) {
		StringBuilder builder = new StringBuilder();
		String nomeEntidade = getNomeEntidade(classe);
		
		for(Class clazz: classeInterna) {
			String str = getNomeEntidade(clazz);
			builder.append("LEFT JOIN FETCH ").append(nomeEntidade).append(".").append(str).append(" ");
		}
		
		return builder.toString();
	}
}
