package com.hiennt.hienntbusiness.repository;

import com.hiennt.hienntbusiness.entity.TblInvoice;
import com.hiennt.hienntbusiness.entity.TblInvoiceId;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface IInvoiceRepository extends JpaRepository<TblInvoice, TblInvoiceId> {
	List<TblInvoice> findByIdCusId (Integer cusId);
	List<TblInvoice> findByTblCustomerCusNameContaining (String cusName);
	List<TblInvoice> findByTblProductProNameContaining (String proName);
}
