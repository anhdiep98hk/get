package com.hiennt.hienntbusiness.controller;

import com.hiennt.hienntbusiness.MyExcetion.HienntException;
import com.hiennt.hienntbusiness.entity.TblCustomer;
import com.hiennt.hienntbusiness.service.ICustomerService;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api")
public class CustomerController {


    @Autowired
    private ICustomerService customerService;
    
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping(path="/customer/all")
    public List<TblCustomer> getAll(){
        List<TblCustomer> customerslist = customerService.getAll();
        return customerslist;
    }

    @GetMapping("/customer/{id}")
    public TblCustomer getById(@PathVariable("id") Integer id){
        TblCustomer customer = customerService.getById(id);
        return customer;
    }

    @PostMapping(path="/customer/add")
    public String addNew(@RequestBody TblCustomer customer){
    	JSONObject result = new JSONObject();
    	try {
			customerService.add(customer);
			result.put("code", "200");
			result.put("message", "add new Customer success!");
			return result.toString();
		} catch (HienntException e) {
			logger.error("Add Customer MyError:", e);
			result.put("code", e.getErrorCode());
			result.put("message", e.getMessage());
			return result.toString();
		} catch (Exception e) {
			logger.error("Add Customer Error :", e);
			result.put("message", e);
			return result.toString();
		}
    }

    @PutMapping("/customer/update")
    public String update(@RequestBody TblCustomer customer){
    	JSONObject result = new JSONObject();
    	try {
			customerService.update(customer);
			result.put("code", "200");
			result.put("message", "update Customer success!");
			return result.toString();
		} catch (HienntException e) {
			logger.error("update Customer MyError:", e);
			result.put("code", e.getErrorCode());
			result.put("message", e.getMessage());
			return result.toString();
		} catch (Exception e) {
			logger.error("update Customer MyError:", e);
			result.put("message", e);
			return result.toString();
		}
    }

    @DeleteMapping("/customer/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
    	JSONObject result = new JSONObject();
    	try {
			customerService.delete(id);
			result.put("code", "200");
			result.put("message", "delete Customer success!");
			return result.toString();
		}catch (HienntException e) {
			logger.error("delete Customer MyError:", e);
			result.put("code", e.getErrorCode());
			result.put("message", e.getMessage());
			return result.toString();
		}catch (Exception e) {
			logger.error("delete Customer Error:", e);
			result.put("message", e);
			return result.toString();
		}
    }
    
    @GetMapping("/customer/getname/")
    public List<TblCustomer> getListName(@Param("nameCus") String nameCus){
    	
    	return customerService.getByName(nameCus);
    }
}
