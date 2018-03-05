import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {InvoicesComponent} from './invoices.component';
import {DetailsInvoiceComponent} from './details-invoice/details-invoice.component';

const invoiceRoutes: Routes = [
  { path: 'invoice', redirectTo: '/superinvoices' },
  { path: 'inv/:cusId/:proId', redirectTo: '/superinv/:cusId/:proId' },
  { path: 'superinvoices',  component: InvoicesComponent },
  { path: 'superinv/:cusId/:proId', component: DetailsInvoiceComponent }
];
@NgModule({
  imports: [
    RouterModule.forChild(invoiceRoutes)
  ],
  declarations: [],
  exports: [
    RouterModule
  ]
})
export class InvoiceRoutingModule { }
