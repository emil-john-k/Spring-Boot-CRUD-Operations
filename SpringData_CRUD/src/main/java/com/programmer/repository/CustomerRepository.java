package com.programmer.repository;

import org.springframework.data.repository.CrudRepository;

import com.programmer.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{

}
