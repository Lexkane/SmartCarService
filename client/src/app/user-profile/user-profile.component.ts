import {Component, OnInit} from '@angular/core';
import {UserComponent} from '../user/user.component';
import {UsersService} from '../users/users.service';
import {User} from '../users/user';
import {TokenStorageService} from '../auth/token-storage.service';
import { CarsService } from '../cars/cars.service';
import { Car } from '../cars/car';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  userProfile: User = new User(null, null, null, null, null, null);
  username: String;
  cars: Car[];
  numberOfCars: Number = null;
  today = new Date().toISOString().slice(0, 10)
  
  constructor(private userService: UsersService, private tokenStorage: TokenStorageService, private carService: CarsService, private router: Router) {
  }

  ngOnInit() {

  this.userService.getUserByUsername(this.tokenStorage.getUsername())
  .subscribe(data => this.userProfile = data);
}

closeProfile(){
  this.router.navigate(['ui/home']);
  }

}
