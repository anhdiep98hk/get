import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductComponent } from './product.component';
import { DetailProductComponent} from './detail-product/detail-product.component';

const productRoutes: Routes = [
  { path: 'product', redirectTo: '/superproducts' },
  { path: 'pro/:id', redirectTo: '/superpro/:id' },
  { path: 'superproducts',  component: ProductComponent },
  { path: 'superpro/:id', component: DetailProductComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(productRoutes)
  ],
  declarations: [],
  exports: [RouterModule]
})
export class ProductRoutingModule { }
