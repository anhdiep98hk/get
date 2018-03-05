import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerService } from './customer.service';
import { DetailsCustomerComponent } from './details-customer/details-customer.component';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    CustomerRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [DetailsCustomerComponent],
  providers: [ CustomerService ]
})
export class CustomerModule { }
