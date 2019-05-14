import { Component, OnInit, Input } from '@angular/core';
import { WorkerService } from '../techservice/worker/worker.service';
import { Worker } from '../techservice/worker/worker';

@Component({
  selector: 'app-workers-feedback',
  templateUrl: './workers-feedback.component.html',
  styleUrls: ['./workers-feedback.component.scss']
})
export class WorkersFeedbackComponent implements OnInit {

  @Input() workerId: number;
  rating: number;
  worker: Worker = new Worker();

  constructor(private workerService: WorkerService) { }

  ngOnInit() {
    console.log(this.workerId); 
    this.getWorker();
  }

  setRating(rating: number, event: Event) {
    //this.rating = rating;
    this.giveFeedback(rating);
    this.changeColor(event);
  }

  changeColor(event: Event) {
    this.clearColor(event);

    let number = Number.parseInt(event.srcElement.id);
    for(let i = 1; i <= number; i++) {
      let element = document.getElementById(i.toString());
      element.className += ' light';
    }
  }

  clearColor(event: Event) {
    for(let i = 1; i <= 5; i++) {
      let element = document.getElementById(i.toString());
      while(element.classList.contains('light')) {
        element.className += element.className.replace('light', '');
      }
    }
  }

  giveFeedback(rating: number) {
    this.workerService.setRatingByWorkerId(this.worker.id, rating)
      .subscribe();
  }

  getWorker() {
    this.workerService.getWorkerById(this.workerId)
      .subscribe(data => this.worker = data);
  }
}
