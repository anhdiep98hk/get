import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '../product.service';
import {FormBuilder, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-detail-product',
  templateUrl: './detail-product.component.html',
  styleUrls: ['./detail-product.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DetailProductComponent implements OnInit {
  id = +this.route.snapshot.paramMap.get('id');
  productDetail;
  productData: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private service: ProductService,
    private fb: FormBuilder,
    private router: Router
  ) { }

  ngOnInit() {
    this.getById();
  }
  getById() {
    this.service.getById(this.id).then(res => {
      this.productDetail = res;
      this.createProductData();
    });
  }
  createProductData() {
    this.productData = this.fb.group({
      proId: this.productDetail.proId,
      proName: this.productDetail.proName,
      proUnitPrice: this.productDetail.proUnitPrice
    });
  }
  back() {
    this.router.navigate(['/product']);
  }
  onSubmit() {
    this.service.update(this.productData.value).then(res => {
      this.back();
    });
  }
}
