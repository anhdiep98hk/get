package com.hiennt.hienntbusiness.service;

import com.hiennt.hienntbusiness.MyExcetion.HienntException;
import com.hiennt.hienntbusiness.common.Errors;
import com.hiennt.hienntbusiness.entity.TblProduct;
import com.hiennt.hienntbusiness.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<TblProduct> getAll() {
        Iterable<TblProduct> iter = productRepository.findAll();
        List<TblProduct> list= new ArrayList<>();
        iter.forEach( cus -> list.add(cus));
        return list;
    }

    @Override
    public TblProduct getById(int id) {
        TblProduct obj = productRepository.findOne(id);
        return obj;
    }

	@Override
	public TblProduct add(TblProduct product) throws Exception {
		checkProfield(product);
		TblProduct obj = productRepository.findByProName(product.getProName());
		if(obj == null) {
			productRepository.save(product);
			return product;
		}
		throw new HienntException(Errors.PRO_ERROR.getMessage(), Errors.PRO_ERROR.getId());
        
	}

	@Override
	public TblProduct update(TblProduct product) throws Exception{
		checkProfield(product);
		if (product.getProId() == null ) throw new HienntException(Errors.PRO_ERROR_ID.getMessage(), Errors.PRO_ERROR_ID.getId());
		TblProduct product2 = productRepository.findOne(product.getProId());
		if(product2 != null ) {
			productRepository.save(product);
			return product;
		}
		throw new HienntException(Errors.PRO_ERROR.getMessage(), Errors.PRO_ERROR.getId());
	}

	@Override
	public TblProduct delete(int id) throws Exception {
		TblProduct obj = productRepository.findOne(id);
		if(obj != null) {
			productRepository.delete(id);
			return obj;
		}
		throw new HienntException(Errors.PRO_ERROR.getMessage(), Errors.PRO_ERROR.getId());
	}

	@Override
	public List<TblProduct> getByName(String name) {
		List<TblProduct> products = productRepository.findByProNameContaining(name);
		return products;
	}

	@Override
	public List<TblProduct> getByPrice(float price) {
		List<TblProduct> products = productRepository.findByProUnitPrice(price);
		return products;
	}
	
	boolean checkProfield(TblProduct product) throws HienntException {
		if (product.getProName() == null || product.getProName() == "") 
			throw new HienntException(Errors.PRO_ERROR_NAME.getMessage(),Errors.PRO_ERROR_NAME.getId());
		else if (product.getProUnitPrice() == null || product.getProUnitPrice()==0)
			throw new HienntException(Errors.PRO_ERROR_UNITPRCE.getMessage(), Errors.PRO_ERROR_UNITPRCE.getId());
		return true;
	}
}
