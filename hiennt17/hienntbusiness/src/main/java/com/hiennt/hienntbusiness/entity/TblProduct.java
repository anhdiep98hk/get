package com.hiennt.hienntbusiness.entity;
// Generated Nov 1, 2017 4:33:10 PM by Hibernate Tools 5.2.5.Final

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TblProduct generated by hbm2java
 */
@Entity
@Table(name = "tbl_product", catalog = "f13_nth_businessdb")
public class TblProduct implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer proId;
	private String proName;
	private Float proUnitPrice;
	private Set<TblInvoice> tblInvoices = new HashSet<TblInvoice>(0);

	public TblProduct() {
	}

	public TblProduct(String proName, Float proUnitPrice, Set<TblInvoice> tblInvoices) {
		this.proName = proName;
		this.proUnitPrice = proUnitPrice;
		this.tblInvoices = tblInvoices;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "pro_id", unique = true, nullable = false)
	public Integer getProId() {
		return this.proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	@Column(name = "pro_name", length = 100)
	public String getProName() {
		return this.proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	@Column(name = "pro_unit_price", precision = 12, scale = 0)
	public Float getProUnitPrice() {
		return this.proUnitPrice;
	}

	public void setProUnitPrice(Float proUnitPrice) {
		this.proUnitPrice = proUnitPrice;
	}

	@OneToMany(mappedBy = "tblProduct", cascade= CascadeType.ALL)
	@JsonIgnore
	public Set<TblInvoice> getTblInvoices() {
		return this.tblInvoices;
	}

	public void setTblInvoices(Set<TblInvoice> tblInvoices) {
		this.tblInvoices = tblInvoices;
	}

}