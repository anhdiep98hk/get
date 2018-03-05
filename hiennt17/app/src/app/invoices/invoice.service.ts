import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Invoice} from '../model/invoice';

@Injectable()
export class InvoiceService {
  private url = 'http://localhost:8080/api/invoice';
  private headers = new Headers({'Content-Type': 'application/json'});
  constructor(private http: Http) { }
  getAll() {
    const  urlapi = `${this.url}/all`;
    return this.http.get(urlapi).toPromise()
      .then(res => res.json());
  }
  getByCusId(cusId: number) {
    const  urlapi = `${this.url}/bycus/?cusId=${cusId}`;
    return this.http.get(urlapi).toPromise().then(res => res.json());
  }
  getByCusName(cusName: string) {
    const  urlapi = `${this.url}/getby/?cusName=${cusName}`;
    return this.http.get(urlapi).toPromise().then(res => res.json());
  }
  getById(cusid: number, proid: number) {
    const  urlapi = `${this.url}/${cusid}/${proid}`;
    return this.http.get(urlapi)
      .toPromise()
      .then(res => res.json());
  }
  delete(cusid: number, proid: number) {
    const urlapi = `${this.url}/delete/${cusid}/${proid}`;
    return this.http.delete(urlapi, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }
  update(invoice: Invoice) {
    const urlapi = `${this.url}/update`;
    return this.http.put(urlapi, JSON.stringify(invoice), { headers: this.headers })
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }
  create(invoice: Invoice) {
    const urlapi = `${this.url}/add`;
    return this.http.post(urlapi, JSON.stringify(invoice), { headers: this.headers })
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }
  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
