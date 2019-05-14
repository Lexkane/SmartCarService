import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-progresbar',
  templateUrl: './progresbar.component.html',
  styleUrls: ['./progresbar.component.scss']
})
export class ProgresbarComponent implements OnInit {

  active: boolean = true;
  done: boolean = false;
  constructor() { }

  ngOnInit() {
    this.classActivateToDo();
    this.classActivateInProgress();
    this.classActivateDone();
    this.classActivateFeedback();
  }

  classActivateFeedback(): string{
    if(this.active == true && this.done == true){
      return "md-step active done";
    } else if (this.active == true) {
      return "md-step active";
    } else {
      return "md-step";
    }
  }

  classActivateDone(): string{
    if(this.active == true && this.done == true){
      return "md-step active done";
    } else if (this.active == true) {
      return "md-step active";
    } else {
      return "md-step";
    }
  }

  classActivateInProgress(): string{
    if(this.active == true && this.done == true){
      return "md-step active done";
    } else if (this.active == true) {
      return "md-step active";
    } else {
      return "md-step";
    }
  }

  classActivateToDo(): string{
    if(this.active == true && this.done == true){
      return "md-step active done";
    } else if (this.active == true) {
      return "md-step active";
    } else {
      return "md-step";
    }
  };

}
