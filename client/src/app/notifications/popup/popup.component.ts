import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute, Router } from '@angular/router';
import {MatDialog} from '@angular/material';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { NotificationsService } from '../notifications.service';

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.scss']
})
export class PopupComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: String,
  public dialogRef: MatDialogRef<PopupComponent>, private router: Router, 
  public dialog: MatDialog, private notificationsService: NotificationsService) { }

  ngOnInit() {
  }

}
