package com.hiennt.hienntbusiness.service;

import com.hiennt.hienntbusiness.MyExcetion.HienntException;
import com.hiennt.hienntbusiness.common.Errors;
import com.hiennt.hienntbusiness.entity.TblInvoice;
import com.hiennt.hienntbusiness.entity.TblInvoiceId;
import com.hiennt.hienntbusiness.repository.ICustomerRepository;
import com.hiennt.hienntbusiness.repository.IInvoiceRepository;
import com.hiennt.hienntbusiness.repository.IProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InvoiceService implements IInvoiceService {

    @Autowired
    private IInvoiceRepository invoiceRepository;
    
    @Autowired
    private IProductRepository productsRepository;
    
    @Autowired
    private ICustomerRepository customersRepository;
    

    @Override
    public List<TblInvoice> getAll() {
        Iterable<TblInvoice> iter = invoiceRepository.findAll();
        List<TblInvoice> list = new ArrayList<>();
        iter.forEach(invoice -> list.add(invoice));
        return list;
    }

	@Override
	public TblInvoice getById(Integer cusId, Integer proId){
		TblInvoiceId invoiceId = new TblInvoiceId(cusId, proId);
			return invoiceRepository.findOne(invoiceId);
	}

	@Override
	public TblInvoice delete(Integer cusId, Integer proId) throws Exception {
		if (productsRepository.findOne(proId)== null)
			throw new HienntException(Errors.INV_ERROR_ID_PRO.getMessage(), Errors.INV_ERROR_ID_PRO.getId());
		else if (customersRepository.findOne(cusId)==null)
			throw new HienntException(Errors.INV_ERROR_ID_CUS.getMessage(), Errors.INV_ERROR_ID_CUS.getId());
		TblInvoiceId invoiceId = new TblInvoiceId(cusId, proId);
		TblInvoice invoice = invoiceRepository.findOne(invoiceId);
		if (invoice != null) {
			invoiceRepository.delete(invoiceId);
			return invoice;
		}
		throw new HienntException(Errors.INV_ERROR_ID.getMessage(), Errors.INV_ERROR_ID.getId());
	}


	@Override
	public TblInvoice add(TblInvoice invoice) throws Exception {
		checkInvfield(invoice);
		if (invoice.getIssueDate() == null)
			throw new HienntException(Errors.INV_ERROR_ISSUEDATE.getMessage(), Errors.INV_ERROR_ISSUEDATE.getId());
		if (productsRepository.findOne(invoice.getId().getProId())== null)
			throw new HienntException(Errors.INV_ERROR_ID_PRO.getMessage(), Errors.INV_ERROR_ID_PRO.getId());
		else if (customersRepository.findOne(invoice.getId().getCusId())==null)
			throw new HienntException(Errors.INV_ERROR_ID_CUS.getMessage(), Errors.INV_ERROR_ID_CUS.getId());
		TblInvoiceId invoiceId = new TblInvoiceId(invoice.getId().getCusId(), invoice.getId().getProId());
		if (invoiceRepository.findOne(invoiceId) != null ) {
			throw new HienntException(Errors.INV_ERROR_ID.getMessage(), Errors.INV_ERROR_ID.getId());
		}else {
			return invoiceRepository.save(invoice);
		}
		
	}

	@Override
	public TblInvoice update(TblInvoice invoice) throws Exception {
		checkInvfield(invoice);
		if (productsRepository.findOne(invoice.getId().getProId())== null)
			throw new HienntException(Errors.INV_ERROR_ID_PRO.getMessage(), Errors.INV_ERROR_ID_PRO.getId());
		else if (customersRepository.findOne(invoice.getId().getCusId())==null)
			throw new HienntException(Errors.INV_ERROR_ID_CUS.getMessage(), Errors.INV_ERROR_ID_CUS.getId());
		TblInvoiceId invoiceId = new TblInvoiceId(invoice.getId().getCusId(), invoice.getId().getProId());
		if (invoiceRepository.findOne(invoiceId) != null ) {
			throw new HienntException(Errors.INV_ERROR_ID.getMessage(), Errors.INV_ERROR_ID.getId());
		}else {
			return invoiceRepository.save(invoice);
		}
	}

	@Override
	public List<TblInvoice> getByIdCusId(Integer cusId) {
		return invoiceRepository.findByIdCusId(cusId);
	}

	@Override
	public List<TblInvoice> searchByCusName(String cusName) {
		return invoiceRepository.findByTblCustomerCusNameContaining(cusName);
	}

	@Override
	public List<TblInvoice> searchByProName(String proName) {
		return invoiceRepository.findByTblProductProNameContaining(proName);
	}
	
	 boolean checkInvfield(TblInvoice invoice) throws HienntException {
		if (invoice.getId() == null)
			throw new HienntException(Errors.INV_ERROR_ID.getMessage(), Errors.INV_ERROR_ID.getId());
		else if (invoice.getTblCustomer().getCusId() == null )
			throw new HienntException(Errors.INV_ERROR_ID_CUS.getMessage(),Errors.INV_ERROR_ID_CUS.getId());
		else if (invoice.getTblProduct().getProId() == null )
			throw new HienntException(Errors.INV_ERROR_ID_PRO.getMessage(),Errors.INV_ERROR_ID_PRO.getId());
		else if (invoice.getNumbers() == null || invoice.getNumbers() == 0)
			throw new HienntException(Errors.INV_ERROR_NUMBERS.getMessage(), Errors.INV_ERROR_NUMBERS.getId());
		else if (invoice.getTotalPayment()== null || invoice.getTotalPayment()==0)
			throw new HienntException(Errors.INV_ERROR_TOTALPAYMENT.getMessage(), Errors.INV_ERROR_TOTALPAYMENT.getId());
		return true;
	}
	
}
