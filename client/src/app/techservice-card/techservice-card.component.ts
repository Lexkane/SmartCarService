import { Component, OnInit, Input } from '@angular/core';
import { Techservice } from '../techservice/techservice';
import { TokenStorageService } from '../auth/token-storage.service';
import { TechserviceService } from '../techservice/techservice.service';

@Component({
  selector: 'techservice-card',
  templateUrl: './techservice-card.component.html',
  styleUrls: ['./techservice-card.component.scss']
})
export class TechserviceCardComponent implements OnInit {

  @Input() techservice: Techservice;
  @Input() applyButton: boolean = false;

  constructor(
    private techserviceService: TechserviceService,
    private tokenStorage: TokenStorageService
  ) { }

  ngOnInit() {
  }

  applyToTechservice() {
    this.techserviceService.applyUserToTechnicalService(
      this.tokenStorage.getUsername(), 
      this.techservice.stoId)
        .subscribe();
  }
}
