import {Component, OnInit, ViewChild, SimpleChange} from '@angular/core';
import {Techservice} from './techservice';
import {TechserviceService} from './techservice.service';
import {TokenStorageService} from '../auth/token-storage.service';
import {User} from '../users/user';
import {UsersService} from '../users/users.service';
import {MapComponent} from 'src/app/techservice/map/map.component'
import {MatExpansionModule} from '@angular/material/expansion';

@Component({
  selector: 'app-techservice',
  templateUrl: './techservice.component.html',
  styleUrls: ['./techservice.component.scss']
})
export class TechserviceComponent implements OnInit {


  techserviceStub: Techservice = new Techservice();
  techservice: Techservice = this.techserviceStub;
  created: boolean;

  @ViewChild(MapComponent) map: MapComponent;

  error: ErrorEvent;

  user: User;

  constructor(private techserviceService: TechserviceService,
              private tokenStorage: TokenStorageService, private userService: UsersService) {
  }

  ngOnInit() {
    this.getCurrentUser();
  }

  onLocationChosen(address: any) {
    if (address) {
      this.techservice.address = address;
    }
  }

  getCurrentUser() {
    this.userService.getUserByUsername(this.tokenStorage.getUsername())
      .subscribe(data => {
        this.user = data;
        this.getTechservice(this.user);
      });
  }

  createTechservice(techservice: Techservice, user: User) {
    this.techserviceService.createTechnicalService(techservice, user.id)
      .subscribe(() => {
        this.getTechservice(user);
      });
  }

  getTechservice(user: User) {
    this.techserviceService.getTechnicalServiceByCurrentUser(this.user.id)
      .subscribe(data => {
        if (data != null) {
          this.techservice = data;
          console.log(data);
        }
      });
  }

  updateTechservice() {
    this.techserviceService.updateTechnicalService(this.techservice)
      .subscribe();
  }

  deleteTechservice() {
    this.techserviceService.deleteTechservice(this.techservice.stoId)
      .subscribe(() => {
        this.techservice = new Techservice();
      });
  }
}
