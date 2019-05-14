import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Observable } from 'rxjs';
import { Notifications } from '../notifications';
import { NotificationsService } from '../notifications.service';
import { PopupService } from 'src/app/notifications/popup/popup.service';
import { MatDialog } from '@angular/material';
import { PopupComponent } from 'src/app/notifications/popup/popup.component';

@Component({
  selector: 'app-notifications-list',
  templateUrl: './notifications-list.component.html',
  styleUrls: ['./notifications-list.component.scss']
})
export class NotificationsListComponent implements OnInit {

  notifications: Notifications[];
  private sorted = false;

  @Input() notification: Notifications;
  @Input() id: number;
  constructor(private router: Router,
    private notificationsService: NotificationsService,
    private route: ActivatedRoute, 
    private popupService: PopupService,  
    public dialog: MatDialog) {
      let stompClient = this.notificationsService.connect();
      stompClient.connect({}, frame => {
        stompClient.subscribe('/notifications-list', response => {
          let notification = JSON.parse(response.body);
          this.notificationsService.wrapNotification(notification)
          this.notifications.unshift(notification);
        })
      })
  }
  ngOnInit() {
    this.route.params.subscribe(params => { this.id = params["id"]; });
    this.notificationsService.currentNotifications.subscribe(data => this.notifications = data);
    this.notificationsService.updateNotifications(this.notificationsService.getAllNotifications(this.id));
  }
  deleteNotification(id: number): void {
    this.notificationsService.deleteNotification(id)
      .subscribe(
        data => {
          console.log('delete notification data', data);
          this.notifications = this.notifications.filter(n => n.id !== id);
        },
        error => console.log(error));
  }

  applyFor(): void {
    let isSelected = false;
    let carIds = new Set();
    this.notifications.forEach(
      n => {
        if (n.isSelected) {
          isSelected = true;
          carIds.add(n.carId);
        }
      }
    )
    if (isSelected && carIds.size === 1) {
      this.router.navigate(['/ui/notifications-approvement', this.id]);
    } else {
      var message = "";
      if (carIds.size > 1) {
        message = "You can choose only one car at once";
      } else {
        message = "Select at least one notification";
      }

      const dialogRef = this.dialog.open(PopupComponent, {
        height: '150px',
        width: '400px',
        data: message
      });
  
      dialogRef.afterClosed().subscribe(result => {
        console.log("afterClosed");
        });
    }
  }

  sortBy(by: string | any): void {

    this.notifications.sort((a: any, b: any) => {
      if (a[by] < b[by]) {
        return this.sorted ? 1 : -1;
      }
      if (a[by] > b[by]) {
        return this.sorted ? -1 : 1;
      }

      return 0;
    });

    this.sorted = !this.sorted;
  }
}
