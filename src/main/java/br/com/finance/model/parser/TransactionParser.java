package br.com.finance.model.parser;

import br.com.finance.dto.TransactionDto;
import br.com.finance.model.Transaction;

public class TransactionParser {
	public static TransactionParser get() {
		return new TransactionParser();
	}
	
	public Transaction entidade(TransactionDto dto) {
		Transaction entidade = new Transaction();
		
		entidade.setId(dto.getId());
		entidade.setTitle(dto.getTitle());
		entidade.setDescription(dto.getDescription());
		entidade.setAmount(dto.getAmount());
		entidade.setType(dto.getType());
		entidade.setDate(dto.getDate());
		
		return entidade;
	}
	
	public TransactionDto dto(Transaction entidade) {
		
		TransactionDto dto = new TransactionDto();
		
		dto.setId(entidade.getId());
		dto.setTitle(entidade.getTitle());
		dto.setDescription(entidade.getDescription());
		dto.setAmount(entidade.getAmount());
		dto.setType(entidade.getType());
		dto.setDate(entidade.getDate());
		
		return dto;
	}
}
