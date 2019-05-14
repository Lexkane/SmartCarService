import { Component, OnInit } from '@angular/core';
import { UsersService } from '../users/users.service';
import { TechserviceService } from '../techservice/techservice.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { Techservice } from '../techservice/techservice';
import { Globals } from '../globals';

@Component({
  selector: 'app-techservice-page',
  templateUrl: './techservice-page.component.html',
  styleUrls: ['./techservice-page.component.scss']
})
export class TechservicePageComponent implements OnInit {

  appliedTechservice: Techservice = new Techservice();
  allTechservices: Techservice[] = [];

  constructor(
    private techserviceService: TechserviceService,
    private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    this.getTechservice().then(() => {
      if(this.appliedTechservice.stoId < 0) {
        this.getAllTechservices();
      }
    });
    console.log(this.appliedTechservice);
  }

  getTechservice() {
    Globals.baseURL;
    return new Promise((resolve, reject) => {
      this.techserviceService.getTechnicalServiceByUsername(this.tokenStorage.getUsername())
        .subscribe(data => {
          if (data != null) {
            this.appliedTechservice = data;
            console.log(data);
          }
          resolve();
        });
    });
  }

  getAllTechservices() {
    this.techserviceService.getAllTechnicalServices()
      .subscribe(data => this.allTechservices = data);
  }
}
