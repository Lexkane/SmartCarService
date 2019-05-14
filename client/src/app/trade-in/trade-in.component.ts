import { Component, OnInit,Input } from '@angular/core';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { ActivatedRoute, Router } from '@angular/router';
import{CarsService} from 'src/app/cars/cars.service';
import {Car } from 'src/app/cars/car';
import{Dealer} from 'src/app/dealer/dealer';
import { from, Observable } from 'rxjs';
import { TradeInService } from './trade-in.service';
import {TradeIn} from './tradein';
import {Globals} from "../globals";
import {FormGroup, FormControl, Validators,FormsModule } from "@angular/forms";
import {Message} from "./message";
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { ToastrService } from 'ngx-toastr';
import {DealerService} from "../dealer/dealer.service";



@Component({
  selector: 'app-trade-in',
  templateUrl: './trade-in.component.html',
  styleUrls: ['./trade-in.component.scss']

})
export class TradeInComponent implements OnInit {
  serverUrl = "http://localhost:9501/socket";

  username: String;
  mycar: Car;
  dealers: Dealer[] = [];
  allCars: Car[] = [];

  @Input()
  vin: string;

  isCustomSocketOpened = true;


  toId: String;
  msg: String;
  showchat:boolean=false;
  dealer: Dealer = {dealerName: '', dealerAddress: '', dealerEdr: '', dealerEmail: ''};
  mydealer=this.dealer;
  private stompClient;

  messages: Message[] = [];

  constructor(private dealerService: DealerService, private toastr: ToastrService, private tradeInService: TradeInService, private carsService: CarsService, private tokenStorage: TokenStorageService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.vin = params["vin"];
    })

    this.carsService.getCarByVin(this.vin).subscribe(data => this.mycar = data);

    this.tradeInService.getAllCars().subscribe(data => this.allCars = data);

    this.tradeInService.getAllDealers().subscribe(data => this.dealers = data);

    this.username = this.tokenStorage.getUsername();


    // +
    this.initializeWebSocketConnection();

  }

  sendMessageUsingRest() {

    let message: Message = {message: this.msg, fromId: this.tokenStorage.getUsername(), toId: this.toId};
    this.tradeInService.post(message).subscribe(res => {
      console.log(res);
    })
    this.msg="";

  }

  sendMessageUsingSocket() {

    let message: Message = {message: this.msg, fromId: this.tokenStorage.getUsername(), toId: this.toId};
    this.stompClient.send("/socket-subscriber/send/message", {}, JSON.stringify(message));

  }

  initializeWebSocketConnection() {
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function (frame) {
      // that.openGlobalSocket();
      that.openSocket();
    });

  }

  // openGlobalSocket() {
  //   this.stompClient.subscribe("/socket-publisher" , (message) => {
  //     this.handleResult(message);
  //   });
  //
  // }

  openSocket() {
    // if (this.isLoaded) {
    //   this.isCustomSocketOpened = true;
    this.stompClient.subscribe("/socket-publisher/" + this.tokenStorage.getUsername(), (message) => {
      this.handleResult(message);
    });
// }
  }

  handleResult(message) {
    // if (message.body) {
    let messageResult: Message = JSON.parse(message.body);
    console.log(messageResult);
    this.messages.push(messageResult)
    this.toastr.success("new message recieved", null, {
        'timeOut': 3000
      }
    )
    // }
  }


  getFromRouterParams(param: string) {
    return this.route.snapshot.queryParamMap.get(param);
  }

  getCars(edr: string) {
    return this.tradeInService.getAllCarsByDealerEdr(edr).subscribe();
  }

  sendTradeIn(newCarvin: String, UsedCarvin: String) {
    return this.tradeInService.sendTradeIn(newCarvin, UsedCarvin).subscribe();
  }

  startmessage(vin: String) {

    this.dealerService.getDealerByCarVin(vin).subscribe(data => {this.mydealer.dealerEdr = data.dealerEdr,console.log(this.mydealer.dealerEdr),this.toId=this.mydealer.dealerEdr});
this.showchat=true;
    console.log(this.mydealer.dealerEdr);

  }


}
