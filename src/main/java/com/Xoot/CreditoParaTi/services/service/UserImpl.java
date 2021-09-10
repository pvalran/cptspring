package com.Xoot.CreditoParaTi.services.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.dto.SpouseDTO;
import com.Xoot.CreditoParaTi.dto.UserBoardDTO;
import com.Xoot.CreditoParaTi.entity.Spouse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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

import com.Xoot.CreditoParaTi.services.interfaces.IUserService;
import com.Xoot.CreditoParaTi.entity.Usuario;
import com.Xoot.CreditoParaTi.repositories.interfaces.IUserDao;

@Service
public class UserImpl  implements IUserService, UserDetailsService{
	@Autowired
	private IUserDao userDao;
	private Logger logger = LoggerFactory.getLogger(UserImpl.class);

	@Autowired
	private ModelMapper modelMapper;
	
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
	public ResponseDTO update(Integer id, UserBoardDTO ObjDTO) {
		Usuario entity = userDao.findById(id).orElse(null);
		if (entity != null) {
			entity.setMdfd_on(new Date());
			modelMapper.getConfiguration().setSkipNullEnabled(true)
					.setCollectionsMergeEnabled(false)
					.setMatchingStrategy(MatchingStrategies.STRICT);
			modelMapper.map(ObjDTO,entity);
			userDao.save(entity);
			ObjDTO = modelMapper.map(entity,UserBoardDTO.class);
			return new ResponseDTO(ObjDTO,"Modificaciòn realizada con exito",true);
		}
		return new ResponseDTO(ObjDTO,"Error en la modificación del registro",false);
	}


	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAllActive() {
		return userDao.findAllActive();
	}

	@Override
	public List<Usuario> findAllBoard()  { return userDao.findAllBoard(); }

	@Override
	public List<Usuario> findAllBoardApp()  { return userDao.findAllBoardApp(); }

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

    @Override
    public Usuario Login(String userName, String password) { return userDao.Login(userName,password); }


}
