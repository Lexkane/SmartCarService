import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Worker } from '../worker/worker';
import { WorkerService } from '../worker/worker.service';

@Component({
  selector: 'worker-card',
  templateUrl: './worker-card.component.html',
  styleUrls: ['./worker-card.component.scss']
})
export class WorkerCardComponent implements OnInit {

  @Input() worker: Worker;
  @Output() deleteWorkerEvent = new EventEmitter();

  constructor(private workerService: WorkerService) { }

  ngOnInit() {
  }

  deleteWorker() {
    console.log('trying to delete worker :' + this.worker.id);
    this.workerService.deleteWorkerById(this.worker.id)
      .subscribe(data => this.deleteWorkerEvent.next());
  }
}
