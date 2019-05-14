import { Component, OnInit } from '@angular/core';
import { CarsService } from '../cars.service';
import { MatDialog } from '@angular/material';
import { InfoMassageComponent } from 'src/app/info-massage/info-massage.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-car-norms',
  templateUrl: './car-norms.component.html',
  styleUrls: ['./car-norms.component.scss']
})
export class CarNormsComponent implements OnInit {

  fileToUpload: File = null;
  httpStatusCode: number;
  error: ErrorEvent;
  
  constructor(private carService: CarsService, public dialog: MatDialog, private router: Router) { }

  ngOnInit() {
  }

  handleFileInput(event) {
    this.fileToUpload = <File>event.target.files[0];
    console.log(event.target.files)
  }

  uploadFile() {
   var formData = new FormData();
   formData.append('file', this.fileToUpload);
   console.log("file to upload" + formData);

    this.carService.postFile(formData)
    .subscribe(error => {
      this.httpStatusCode = error.status,
      error => this.error = error;

        if(this.httpStatusCode === 202){
          console.log(this.httpStatusCode);
          this.router.navigate(['ui/cars']);
        } else {
          console.log(this.error);
          this.showWarning();
       }
      });
    }

    showWarning(): void {
      const dialogRef = this.dialog.open(InfoMassageComponent, {
        height: '150px',
        width: '400px',        
      });
  
      dialogRef.afterClosed().subscribe(result => {
      });
    }
}
