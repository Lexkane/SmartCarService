import { Component, OnInit } from '@angular/core';
import{Car} from 'src/app/cars/car';
import { from } from 'rxjs';
import{DealercarsService} from './dealercars.service';
import { CarsService } from 'src/app/cars/cars.service';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import {MatDialog} from "@angular/material";
import {AlertsComponent} from "../../alerts/alerts.component";
import {Techservice} from "../../techservice/techservice";
import {DealerstosService} from "../dealerstos/dealerstos.service";
@Component({
  selector: 'app-dealercars',
  templateUrl: './dealercars.component.html',
  styleUrls: ['./dealercars.component.scss']
})
export class DealercarsComponent implements OnInit {

  cars: Car[];
  private username: String;
  constructor(public dialog: MatDialog,private carsService: CarsService,private route: ActivatedRoute,private router: Router,private dealercarsService:DealercarsService,private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    this.username=this.tokenStorage.getUsername();
    this.dealercarsService.getAllDealerCars(this.username)
    .subscribe(data=>this.cars=data);


  }

 //  deleteCarById(id: number){
 //    this.carsService.deleteCarById(id).subscribe();
 //    this.router.navigate(['/ui/dealercars']);
 // }

  // goToCharts(car: Car){
  //  this.router.navigate(['/charts'],
  //   {queryParams: {
  //     carId: car.id,
  //     carVin: car.vin
  //   }}
  // );}
  goToCharts(carId: number){
    this.router.navigate(['/ui/charts'],
      {queryParams: {
        carId: carId
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
}
