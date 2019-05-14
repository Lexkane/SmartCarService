import { Component, OnInit } from '@angular/core';
import { SkillService } from './skill-service';
import { ActivatedRoute, Router } from '@angular/router';
import { WorkerList } from '../worker-list/worker-list-real';
import { NumberValueAccessor } from '@angular/forms/src/directives';
import { WorkType } from './work-type';
import { NotificationsService } from 'src/app/notifications/notifications.service';
import { Notifications } from 'src/app/notifications/notifications';
import { Notification } from 'rxjs';
@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.scss'],
  providers: [SkillService]
})
export class StoSkillComponent implements OnInit {
  workerList: WorkerList = new WorkerList();
  selectedWork: Map<string, number> = new Map();
  totalPrice: number = 0;
  workType: Map<string, Array<WorkType>>;
  error: ErrorEvent;
  searchId: number;
  userId: number;

  fromNoti : boolean = false;

  notificationWork: Map<string, boolean> = new Map<string, boolean>();
  noti: Notifications[];
  notiId : Array<number> = new Array();

  show: boolean = true;
  works: Array<WorkType>;
  headElements: Array<string> = ["Name", "Price"];
  selectedSkill: Map<string, number> = new Map();
  lastSkillName: string;
  info : number;

  noWorkSelect = false;

  constructor(private skillService: SkillService, private route: ActivatedRoute,
     private notificationService: NotificationsService, private router: Router) {}

  ngOnInit() {
    this.searchId = Number(this.getFromRouterParams('carId'));
    console.log(this.searchId);
    if(this.searchId == 0){
      this.fromNoti = true;
      this.getUsersNotifications();
      if(this.noti[0] == undefined){
        this.goToCarsPage();
      }
      else{
      this.searchId = this.noti[0].carId;
      }
  }
  this.getWorkType();
}

  private getFromRouterParams(param: string) {
    return this.route.snapshot.queryParamMap.get(param);
  }


  getWorkType() {
    this.skillService.getAllSkillsToStoResp(this.searchId)
      .subscribe((data: Map<string, Array<WorkType>>) => this.workType = data,
        error => this.error = error);
  }

  getUsersNotifications() {
    this.notificationService.currentNotifications
      .subscribe((data: Notifications[]) => {
        this.noti = data;
        for(let index = 0; index < this.noti.length; index++){
          console.log(this.noti[index].type);
            if(this.noti[index].isSelected && this.noti[index].type == 'Warning'){
              this.notificationWork.set(this.noti[index].workTypeName, true);
                this.notiId.push(this.noti[index].id);
            }
        }  
      }
    );
  }


  addOrDelete(w: WorkType, skillName: string) {
    this.show = true;
    this.noWorkSelect = false;

    if (this.selectedWork.get(w.workName) != undefined) {
      if (this.selectedWork.get(w.workName) == -1) {
        this.totalPrice += w.cost;
        this.selectedWork.set(w.workName, 1);
        this.setClass(w.workName, 'selected');
        this.selectedSkill.set(skillName, this.selectedSkill.get(skillName) + 1);
      }
      else {
        this.totalPrice -= w.cost;
        this.selectedWork.set(w.workName, -1);
        if (this.notificationWork.get(w.workName) == undefined) {
          this.setClass(w.workName, 'non-select');
        }
        else {
          this.setClass(w.workName, 'notification');
        }
        this.selectedSkill.set(skillName, this.selectedSkill.get(skillName) - 1);
      }
    }
    else {
      this.selectedWork.set(w.workName, 1);
      this.totalPrice += w.cost;
      this.setClass(w.workName, 'selected');
      if (this.selectedSkill.get(skillName) == undefined) {
        this.selectedSkill.set(skillName, 1);
      }
      else {
        this.selectedSkill.set(skillName, this.selectedSkill.get(skillName) + 1);
      }
    }

  }

  setWorkStyle(w: WorkType): string {
    if (this.selectedWork.get(w.workName) == undefined || this.selectedWork.get(w.workName) == -1) {
      if (this.notificationWork.get(w.workName) == undefined) {
        return "non-select";
      }
      else {
        return "notification"
      }
    }
    else {
      return "selected";
    }
  }

  private setClass(id: string, className: string): void {
    document.getElementById(id).className = className;
  }

  private setSkillClass(id: string, className: string): void {
    document.getElementById(id).className = "list-group-item " + className;
  }

  changeShow() {
    this.workerList = this.GetData();
    if (this.workerList.worksName.length == 0) {
      this.noWorkSelect = true;
    }
    else {
      this.show = false;
    }
  }

  GetData(): WorkerList {
    this.works = undefined;

    let workerList: WorkerList = new WorkerList();
    let workName: string[] = new Array<string>();
    let skillName: string[] = new Array<string>();
    this.selectedWork.forEach((value: number, key: string) => {
      if (value === 1) {
        workName.push(key);
      }
    });
    this.selectedSkill.forEach((value: number, key: string) => {
      if (value >= 1) {
        skillName.push(key);
      }
    });
    workerList.worksName = workName;
    workerList.skillsName = skillName;
    workerList.carId = this.searchId;

    this.selectedWork = new Map();
    this.selectedSkill = new Map();
    console.log(workerList);
    return workerList;
  }

  workEmpty() {
    if (this.works == undefined) {
      return false;
    }
    return true;
  }

  chooseSkill(works: Array<WorkType>) {
    this.works = works;
  }

  selectWork(workId: string) {
    this.setClass(workId, "selected");
  }

  selectSkill(skillId: string) {
    this.show = true;

    if (this.lastSkillName == undefined) {
      this.setSkillClass(skillId, "selectSkill");
      this.lastSkillName = skillId;
    }
    else {
      if(this.lastSkillName == skillId){
        this.setSkillClass(this.lastSkillName, "non-selectSkill");
        this.lastSkillName = undefined;
        this.works = undefined;
        return;
      }
      this.setSkillClass(skillId, "selectSkill");
      this.setSkillClass(this.lastSkillName, "non-selectSkill");
      this.lastSkillName = skillId;
    }
  }

  isWarning() {
    if (this.noWorkSelect) {
      document.getElementById("bookingPage").className = "d-flex flex-row warning";
    }
    else {
      document.getElementById("bookingPage").className = "d-flex flex-row no-warning";
    }
  }
  goToCarsPage() {
    this.router.navigate(['ui/cars']);
  }
}
