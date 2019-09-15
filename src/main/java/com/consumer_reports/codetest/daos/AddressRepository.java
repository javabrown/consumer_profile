package com.consumer_reports.codetest.daos;

import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AddressDao, Integer> {
}