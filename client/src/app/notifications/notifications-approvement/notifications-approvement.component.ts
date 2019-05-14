import {Component, OnInit, Input} from '@angular/core';
import {Notifications} from '../notifications';
import {NotificationsService} from '../notifications.service';
import {Router, ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-notifications-approvement',
  templateUrl: './notifications-approvement.component.html',
  styleUrls: ['./notifications-approvement.component.scss']
})
export class NotificationsApprovementComponent implements OnInit {

  notifications: Notifications[];
  private sorted = false;

  @Input() id: number;

  constructor(private router: Router,
              private notificationsService: NotificationsService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = params["id"];
    });
    this.notificationsService.currentNotifications.subscribe(data => this.notifications = data);
  }

  cancel() {
    this.router.navigate(['/ui/notifications-list', this.id]);
  }

  removeFromApprovement(id) {
    this.notifications.forEach(n => {
      if (n.id === id) n.isSelected = false
    })
  }

  approve(): void {
    let isSelected = false;
    this.notifications.forEach(
      n => {
        if (n.isSelected) {
          isSelected = true;
        }
      }
    )
    if (isSelected) {
      this.router.navigate(['/ui/booking']);
    } else {
      alert("Nothing to approve");
    }
  }
}
