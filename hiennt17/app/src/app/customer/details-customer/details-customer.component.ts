import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { CustomerService } from '../customer.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-details-customer',
  templateUrl: './details-customer.component.html',
  styleUrls: ['./details-customer.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DetailsCustomerComponent implements OnInit {
  id = +this.route.snapshot.paramMap.get('id');
  customernew;
  customerData: FormGroup;
  constructor(
    private route: ActivatedRoute,
    private service: CustomerService,
    private fb: FormBuilder,
    private router: Router
  ) { }

  ngOnInit() {
    this.getById();
  }
  creatCustomerData() {
    this.customerData = this.fb.group({
      cusId: this.customernew.cusId,
      cusName: [this.customernew.cusName, [Validators.required, Validators.minLength(2), Validators.maxLength(10) ] ],
      cusAddress: this.customernew.cusAddress,
      cusCity: this.customernew.cusCity,
      cusState: this.customernew.cusState,
      cusZip: [this.customernew.cusZip,
        [Validators.required, Validators.pattern('[0-9]'), Validators.minLength(9), Validators.maxLength(12)]],
      cusPhone: [this.customernew.cusPhone,
        [Validators.required, Validators.pattern('(\\+84|0)\\d{9,10}'), Validators.minLength(10), Validators.maxLength(11)]]
    });
  }
  getById() {
    this.service.getById(this.id).then(res => {
      this.customernew = res;
      this.creatCustomerData();
    });
  }
  onSubmit() {
     this.service.update(this.customerData.value).then(res => {
       this.back();
     });
  }
  back() {
    this.router.navigate(['/customer']);
  }
}
