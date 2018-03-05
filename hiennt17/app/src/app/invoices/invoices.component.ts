import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Invoice} from '../model/invoice';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {InvoiceService} from './invoice.service';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {InvoiceId} from '../model/invoiceId';
import {CustomerService} from '../customer/customer.service';
import {ProductService} from '../product/product.service';

@Component({
  selector: 'app-invoices',
  templateUrl: './invoices.component.html',
  styleUrls: ['./invoices.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class InvoicesComponent implements OnInit {
  checkAdd = false;
  invoice$: Observable<Invoice[]>;
  invoice: Invoice;
  searchBy= new FormControl();
  customers = this.customers;
  products= this.products;
  invoiceForm: FormGroup;
  private selectedId: InvoiceId;
  constructor(
    private service: InvoiceService,
    private route: ActivatedRoute,
    private serviceCus: CustomerService,
    private servicePro: ProductService,
    private fb: FormBuilder
  ) {
  }

  ngOnInit() {
    this.getAll();
    this.getAllCus();
    this.getAllPro();
    this.createFormInvoice();
    this.invoice$ = this.route.paramMap
      .switchMap((params: ParamMap) => {
        this.selectedId.cusId = +params.get('cusId');
        this.selectedId.proId = +params.get('proId');
        return this.service.getById(this.selectedId.cusId, this.selectedId.proId);
      });
    this.searchBy.valueChanges.subscribe(val => {
      val === '' ? this.getAll() : this.service.getByCusName(val)
         .then(res => this.invoice = res);
    });
  }

  createFormInvoice() {
    this.invoiceForm = this.fb.group({
      id: this.fb.group({
        cusId: '',
        proId: '',
      }),
      numbers: '',
      issueDate: '',
      totalPayment: ''
    });
  }

  getAllPro(): void {
    this.servicePro.getAll().then(pro => this.products = pro);
  }

  getAllCus(): void {
    this.serviceCus.getAll().then(cus => this.customers = cus);
  }

  getAll(): void {
    this.service.getAll().then( inv => {this.invoice = inv; });
  }

  onCheck() {
    this.checkAdd = !this.checkAdd;
  }

  onSubmit() {
    this.invoiceForm.patchValue({
      issueDate: +new Date(),
    });
    console.log(this.invoiceForm.value);
    this.service.create(this.invoiceForm.value).then(data => {
      this.getAll();
      this.onCheck();
    });
  }

  delete(cusId: number, proId: number): void {
    this.service.delete(cusId, proId).then(data => {
      this.getAll();
    });
  }

  contactTrackByFn(item) {
    return item.cusId;
  }
}
