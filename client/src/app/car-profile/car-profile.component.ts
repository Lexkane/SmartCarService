import { Component, OnInit } from '@angular/core';
import { OwnerCar } from './owner-car';
import { CarProfileService } from './car-profile.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { Router } from '@angular/router';
import {VechicleInspection} from './vechicle-inspection';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { FormGroup, FormControl } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { MatDialog } from '@angular/material';
import { InfoMassageComponent } from '../info-massage/info-massage.component';

@Component({
  selector: 'app-car-profile',
  templateUrl: './car-profile.component.html',
  styleUrls: ['./car-profile.component.scss']
})
export class CarProfileComponent implements OnInit {

  carProfile: any = {};
  carVechicleInspection: any = {};
  username: String;
  years =  ["2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997"];
  showCarProfile: boolean = true;
  showInspection: boolean = false;
  httpStatusCode: number;
  error: ErrorEvent;
  car: OwnerCar;
  carVin: String;
  carNumber: String;

  constructor(private carService: CarProfileService, private tokenStorage: TokenStorageService, private router: Router, private datePipe: DatePipe, public dialog: MatDialog) { }

  ngOnInit() {
    this.username = this.tokenStorage.getUsername();
  }

  onSubmit(){
    this.carService.addCar(this.carProfile, this.username)
    .subscribe(error => {
      this.httpStatusCode = error.status,
      error => this.error = error;
      this.carVin = this.carProfile.vin;

      if(this.httpStatusCode === 201){
        console.log(this.error);
        this.showCarProfile = false;
        this.showInspection = true;
      } else {
        console.log(this.error);
        this.showWarning();
     }
  });
  }

  showWarning(): void {
    const dialogRef = this.dialog.open(InfoMassageComponent, {
      height: '150px',
      width: '400px',
      
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }

  Confirm(){
    this.carVechicleInspection.dateOfInspection == "" ? "" : this.datePipe.transform(this.carVechicleInspection.dateOfInspection, 'yyyy-MM-dd')
    this.carService.createInspection(this.carVechicleInspection, this.carVin).subscribe();
    this.reloadPage();
  }

  reloadPage() {
    this.router.navigate(['ui/cars']);
  }

  closeProfile(){
  this.router.navigate(['ui/home']);
  }

}
