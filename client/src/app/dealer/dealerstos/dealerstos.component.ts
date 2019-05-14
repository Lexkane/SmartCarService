import { Component, OnInit } from '@angular/core';
import { from } from 'rxjs';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { DealerstosService } from './dealerstos.service';
import{Techservice} from 'src/app/techservice/techservice';

@Component({
  selector: 'app-dealerstos',
  templateUrl: './dealerstos.component.html',
  styleUrls: ['./dealerstos.component.scss']
})
export class DealerstosComponent implements OnInit {

  stos:Techservice[];
  private username: String;

  constructor(private dealerstosService:DealerstosService,private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    this.username=this.tokenStorage.getUsername();

    this.dealerstosService.getAllDealersSto(this.username)
    .subscribe(data=>this.stos=data);
    console.log("stos");
    console.log(this.stos);
  }
  deletefromdealer(stoId: number){
    console.log(stoId);
    this.dealerstosService.deleteFromDealer(stoId).subscribe();
  }
}
