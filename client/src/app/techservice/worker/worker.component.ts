import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { WorkerService } from './worker.service';
import { AuthService } from 'src/app/auth/auth.service';
import { SignUpInfo } from 'src/app/auth/signup-info';
import { SkillComponent } from 'src/app/techservice/worker/skill/skill.component';
import { Worker } from './worker';

@Component({
  selector: 'app-worker',
  templateUrl: './worker.component.html',
  styleUrls: ['./worker.component.scss']
})
export class WorkerComponent implements OnInit {

  error: ErrorEvent;
  workers: Worker[] = [];

  @Input() techserviceId: number;
  @ViewChild(SkillComponent) skill: SkillComponent; 

  registerFormStub: SignUpInfo = new SignUpInfo('', '', '', '', '', 'ROLE_WORKER');
  registerForm: SignUpInfo = this.registerFormStub;

  constructor(private workerService: WorkerService, private authService: AuthService) { }

  ngOnInit() {
    this.recieveWorkers()
  } 
/*
  deleteWorker(workerId: number) {
    this.workerService.deleteWorkerById(workerId).subscribe(
      data => this.recieveWorkers()
    );

  }
*/

  recieveWorkers() {
    this.workerService.getAllWorkers(this.techserviceId)
          .subscribe(data => {
            this.workers = data;
            this.workers.forEach(worker => {
              this.workerService.getRatingByWorkerId(worker.id).subscribe(data => worker.rating = data ? data : 0);
            })}
          , error => this.error = error);
          
  }

  registerWorker() {
    this.authService.signUp(this.registerForm).subscribe((data) => {
      this.workerService.initialiseWorker(
        this.registerForm.username, 
        this.techserviceId, 
        this.skill.selectedSkill)
          .subscribe(() => { 
            this.recieveWorkers(); 
            this.registerForm = new SignUpInfo('', '', '', '', '', 'ROLE_WORKER');
           });
    }, (error) => {
      this.workerService.initialiseWorker(
        this.registerForm.username, 
        this.techserviceId, 
        this.skill.selectedSkill)
          .subscribe(() => { 
            this.recieveWorkers(); 
            this.registerForm = new SignUpInfo('', '', '', '', '', 'ROLE_WORKER');
           });
    });
  }
}
