import 'rxjs/add/operator/switchMap';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { CustomerService } from './customer.service';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {Customer} from '../model/customer';
import {FormControl} from '@angular/forms';
import {log} from 'util';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class CustomerComponent implements OnInit {
  checkAdd = false;
  customers$: Observable<Customer[]>;
  customers;
  searchBy= new FormControl();
  private selectedId: number;

  constructor(
    private service: CustomerService,
    private route: ActivatedRoute
  ) {
    // this.searchBy.valueChanges.subscribe(val => {
    //   val === '' ? this.getAll() : this.service.getName(val).then( res => this.customers = res);
    // });
  }

  getAll(): void {
    this.service.getAll().then(cus => {this.customers = cus; console.log('a'); } );
  }
  ngOnInit() {
    this.getAll();
    this.customers$ = this.route.paramMap
      .switchMap((params: ParamMap) => {
        // (+) before `params.get()` turns the string into a number
        this.selectedId = +params.get('id');
        return this.service.getById(+params.get('id'));
      });
    this.searchBy.valueChanges.subscribe(val => {
      val === '' ? this.getAll() : this.service.getName(val)
        .then( res => this.customers = res);
    });
  }

  onCheck() {
    this.checkAdd = !this.checkAdd;
  }

  onSubmit(form) {
    this.service.create(form).then(data => {
      this.getAll();
      this.onCheck();
    });
  }

  delete(id: number): void {
    this.service.delete(id).then(() => this.customers = this.customers
      .filter(c => c.cusId !== id));
  }

  contactTrackByFn(item) {
    return item.cusId;
  }

}
