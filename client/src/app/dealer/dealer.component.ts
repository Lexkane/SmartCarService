import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import{Dealer} from './dealer';
import { from } from 'rxjs';
import { TokenStorageService } from '../auth/token-storage.service';
import {DealerService} from './dealer.service';
@Component({
  selector: 'app-dealer',
  templateUrl: './dealer.component.html',
  styleUrls: ['./dealer.component.scss'],
})
export class DealerComponent implements OnInit {

   error: ErrorEvent;
   username: String;
   // mydealertmp:Dealer={dealerName:'',dealerAddress:'',dealerEdr:'',dealerEmail:''};
  // mydealertmp:Dealer;
  // mydealer=this.mydealertmp;
  mydealer:Dealer={dealerName:'',dealerAddress:'',dealerEdr:'',dealerEmail:''};
  name:String=this.mydealer.dealerName;
   constructor(private dealerService: DealerService,private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    this.username = this.tokenStorage.getUsername();
    this.dealerService.getDealer(this.username).
    subscribe(data => this.mydealer = data);

   }
  createDealer(){
    this.dealerService.createDealer(this.mydealer,this.username).subscribe(data => {}, error => this.error = error);
  }

  editDealer(){
    this.dealerService.editDealer(this.mydealer).subscribe();
  }

}
