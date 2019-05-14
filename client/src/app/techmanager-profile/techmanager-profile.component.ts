import { Component, OnInit } from '@angular/core';
import { TechmanagerProfileService } from './techmanager-profile.service';
import { TechmanagerProfile } from './techmanager-profile';

@Component({
  selector: 'app-techmanager-profile',
  templateUrl: './techmanager-profile.component.html',
  styleUrls: ['./techmanager-profile.component.scss'],
  providers: [TechmanagerProfileService]
})
export class TechmanagerProfileComponent implements OnInit {

  profile: TechmanagerProfile;
  error: ErrorEvent;
  //response: Response; 
  constructor(private profileService: TechmanagerProfileService) { }

  ngOnInit() { 
    this.initProfile();
  }

  initProfile() {
    this.profileService.getProfileResp()
    .subscribe(data => this.profile = data,
              error => this.error = error);
  }

  updateProfile() {
    this.profileService.putProfileUpdate(this.profile).subscribe();
  }
}
