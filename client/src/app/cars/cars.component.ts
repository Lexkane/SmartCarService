import { Component, OnInit, Input } from '@angular/core';
import {Car} from './car';
import { CarsService } from './cars.service';
import { User } from '../users/user';
import { TokenStorageService } from '../auth/token-storage.service';
import { UsersService } from '../users/users.service';
import { ChartService } from '../chart-page/charts/chart/chart.service';
import { identifierModuleUrl } from '@angular/compiler';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from '../alerts/alert.service';
import { MatDialog } from '@angular/material';
import { AlertsComponent } from '../alerts/alerts.component';
import { ChartData } from '../chart-page/charts/chart/chart-data';
import { JsonPipe } from '@angular/common';
import { InfoMassageComponent } from '../info-massage/info-massage.component';

@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.scss'],
  providers: [ChartService]
})
export class CarsComponent implements OnInit {

  cars: Car[];
  username: String;
  user: User;
  car: Car;
  showCards: boolean = false;
  showProfile: boolean = false;
  showProposal: boolean = false;
  userId: Number;
  httpStatusCode: number;
  error: ErrorEvent;

  constructor(private carsService: CarsService, private userService: UsersService, private tokenStorage: TokenStorageService, private route: ActivatedRoute, private router: Router, private alertService: AlertService,  public dialog: MatDialog, private chartService: ChartService) { }

  ngOnInit(){
  this.carsService.getOwnerCarsByUsername(this.tokenStorage.getUsername())
  .subscribe(data => {
    this.cars = data,
    error => this.error = error;

    if(this.cars.length === 1){
      this.showProfile = true;  
    } else if(this.cars.length > 1){
      this.showCards = true;
    } else {
      this.showProposal = true;
    }
    });
  }

  applyToSTO(id: number){
    this.router.navigate(['/ui/booking', id]);
  }

  applyToTradeIn(vin: String){
    console.log(vin);
    this.router.navigate(['ui/tradeIn',vin]);
  }

  goToCharts(car: Car){
      this.router.navigate(['/ui/charts'],
        {queryParams: {
          carId: car.id
        }}
      );
  }

  history(carId: number){
    this.router.navigate(['/ui/history'],
      {queryParams: {
        carId: carId
      }}
    );
  }

  deleteCarById(id: Number): void {
    const dialogRef = this.dialog.open(AlertsComponent, {
      height: '150px',
      width: '400px',
      data: id
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }

  openProfile(car: Car){
    this.router.navigate(['ui/carprofile'],
      {queryParams: {
        carId: car.id,
        carBrand: car.brand,
        carModel: car.model,
        carGY: car.graduation_year,
        carNumber: car.number,
        carVin: car.vin,
        carPrice:car.price,
        carEnd_guarantee: car.end_guarantee
      }}
    );
  }

  getCarById (id: number){
    this.carsService.getCarById(id).subscribe(data => this.car = data);
  }

  getCarByNumber(number: String){
    this.carsService.getCarByNumber(number).subscribe(data => this.car = data);
  }

  getCarByVin(vin: String){
      this.carsService.getCarByVin(vin).subscribe(data => this.car = data);
  }

  reloadPage() {
    this.router.navigate(['ui/cars']);
  }

  closeProfile(){
  this.router.navigate(['ui/home']);
  }
    
}
