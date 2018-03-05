import {InvoiceId} from './invoiceId';
import {Customer} from './customer';
import {Product} from './product';

export class Invoice {
    id: InvoiceId;
    customer: Customer;
    product: Product;
    numbers: number;
    issueDate: string;
    totalPayment: number;
}
