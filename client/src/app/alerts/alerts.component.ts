import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute, Router } from '@angular/router';
import {MatDialog} from '@angular/material';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { CarsService } from '../cars/cars.service';

@Component({
  selector: 'app-alerts',
  templateUrl: './alerts.component.html',
  styleUrls: ['./alerts.component.scss']
})
export class AlertsComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: number,
  public dialogRef: MatDialogRef<AlertsComponent>, private router: Router, public dialog: MatDialog, private carsService: CarsService) { }

  ngOnInit() {
  }

  deleteCar(){
    console.log(this.data)
    this.carsService.deleteCarById(this.data).subscribe();
    this.reloadPage();
  }

  reloadPage() {
    window.location.href='/ui/cars/';
  }

}
