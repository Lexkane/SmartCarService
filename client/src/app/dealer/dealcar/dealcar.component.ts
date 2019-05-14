import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { OwnerCar } from 'src/app/car-profile/owner-car';
import{CarProfileService} from 'src/app/car-profile/car-profile.service';
import{DealcarService} from'./dealcar.service';
import { from } from 'rxjs';
import { Car } from 'src/app/cars/car';
@Component({
  selector: 'app-dealcar',
  templateUrl: './dealcar.component.html',
  styleUrls: ['./dealcar.component.scss']
})
export class DealcarComponent implements OnInit {
  years =  ["2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997"];

  username: String;
  car:any = {};

  constructor(private tokenStorage: TokenStorageService,private dealerCarService:DealcarService ) { }

  ngOnInit() {
    this.username = this.tokenStorage.getUsername();
  }

  createCar() {
     this.dealerCarService.createCar(this.username,this.car).subscribe();
     this.car={};
  }

}
