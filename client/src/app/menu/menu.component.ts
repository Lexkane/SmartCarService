import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../auth/token-storage.service';
import { User } from '../users/user';
import { UsersService } from '../users/users.service';
import { ActivatedRoute, Router } from '@angular/router';
import { TechserviceService } from '../techservice/techservice.service';
import { Techservice } from '../techservice/techservice';
import { Notifications } from '../notifications/notifications';
import { NotificationsService } from '../notifications/notifications.service';
import { Observable } from 'rxjs/internal/Observable';
import { ObserveOnMessage } from 'rxjs/internal/operators/observeOn';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  private roles: String[];
  private authority: String;
  private username: String;
  private techservice: Techservice = new Techservice();
  user: User = new User(null, null, null, null, null, null);
  private notificationsOpen: boolean;

  notifications: Notifications[];

  constructor(private tokenStorage: TokenStorageService, private userService: UsersService, private route: ActivatedRoute, private techserviceService: TechserviceService, private router: Router,
    private notificationsService: NotificationsService) { }

  ngOnInit() {
    this.notificationsOpen = false;
    this.username = this.tokenStorage.getUsername();

    this.userService.getUserByUsername(this.username)
      .subscribe(data => this.user = data);

    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_CAR_OWNER') {
          this.authority = 'owner';
          return false;
        } else if (role === 'ROLE_SALES_MANAGER') {
          this.authority = 'sales';
          return false;
        } else if (role === 'ROLE_TECHNICAL_MANAGER') {
          this.authority = 'techmanager';
          return false;
        } else if (role === 'ROLE_DIELER') {
          this.authority = 'dieler';
          return false;
        } else if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        } else if (role === 'ROLE_WORKER') {
          this.authority = 'worker';
          return false;
        }

      });
    } else {
      window.location.href = "/ui/auth/login";
    }
    this.notificationsService.currentNotifications.subscribe(data => this.notifications = data);
    this.notificationsService.updateNotifications(this.notificationsService.getAllNotifications(this.user.id));
  }

  goToNotifications(){
    this.router.navigate(['/ui/notifications-list', this.user.id]);
    this.notificationsOpen = true;
  }

  goToOwnerCars() {
    this.router.navigate(['/ui/cars']);
  }

  logout(){
    this.tokenStorage.signOut();
    window.location.href='/ui/preview';
  }

  goToDealerinfo(){
    this.router.navigate(['/ui/dealer']);
  }

  goToDealerCars(){
    this.router.navigate(['/ui/dealercars']);
  }

  addCarToDealer(){
    this.router.navigate(['/ui/addDealerCar']);
  }
  goToDealerChat(){
    this.router.navigate(['/ui/dealerChat']);
  }
  goToUserProfile(){
    this.router.navigate(['/ui/userprofile']);
  }

  goHome(){
    this.router.navigate(['/ui/home']);
  }
}
