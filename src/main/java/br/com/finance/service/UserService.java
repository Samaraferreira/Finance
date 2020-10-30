package br.com.finance.service;

import javax.inject.Inject;

import br.com.finance.dao.UserDao;
import br.com.finance.model.User;

public class UserService {
	@Inject
	UserDao dao;
	
	public User findByEmail(String email) {
		return dao.findByEmail(email);
	}
}
