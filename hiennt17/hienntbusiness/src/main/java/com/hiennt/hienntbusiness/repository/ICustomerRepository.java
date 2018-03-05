package com.hiennt.hienntbusiness.repository;

import com.hiennt.hienntbusiness.entity.TblCustomer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ICustomerRepository extends JpaRepository<TblCustomer, Integer> {
	List<TblCustomer> findByCusNameContaining(String cusName);
}
