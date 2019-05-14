import { Component, OnInit } from '@angular/core';
import {Dealer} from 'src/app/dealer/dealer';
import {DealersService} from './dealers.service';
import {TokenStorageService} from '../auth/token-storage.service';
import {TechserviceService} from '../techservice/techservice.service';
import {Techservice} from '../techservice/techservice';
import{Apply} from './apply';
import {Observable} from "rxjs";
@Component({
  selector: 'app-dealers',
  templateUrl: './dealers.component.html',
  styleUrls: ['./dealers.component.scss']
})
export class DealersComponent implements OnInit {
  techservice: Techservice;
  allDealers:Dealer[];
  constructor(private dealersService: DealersService,private tokenStorage: TokenStorageService,private techserviceService: TechserviceService) { }

  ngOnInit() {
    this.getTechserviceByCurrentUser();

    this.dealersService.getAllDealers().
    subscribe(data => this.allDealers=data);

  }
  getTechserviceByCurrentUser() {
    return new Promise((resolve, reject) => {
      this.techserviceService.getTechnicalServiceByUsername(this.tokenStorage.getUsername())
        .subscribe(data => {
          if (data != null) {
            this.techservice = data;
          }
          resolve();
        });
    });
  }


  dealerSto(edr:String){

    this.dealersService.applyToDealer(new Apply(edr,this.techservice.stoId)).subscribe();
  }

}
