import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {  HttpModule } from '@angular/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { CustomerService } from './customer/customer.service';
import { InvoiceService } from './invoices/invoice.service';
import { ProductService } from './product/product.service';
import { AppComponent } from './app.component';
import { CustomerComponent } from './customer/customer.component';
import { InvoicesComponent } from './invoices/invoices.component';
import { ProductComponent } from './product/product.component';
import { AppRoutingModule } from './/app-routing.module';
import { CustomerModule } from './customer/customer.module';
import {HeaderComponent} from './header/header';
import {ProductModule} from './product/product.module';
import { DetailsInvoiceComponent } from './invoices/details-invoice/details-invoice.component';
import {InvoiceModule} from './invoices/invoice.module';


@NgModule({
  declarations: [
    AppComponent,
    CustomerComponent,
    InvoicesComponent,
    ProductComponent,
    HeaderComponent,
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    CustomerModule,
    ProductModule,
    InvoiceModule
  ],
  providers: [CustomerService, InvoiceService, ProductService],
  bootstrap: [AppComponent]
})
export class AppModule { }
