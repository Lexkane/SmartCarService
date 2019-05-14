import { Component, OnInit } from '@angular/core';
import { from } from 'rxjs';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import {TradesinService} from './tradesin.service';
import {TradeIn} from './tradeIIn';
import {ActivatedRoute, Router} from "@angular/router";
@Component({
  selector: 'app-tradesin',
  templateUrl: './tradesin.component.html',
  styleUrls: ['./tradesin.component.scss']
})
export class TradesinComponent implements OnInit {

  allTradeIn:TradeIn[];
  username:String;

  constructor(private tradesinService:TradesinService,private tokenStorage: TokenStorageService,private route: ActivatedRoute,private router: Router) { }

  ngOnInit() {
     this.username=this.tokenStorage.getUsername();
     this.tradesinService.getAllTradeInByDealer(this.username).subscribe(data=>this.allTradeIn=data)
  }

  deleteTradeIn(id:number){
     this.tradesinService.deleteTradeIn(id).subscribe();
     this.router.navigate(['/ui/dealersTradeIn']);
  }

  SuccessTradeIn(id:number){
     this.tradesinService.successTradeIn(id).subscribe();
    this.router.navigate(['/ui/dealersTradeIn']);

  }

}
