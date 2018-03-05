package com.hiennt.hienntbusiness.controller;

import com.hiennt.hienntbusiness.MyExcetion.HienntException;
import com.hiennt.hienntbusiness.entity.TblInvoice;
import com.hiennt.hienntbusiness.service.IInvoiceService;

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
public class InvoiceController {
    @Autowired
    private IInvoiceService invoiceService;
    
    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @GetMapping("/invoice/all")
    public List<TblInvoice> getAll(){
        List<TblInvoice> list= invoiceService.getAll();
        return list;
    }
    
    @GetMapping("/invoice/bycus/")
    public List<TblInvoice> getByNameCusId(@Param("cusId") Integer cusId){
        List<TblInvoice> list= invoiceService.getByIdCusId(cusId);
        return list;
    }


    @GetMapping("/invoice/{cusid}/{proid}")
    public TblInvoice getById(@PathVariable("cusid") Integer cusId, @PathVariable("proid") Integer proId){
			return invoiceService.getById(cusId, proId);
    }

    @PostMapping("/invoice/add")
    public String addNew(@RequestBody TblInvoice invoice){
    	JSONObject result = new JSONObject();
    	try {
			invoiceService.add(invoice);
			result.put("code", "200");
			result.put("message", "add new Invoice success!");
			return result.toString();
		}catch (HienntException e) {
			logger.error("Add Invoice MyError:", e);
			result.put("code", e.getErrorCode());
			result.put("message", e.getMessage());
			return result.toString();
		} catch (Exception e) {
			logger.error("Add Invoice Error:", e);
			result.put("message", e);
			return result.toString();
		}
    }

    @PutMapping("/invoice/update")
    public String update(@RequestBody TblInvoice invoice){
    	JSONObject result = new JSONObject();
    	try {
			invoiceService.update(invoice);
			result.put("code", "200");
			result.put("message", "add new Invoice success!");
			return result.toString();
		}catch (HienntException e) {
			logger.error("Add Invoice MyError:", e);
			result.put("code", e.getErrorCode());
			result.put("message", e.getMessage());
			return result.toString();
		} catch (Exception e) {
			logger.error("Add Invoice Error:", e);
			result.put("message", e);
			return result.toString();
		}
    }

    @DeleteMapping("/invoice/delete/{cusid}/{proid}")
    public String delete(@PathVariable("cusid") Integer cusId, @PathVariable("proid") Integer proId){
    	JSONObject result = new JSONObject();
		try {
			invoiceService.delete(cusId, proId);
			result.put("code", "200");
			result.put("message", "add new Invoice success!");
			return result.toString();
		}catch (HienntException e) {
			logger.error("Add Invoice MyError:", e);
			result.put("code", e.getErrorCode());
			result.put("message", e.getMessage());
			return result.toString();
		} catch (Exception e) {
			logger.error("Add Invoice Error:", e);
			result.put("message", e);
			return result.toString();
		}
    }
    
    @GetMapping("/invoice/getby/")
    public List<TblInvoice> searchByCusName(@Param("cusName") String cusName) {
    	return invoiceService.searchByCusName(cusName);
    }
}
