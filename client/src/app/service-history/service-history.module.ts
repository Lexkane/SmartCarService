import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';

import {MDBBootstrapModule} from 'angular-bootstrap-md';
import {ServiceHistoryComponent} from "./service-history.component";
import {PdfComponent} from "./pdf/pdf.component";
import { ReportComponent } from './vi-report/report.component';



@NgModule({
  declarations: [
    ServiceHistoryComponent,
    PdfComponent,
    ReportComponent
  ],
  imports: [
    CommonModule,
    MDBBootstrapModule.forRoot(),
    HttpClientModule,
    BrowserModule,
  ],
  bootstrap: [ServiceHistoryComponent],
  exports: [
    ServiceHistoryComponent
  ]
})
export class ServiceHistoryModule {
}


