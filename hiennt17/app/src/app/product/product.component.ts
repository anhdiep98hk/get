import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ProductService} from './product.service';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {Product} from '../model/product';
import {FormControl} from '@angular/forms';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ProductComponent implements OnInit {
  products;
  products$: Observable<Product[]>;
  searchBy= new FormControl();
  private selectedId: number;
  checkAdd = false;

  constructor(
    private service: ProductService,
    private route: ActivatedRoute
  ) { }

  getAll(): void {
    this.service.getAll().then(pro => this.products = pro);
  }

  ngOnInit() {
    this.getAll();
    this.products$ = this.route.paramMap
      .switchMap((params: ParamMap) => {
        this.selectedId = +params.get('id');
        return this.service.getById(+params.get('id'));
      });
    this.searchBy.valueChanges.subscribe(val => {
      val === '' ? this.getAll() : this.service.getByName(val)
        .then( res => this.products = res);
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

  contactTrackByFn(item) {
    return item.proId;
  }

  delete(id: number): void {
    this.service.delete(id).then (() => this.products = this.products.filter(p => p.proId !== id));
  }
}
