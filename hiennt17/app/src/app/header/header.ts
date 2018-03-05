import { Component, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-header',
  template: `
    <div class="row">
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <ul class="nav navbar-nav ">
          <li><a routerLink="/customer" >Customer</a></li>
          <li><a routerLink="/invoice" >Invoice</a></li>
          <li><a routerLink="/product" >Product</a></li>
        </ul>
      </div>
    </nav>
    </div>
  `,
  styleUrls: ['./header.css'],
  encapsulation: ViewEncapsulation.None
})
export class HeaderComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
