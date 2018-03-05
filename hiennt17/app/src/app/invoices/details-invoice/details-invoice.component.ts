import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {InvoiceService} from '../invoice.service';
import {CustomerService} from '../../customer/customer.service';
import {ProductService} from '../../product/product.service';
import {FormBuilder, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-details-invoice',
  templateUrl: './details-invoice.component.html',
  styleUrls: ['./details-invoice.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DetailsInvoiceComponent implements OnInit {
  cusId = +this.route.snapshot.paramMap.get('cusId');
  proId = +this.route.snapshot.paramMap.get('proId');
  invoices;
  invoiceForm: FormGroup;
  customers;
  products;
  constructor(
    private route: ActivatedRoute,
    private service: InvoiceService,
    private serviceCus: CustomerService,
    private servicePro: ProductService,
    private router: Router,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    console.log(this.cusId);
    console.log(this.proId);
    this.getById();
  }

  createInvoiceData() {
    this.invoiceForm = this.fb.group({
      id: this.fb.group({
        cusId: this.invoices.tblCustomer.cusName,
        proId: this.invoices.tblProduct.proName
      }),
      numbers: this.invoices.numbers,
      totalPayment: this.invoices.totalPayment
    });
  }

  getById() {
    this.service.getById(this.cusId, this.proId).then(res => {
      this.invoices = res;
      this.createInvoiceData();
    });
  }

  onSubmit() {
    this.invoiceForm.patchValue({
      id: {
        cusId: this.cusId,
        proId: this.proId
      }
    });
    console.log(this.invoiceForm.value);
    this.service.update(this.invoiceForm.value).then(data => {
      this.back();
    });
  }
  back() {
    this.router.navigate(['/invoice']);
  }
}
