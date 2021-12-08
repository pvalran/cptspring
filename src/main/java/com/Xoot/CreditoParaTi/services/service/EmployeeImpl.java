package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.dto.UserBoardDTO;
import com.Xoot.CreditoParaTi.entity.pima.Employee;
import com.Xoot.CreditoParaTi.repositories.pima.IEmployeeDao;
import com.Xoot.CreditoParaTi.services.interfaces.IEmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeImpl implements IEmployeeService {
	@Autowired
	private IEmployeeDao employeeDao;
	private Logger logger = LoggerFactory.getLogger(EmployeeImpl.class);

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	@Transactional(readOnly = true)
	public Employee findById(Integer id) {
		return employeeDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Employee save(Employee employee) {
		return employeeDao.save(employee);
	}

	@Override
	public ResponseDTO update(Integer id, UserBoardDTO ObjDTO) {
		Employee entity = employeeDao.findById(id).orElse(null);
		if (entity != null) {
			entity.setDtLastLogin(new Date());
			entity.setMdfd_on(new Date());
			modelMapper.getConfiguration().setSkipNullEnabled(true)
					.setCollectionsMergeEnabled(false)
					.setMatchingStrategy(MatchingStrategies.STRICT);
			modelMapper.map(ObjDTO,entity);
			employeeDao.save(entity);
			ObjDTO = modelMapper.map(entity,UserBoardDTO.class);
			return new ResponseDTO(ObjDTO,"Modificaciòn realizada con exito",true);
		}
		return new ResponseDTO(ObjDTO,"Error en la modificación del registro",false);
	}


	@Override
	@Transactional(readOnly = true)
	public List<Employee> findAllActive() {
		return employeeDao.findAllActive();
	}

	@Override
	public List<Employee> findAllBoard()  { return employeeDao.findAllBoard(); }

	@Override
	public List<Employee> findAllBoardApp()  { return employeeDao.findAllBoardApp(); }

	@Override
	@Transactional(readOnly = true)
	public Employee findByUsername(String username) {
		return employeeDao.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public Employee findByemail(String email) {
		return employeeDao.findByemail(email);
	}

    @Override
    public Employee Login(String userName, String password) { return employeeDao.Login(userName,password); }
}
