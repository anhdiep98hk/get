import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductRoutingModule } from './product-routing.module';
import { ReactiveFormsModule} from '@angular/forms';
import { ProductService} from './product.service';
import { DetailProductComponent  } from './detail-product/detail-product.component';

@NgModule({
  imports: [
    CommonModule,
    ProductRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [DetailProductComponent],
  providers: [ ProductService ]
})
export class ProductModule { }
