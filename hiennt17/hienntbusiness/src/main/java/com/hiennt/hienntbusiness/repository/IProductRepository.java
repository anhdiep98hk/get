package com.hiennt.hienntbusiness.repository;

import com.hiennt.hienntbusiness.entity.TblProduct;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface IProductRepository extends JpaRepository<TblProduct, Integer> {
	TblProduct findByProName (String proName);
	List<TblProduct> findByProNameContaining (String proName);
	List<TblProduct> findByProUnitPrice (Float proUnitPrice);
}
