package com.programmer.serivice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmer.dto.CustomerDTO;
import com.programmer.entity.Customer;
import com.programmer.exception.InfyBankException;
import com.programmer.repository.CustomerRepository;

@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public void addCustomer(CustomerDTO customerDto) throws InfyBankException {
		Optional<Customer> optional = customerRepository.findById(customerDto.getCustomerId());
		if (optional.isPresent())
			throw new InfyBankException("Service.CUSTOMER_FOUND");
		Customer customer = new Customer();
		customer.setDateOfBirth(customerDto.getDateOfBirth());
		customer.setEmailId(customerDto.getEmailId());
		customer.setName(customerDto.getName());
		customer.setCustomerId(customerDto.getCustomerId());
		customerRepository.save(customer);

	}

	@Override
	public void updateCustomer(Integer customerId, String emailId) throws InfyBankException {

		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		customer.setEmailId(emailId);
	}

	@Override
	public CustomerDTO getCustomer(Integer customerId) throws InfyBankException {

		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setCustomerId(customer.getCustomerId());
		customerDto.setDateOfBirth(customer.getDateOfBirth());
		customerDto.setEmailId(customer.getEmailId());
		customerDto.setName(customer.getName());
		return customerDto;

	}

	@Override
	public List<CustomerDTO> findAll() throws InfyBankException {

		Iterable<Customer> customers = customerRepository.findAll();
		List<CustomerDTO> customerDTOs = new ArrayList<>();

		customers.forEach(customer -> {

			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setDateOfBirth(customer.getDateOfBirth());
			customerDTO.setEmailId(customer.getEmailId());
			customerDTO.setName(customer.getName());
			customerDTOs.add(customerDTO);

		});
		if (customerDTOs.isEmpty())
			throw new InfyBankException("Service.CUSTOMERS_NOT_FOUND");
		return customerDTOs;
	}

	@Override
	public void deleteCustomer(Integer customerId) throws InfyBankException {
		
		Optional<Customer> optional=customerRepository.findById(customerId);
		optional.orElseThrow(()->new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		customerRepository.deleteById(customerId);
		
	}

}
