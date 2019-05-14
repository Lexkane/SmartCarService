import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material';
import { PopupComponent } from './popup.component';

@Injectable({
  providedIn: 'root'
})
export class PopupService {

  constructor(private dialog: MatDialog) { }

}
