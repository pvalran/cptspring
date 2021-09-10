package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.dto.UserBoardDTO;
import com.Xoot.CreditoParaTi.entity.Employee;

import java.util.List;

public interface IEmployeeService {

	public Employee findById(Integer id);
	
	public Employee save(Employee user);

	public ResponseDTO update(Integer id, UserBoardDTO ObjDTO);
	
	public List<Employee> findAllActive();

	public List<Employee> findAllBoard();

	public List<Employee> findAllBoardApp();
	
	public Employee findByUsername(String username);
	
	public Employee findByemail(String email);

	public Employee Login(String userName, String password);
}
