import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';

import {MDBBootstrapModule} from 'angular-bootstrap-md';
import {NgCircleProgressModule} from 'ng-circle-progress';

import {ChartPageComponent} from './chart-page.component';
import {LineChartComponent} from './charts/line-chart/line-chart.component';
import {BarChartComponent} from './charts/bar-chart/bar-chart.component';
import {ProgressChartComponent} from './charts/progress-chart/progress-chart.component';
import {ChartComponent} from './charts/chart/chart.component';
import {LittleBtnComponent} from './little-btn/little-btn.component';

@NgModule({
  declarations: [
    ChartPageComponent,
    LineChartComponent,
    BarChartComponent,
    ProgressChartComponent,
    ChartComponent,
    LittleBtnComponent,
  ],
  imports: [
    CommonModule,
    MDBBootstrapModule.forRoot(),
    HttpClientModule,
    BrowserModule,

    // Specify ng-circle-progress as an import
    NgCircleProgressModule.forRoot({
      // set defaults here
      radius: 100,
      outerStrokeWidth: 16,
      innerStrokeWidth: 8,
      outerStrokeColor: "#78C000",
      innerStrokeColor: "#C7E596",
      animationDuration: 300,
    }),
  ],
  bootstrap: [ChartPageComponent],
  exports: [
    ChartPageComponent,
    LineChartComponent,
    BarChartComponent,
    ProgressChartComponent
  ]
})
export class ChartPageModule {
}


