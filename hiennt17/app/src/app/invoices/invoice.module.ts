import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DetailsInvoiceComponent } from './details-invoice/details-invoice.component';
import {InvoiceService} from './invoice.service';
import {ReactiveFormsModule} from '@angular/forms';
import {InvoiceRoutingModule} from './invoice-routing.module';

@NgModule({
  imports: [
    CommonModule,
    InvoiceRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [DetailsInvoiceComponent],
  providers: [ InvoiceService ]
})
export class InvoiceModule { }
