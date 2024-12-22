package com.programmer;

import java.time.LocalDate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertyResolver;

import com.programmer.dto.CustomerDTO;
import com.programmer.serivice.CustomerServiceImpl;

@SpringBootApplication
public class SpringDataCrudApplication implements CommandLineRunner {

	public static final Log LOGGER = LogFactory.getLog(SpringDataCrudApplication.class);

	@Autowired
	CustomerServiceImpl customerService;

	@Autowired
	Environment environment;

	PropertyResolver propertyResolver = environment;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataCrudApplication.class, args);

	}

	public void run(String... args) throws Exception {

		addCustomer();
		updateCustomer();
		getCustomer();
		findAllCustomers();
		deleteCustomer();

	}

	public void addCustomer() {

		CustomerDTO customer = new CustomerDTO();
		customer.setCustomerId(9);
		customer.setEmailId("harry@infy.com");
		customer.setName("Harry");
		customer.setDateOfBirth(LocalDate.now());

		try {
			customerService.addCustomer(customer);
			LOGGER.info(environment.getProperty("UserInterface.INSERT_SUCCESS"));
		} catch (Exception e) {

			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}

	}

	public void updateCustomer() {

		try {
			customerService.updateCustomer(2, "tim12@infy.com");
			LOGGER.info(environment.getProperty("UserInterface.UPDATE_SUCCESS"));
		} catch (Exception e) {

			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}

	public void getCustomer() {

		try {
			CustomerDTO customer = customerService.getCustomer(2);
			LOGGER.info(customer);
		} catch (Exception e) {

			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}

	public void findAllCustomers() {

		try {
			customerService.findAll().forEach(LOGGER::info);
		} catch (Exception e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}

	public void deleteCustomer() {

		try {
			customerService.deleteCustomer(5);
			LOGGER.info(environment.getProperty("UserInterface.DELETE_SUCCESS"));
		} catch (Exception e) {

			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}

	}

}
