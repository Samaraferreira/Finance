package br.com.finance.dao;

import javax.enterprise.context.RequestScoped;

import br.com.finance.model.User;

@RequestScoped
public class UserDao {
	public User findByEmail(String email) {	
		return User.find("email", email).firstResult();
	}
}
