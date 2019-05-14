import { Component, OnInit, Input } from '@angular/core';
import { TimeList } from './time-list';
import { TimeListService } from './time-list-service';
import { WorkTime } from './work-time';
import { NewBooking } from './new-booking';
import { error } from '@angular/compiler/src/util';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingInfo } from './booking-info';
import { WorkInfo } from '../worker-list/work-info';
import { Report } from './new-report';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { NotificationsService } from 'src/app/notifications/notifications.service';

@Component({
  selector: 'app-time-list',
  templateUrl: './time-list.component.html',
  styleUrls: ['./time-list.component.scss']
})
export class TimeListComponent implements OnInit {

  @Input()
  carId : number;

  @Input()
  workInfo : Array<WorkInfo>;

  @Input()
  timeList : TimeList;

  @Input()
  price : number;

  @Input()
  notiId : number[];

  @Input()
  fromNoti: boolean;



  error: ErrorEvent;
  errorStatus : number
  bookingInfo : BookingInfo;
  newBooking : NewBooking;
  postBookingStatusCode : number;
  postReportStatusCode : number;
  lastId : string;
  username : string;

  postBookingError = false;

  months = {'01':'Jan', '02':'Feb', '03':'Mar', '04':'Apr', '05':'May', '06':'June', '07':'July',
   '08':'Aug', '09':'Sept', '10':'Oct','11':'Nov', '12':'Dec'};

  constructor(private timeListServices : TimeListService, private router: Router,
    private tokenStorage: TokenStorageService, private notificationService: NotificationsService) {}

  ngOnInit() {
    this.getBookingInfo();
  }

  getBookingInfo(){
    this.timeListServices.getBookingTime(this.timeList)
    .subscribe((data) => this.bookingInfo = data,
    error => this.error = error);
  }

  getDate(date : string) : string{
    return date.slice(8, 10) + " " + this.months[date.slice(5, 7)] + " " + date.slice(11, 16);
  }

  parseDate(date : string): string{
    return date.slice(0, 10) + " " + date.slice(11, 19);
  }

  postFeedBack(){
    this.username = this.username = this.tokenStorage.getUsername();
    this.timeListServices.postFeedBack(this.timeList.workersId, this.username)
    .subscribe(error => {
      this.error = error});
  }

  deleteNotifications(){
    console.log(this.notiId);
    for(let index = 0; index < this.notiId.length; index++){
      console.log(this.notiId[index]);
          this.notificationService.deleteNotification(this.notiId[index]);
    }  
  }

  postBooking(){
    let newBooking: NewBooking = new NewBooking();
    newBooking.carId = this.carId;
    newBooking.start = this.parseDate(this.bookingInfo.startBooking);
    newBooking.worksInfo = this.workInfo;
    newBooking.workersId = this.timeList.workersId;

        this.timeListServices.postNewBooking(newBooking)
        .subscribe(error => {
          this.errorStatus = error.status,
          error => this.error = error,
          this.postBookingStatusCode = this.errorStatus;
          console.log(this.errorStatus);
          
          if(this.errorStatus === 200){
            console.log(this.fromNoti);
            if(this.fromNoti){
              for(let index = 0; index < this.notiId.length; index++){
                console.log(this.notiId[index]);
                    this.notificationService.deleteNotification(this.notiId[index]).subscribe(error => console.log(error));
              }
            }
            this.postReport();
            this.postFeedBack();
          }
          else{
            this.postBookingError = true;
          }
        },); 
        this.goToCarsPage();
}

  postReport(){
    let newReport: Report = new Report();
    newReport.carId = this.carId;
    newReport.startTime = this.parseDate(this.bookingInfo.startBooking);
    newReport.endTime = this.parseDate(this.bookingInfo.endBooking);
    newReport.requiredTime = this.timeList.needTime;
    newReport.price = this.price;

    this.timeListServices.postNewReport(newReport)
        .subscribe((date) => this.postReportStatusCode = date,
        error => this.error = error);

  }

goToHomePage() {
  this.router.navigate(['ui/home']);
}

goToCarsPage() {
  this.router.navigate(['ui/cars']);
}

}
