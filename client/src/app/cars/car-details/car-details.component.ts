import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Car } from '../car';
import { CarsService } from '../cars.service';
import { AlertService } from 'src/app/alerts/alert.service';
import { MatDialog } from '@angular/material';
import { AlertsComponent } from 'src/app/alerts/alerts.component';

@Component({
  selector: 'app-car-details',
  templateUrl: './car-details.component.html',
  styleUrls: ['./car-details.component.scss']
})
export class CarDetailsComponent implements OnInit {

  carId: Number;
  carBrand: String;
  carModel: String;
  carGY: String;
  carNumber: String;
  carVin: String;
  carPrice: String;
  carEnd_guarantee: String;

  constructor(private route: ActivatedRoute, private router: Router, private carsService: CarsService,  private alertService: AlertService,  public dialog: MatDialog) { }

  ngOnInit() {
    this.carId = Number(this.getFromRouterParams('carId'));
    
    this.carBrand = this.getFromRouterParams('carBrand');
    this.carModel = this.getFromRouterParams('carModel');
    this.carGY = this.getFromRouterParams('carGY');
    this.carNumber = this.getFromRouterParams('carNumber');
    this.carVin = this.getFromRouterParams('carVin');
    this.carPrice = this.getFromRouterParams('carPrice');
    this.carEnd_guarantee = this.getFromRouterParams('carEnd_guarantee');

  }

  private getFromRouterParams(param: string) {
    return this.route.snapshot.queryParamMap.get(param);
  }

  deleteCarById(id: Number): void {
    const dialogRef = this.dialog.open(AlertsComponent, {
      height: '150px',
      width: '400px',
      data: id
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log();
      });
  }

  applyToSTO(carId: Number){
    this.router.navigate(['/ui/booking'],
    {queryParams: {
      carId: carId,
    }}
   );
  }

  applyToTradeIn(vin: String){
    console.log(vin);
    this.router.navigate(['ui/tradeIn', vin]);
  }

  goToCharts(carId: number){
    this.router.navigate(['ui/charts'],
      {queryParams: {
        carId: this.carId,
      }}
    );
  }

  history(carId: Number){
    this.router.navigate(['ui/history'],
      {queryParams: {
        carId: carId,
      }}
    );
  }

  closeProfile(){
    this.router.navigate(['ui/home'])
  }

}
