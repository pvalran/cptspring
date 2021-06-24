package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Xoot.CreditoParaTi.Definiciones.Services.IUserService;
import com.Xoot.CreditoParaTi.entity.Usuario;
import com.Xoot.CreditoParaTi.models.dao.IUserDao;

@Service
public class UserImpl  implements IUserService, UserDetailsService{
	@Autowired
	private IUserDao userDao;
	private Logger logger = LoggerFactory.getLogger(UserImpl.class);
	
	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Integer id) {
		return userDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Usuario save(Usuario user) {
		return userDao.save(user);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		userDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAllActive() {
		return userDao.findAllActive();
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = userDao.findByUsername(username);
		
		if(usuario == null) {
			logger.error("No existe el usuaio");
			throw new UsernameNotFoundException("No existe el usuaio");
		}
		
		List<GrantedAuthority> authorities = usuario.getCategoryUser()
				.stream()
				.map(category -> new SimpleGrantedAuthority(category.getName()))
				.peek(auth -> logger.info("Role: " + auth.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(usuario.getUsername(), usuario.getPassword(), authorities);
	}

	
	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByemail(String email) {
		return userDao.findByemail(email);
	}
}
