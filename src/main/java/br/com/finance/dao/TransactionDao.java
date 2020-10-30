package br.com.finance.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.eclipse.microprofile.opentracing.Traced;

import br.com.finance.model.Transaction;

@RequestScoped
@Traced
public class TransactionDao {
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public Long create(Transaction transaction) {
		// String nameSql = "INSERT_EXPENSE";
//		inserirOuAtualizar(nameSql, expense);
		transaction.persistAndFlush();
		return transaction.getId();
	}
	
	public List<Transaction> list(){
		String nameSql = "LIST_TRANSACTIONS";
		
		List<Transaction> response;
		
		TypedQuery<Transaction> query = em.createNamedQuery(nameSql, Transaction.class);
		
		try {
			response = query.getResultList();
		}catch(NoResultException e) {
			response = new ArrayList();
		}
		
		return response;
	}

}
