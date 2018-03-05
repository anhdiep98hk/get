package com.hiennt.hienntbusiness.controller;

import com.hiennt.hienntbusiness.MyExcetion.HienntException;
import com.hiennt.hienntbusiness.entity.TblProduct;
import com.hiennt.hienntbusiness.service.IProductService;

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
public class ProductController {
    @Autowired
    private IProductService productService;
    
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    
    @GetMapping("/product/all")
    public List<TblProduct> getAll(){
        List<TblProduct> list= productService.getAll();
        return list;
    }

    @GetMapping("/product/getbyid/{id}")
    public TblProduct getById(@PathVariable("id") Integer id){
        TblProduct product = productService.getById(id);
        return product;
    }

    @PostMapping("/product/add")
    public String addNew(@RequestBody TblProduct product){
        JSONObject result = new JSONObject();
        try {
			productService.add(product);
			result.put("code", "200");
			result.put("message", "add new Product success!");
			return result.toString();
		} catch (HienntException e) {
			logger.error("Add Product MyError:", e);
			result.put("code", e.getErrorCode());
			result.put("message", e.getMessage());
			return result.toString();
		} catch (Exception e) {
			logger.error("Add Product Error:", e);
			result.put("message", e.getMessage());
			return result.toString();
		}
    }

    @PutMapping("/product/update")
    public String update(@RequestBody TblProduct product){
    	JSONObject result = new JSONObject();
        try {
			productService.update(product);
			result.put("code", "200");
			result.put("message", "update Product success!");
			return result.toString();
		}catch (HienntException e) {
			logger.error("Update Product MyError:", e);
			result.put("code", e.getErrorCode());
			result.put("message", e.getMessage());
			return result.toString();
		} catch (Exception e) {
			logger.error("Update Product Error:", e);
			result.put("message", e);
			return result.toString();
		}
    }

    @DeleteMapping("/product/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
    	JSONObject result = new JSONObject();
    	try {
			productService.delete(id);
			result.put("code", "200");
			result.put("message", "delete Product success!");
			return result.toString();
		}catch (HienntException e) {
			logger.error("delete Product MyError:", e);
			result.put("code", e.getErrorCode());
			result.put("message", e.getMessage());
			return result.toString();
		} catch (Exception e) {
			logger.error("delete Product Error:", e);
			result.put("code", "502");
			result.put("message", e);
			return result.toString();
		}
    }
    
    @GetMapping("/product/getbyname/")
    public List<TblProduct> getName (@Param("proName") String proName){
    	return productService.getByName(proName);
    }
    
    @GetMapping("/product/getbyprice/")
    public List<TblProduct> getPrice (@Param("proPrice") Float price){
    	return productService.getByPrice(price);
    }
}
