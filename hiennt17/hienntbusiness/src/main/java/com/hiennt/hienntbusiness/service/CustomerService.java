package com.hiennt.hienntbusiness.service;

import com.hiennt.hienntbusiness.MyExcetion.HienntException;
import com.hiennt.hienntbusiness.common.Errors;
import com.hiennt.hienntbusiness.entity.TblCustomer;
import com.hiennt.hienntbusiness.repository.ICustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;
    
    @Override
    public List<TblCustomer> getAll() {
        Iterable<TblCustomer> iter = customerRepository.findAll();
        List<TblCustomer> listCus= new ArrayList<>();
        iter.forEach( cus -> listCus.add(cus));
        return listCus;
    }

    @Override
    public TblCustomer getById(int id) {
        TblCustomer obj = customerRepository.findOne(id);
        return obj;
    }


    @Override
    public TblCustomer update(TblCustomer customer) throws HienntException {
    	checkCusfield(customer);
    	if (customer.getCusId() == null) throw new HienntException(Errors.CUS_ERROR_ID.getMessage(), Errors.CUS_ERROR_ID.getId());
		TblCustomer customernew = customerRepository.findOne(customer.getCusId());
		if (customernew != null) {
			customerRepository.save(customer);
			return customer;
		}
		throw new HienntException(Errors.CUS_ERROR.getMessage(), Errors.CUS_ERROR.getId());
    }

    @Override
    public TblCustomer delete(int id) throws HienntException {
    	TblCustomer customernew = customerRepository.findOne(id);
    	if (customernew != null) {
    		customerRepository.delete(id);
    		return customernew;
		}
    	throw new HienntException(Errors.CUS_ERROR_ID.getMessage(),Errors.CUS_ERROR_ID.getId());
        
    }

	@Override
	public TblCustomer add(TblCustomer customer) throws HienntException  {
		checkCusfield(customer);
		return customerRepository.save(customer);
	}

	@Override
	public List<TblCustomer> getByName(String name) {
		List<TblCustomer> customer = customerRepository.findByCusNameContaining(name);
		return customer;
	}
	
	
	 boolean checkCusfield(TblCustomer customer) throws HienntException {
		if (customer.getCusName() == null || customer.getCusName() == "") 
			throw new HienntException(Errors.CUS_ERROR_NAME.getMessage(), Errors.CUS_ERROR_NAME.getId());
    	else if (customer.getCusAddress() == null || customer.getCusAddress() == "") 
    		throw new HienntException(Errors.CUS_ERROR_ADDRESS.getMessage(), Errors.CUS_ERROR_ADDRESS.getId());
    	else if (customer.getCusCity() == null || customer.getCusCity() == "") 
    		throw new HienntException(Errors.CUS_ERROR_CITY.getMessage(), Errors.CUS_ERROR_CITY.getId());
    	else if (customer.getCusState() == null || customer.getCusState() == "") 
    		throw new HienntException(Errors.CUS_ERROR_STATE.getMessage(), Errors.CUS_ERROR_STATE.getId());
    	else if (customer.getCusZip() == null || customer.getCusZip() == "") 
    		throw new HienntException(Errors.CUS_ERROR_ZIP.getMessage(), Errors.CUS_ERROR_ZIP.getId());
    	else if (customer.getCusPhone()==null || customer.getCusPhone()== "") 
    		throw new HienntException(Errors.CUS_ERROR_PHONE.getMessage(), Errors.CUS_ERROR_PHONE.getId());
		return true;
	}
}
