import { Injectable } from '@angular/core';
import {MatDialog} from '@angular/material';
import { AlertsComponent } from './alerts.component';

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  constructor(private dialog: MatDialog) { }

}
