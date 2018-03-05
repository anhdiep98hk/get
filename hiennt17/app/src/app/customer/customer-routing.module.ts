import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerComponent } from './customer.component';
import { DetailsCustomerComponent } from './details-customer/details-customer.component';

const customerRoutes: Routes = [
  { path: 'customer', redirectTo: '/supercustomers' },
  { path: 'cus/:id', redirectTo: '/supercus/:id' },
  { path: 'supercustomers',  component: CustomerComponent },
  { path: 'supercus/:id', component: DetailsCustomerComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(customerRoutes)
  ],
  declarations: [],
  exports: [
    RouterModule
  ]
})
export class CustomerRoutingModule { }
