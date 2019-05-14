import { Component, OnInit, Input } from '@angular/core';
import { Techservice } from '../techservice';
import { NumberValueAccessor } from '@angular/forms/src/directives';
import { WorkerService } from '../worker/worker.service';
import { Worker } from '../worker/worker';

@Component({
  selector: 'app-workers-list',
  templateUrl: './workers-list.component.html',
  styleUrls: ['./workers-list.component.scss']
})
export class WorkersListComponent implements OnInit {

  @Input() techserviceId: number;
  error: ErrorEvent;
  workers: Worker[] = [];
  
  constructor(private workerService: WorkerService) { }

  ngOnInit() {
    this.recieveWorkers();
  }

  recieveWorkers() {
    this.workerService.getAllWorkers(this.techserviceId)
          .subscribe(data => {
            this.workers = data;
            this.workers.forEach(worker => {
              this.workerService.getRatingByWorkerId(worker.id).subscribe(data => worker.rating = data ? data : 0);
            })}
          , error => this.error = error); 
  }
}
