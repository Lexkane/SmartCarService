import { Component, OnInit } from '@angular/core';
import {Message} from "../trade-in/message";
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { ToastrService } from 'ngx-toastr';
import {DealerService} from "../dealer/dealer.service";
import {TokenStorageService} from "../auth/token-storage.service";
import {ActivatedRoute, Router} from "@angular/router";
import {TradeInService} from "../trade-in/trade-in.service";
import {Dealer} from "../dealer/dealer";


@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {

  serverUrl = "http://localhost:9501/socket";
  private stompClient;
  isCustomSocketOpened = true;
  msg: String;
  messages: Message[] = [];
  toId: String;
  mydealer:Dealer;
  constructor(private dealerService : DealerService,private tradeInService: TradeInService,private toastr: ToastrService,private tokenStorage: TokenStorageService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.dealerService.getDealer(this.tokenStorage.getUsername()).
    subscribe(data => this.mydealer = data);

    this.initializeWebSocketConnection();

  }
  sendMessageUsingRest() {

    let message: Message = {message: this.msg, fromId: this.mydealer.dealerEdr, toId: this.toId};
    this.tradeInService.post(message).subscribe(res => {
      console.log(res);
    })
    this.msg="";

  }

//   sendMessageUsingSocket() {
//
//     let message: Message = {message: this.msg, fromId: this.mydealer.dealerEdr, toId: this.toId};
//     this.stompClient.send("/socket-subscriber/send/message", {}, JSON.stringify(message));
// this.msg="";
//   }

  initializeWebSocketConnection() {
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function (frame) {
      that.openSocket();
    });

  }

  openSocket() {
    this.stompClient.subscribe("/socket-publisher/" + this.mydealer.dealerEdr, (message) => {
      this.handleResult(message);
    });

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

  }

  select(toid:String){
    this.toId=toid;
    console.log(toid);
  }
}
