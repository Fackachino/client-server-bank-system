package com.samoylov.server.repository;

import com.samoylov.server.entity.Customer;;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
