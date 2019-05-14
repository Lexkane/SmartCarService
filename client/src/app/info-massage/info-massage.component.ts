import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import {MatDialog} from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-info-massage',
  templateUrl: './info-massage.component.html',
  styleUrls: ['./info-massage.component.scss']
})
export class InfoMassageComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<InfoMassageComponent>, private router: Router, public dialog: MatDialog) { }

  ngOnInit() {
  }

}
