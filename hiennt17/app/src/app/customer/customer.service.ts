import { Injectable } from '@angular/core';
import { Http, Headers, Response} from '@angular/http';
import { Customer } from '../model/customer';

@Injectable()
export class CustomerService {
  private url = 'http://localhost:8080/api/customer';
  private headers = new Headers({ 'Content-Type': 'application/json'});

  constructor(private http: Http) { }

  getAll() {
    const urlapi = `${this.url}/all`;
    return this.http.get(urlapi).toPromise()
    .then(res => res.json());
  }

  getName(nameCus: string) {
    const urlapi = `${this.url}/getname/?nameCus=${nameCus}`;
    return this.http.get(urlapi).toPromise()
    .then(res => res.json());
  }
  getById(id: number) {
    const  urlapi = `${this.url}/${id}`;
    return this.http.get(urlapi).toPromise()
      .then(res => res.json());
  }

  create(customer: Customer) {
    const urlapi = `${this.url}/add`;
    return this.http.post(urlapi, JSON.stringify(customer), { headers: this.headers })
    .toPromise()
    .catch(this.handleError);
  }

  update(customer: Customer) {
    const urlapi = `${this.url}/update`;
    return this.http.put(urlapi, JSON.stringify(customer), { headers: this.headers })
      .toPromise()
      .then(res => res.json())
      .catch(err => console.log(err));
  }

  delete(id: number) {
    const urlapi = `${this.url}/delete/${id}`;
    return this.http.delete(urlapi, {headers: this.headers})
    .toPromise()
    .then(() => null)
    .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
