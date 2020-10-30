package br.com.finance.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.eclipse.microprofile.opentracing.Traced;

import br.com.finance.dao.TransactionDao;
import br.com.finance.dto.TransactionDto;
import br.com.finance.model.Transaction;
import br.com.finance.model.parser.TransactionParser;

@RequestScoped
@Traced
public class TransactionService {
	@Inject
	TransactionDao dao;
	
	public List<TransactionDto> list() {
		return dao
				.list()
				.stream()
				.map(TransactionParser.get()::dto)
				.collect(Collectors.toList());
	}
	
	@Transactional(rollbackOn = Exception.class)
	public void create(TransactionDto transactionDto) {
		Transaction expense = TransactionParser.get().entidade(transactionDto);
		
		dao.create(expense);		
	}

}
