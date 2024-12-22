package com.programmer.serivice;

import java.util.List;

import com.programmer.dto.CustomerDTO;
import com.programmer.exception.InfyBankException;

public interface CustomerService {
	
	public void addCustomer(CustomerDTO customer) throws InfyBankException;
	public void updateCustomer(Integer customerId, String emailId) throws InfyBankException;
	public CustomerDTO getCustomer(Integer customerId) throws InfyBankException;
	public List<CustomerDTO> findAll() throws InfyBankException;
	public void deleteCustomer(Integer customerId) throws InfyBankException;
}
