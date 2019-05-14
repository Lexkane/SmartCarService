import {Component, Input, OnInit} from '@angular/core';
import {ServiceHistoryService} from "../service-history.service";

@Component({
  selector: 'pdf',
  templateUrl: './pdf.component.html',
  styleUrls: ['./pdf.component.scss'],
  providers: [ServiceHistoryService]
})
export class PdfComponent implements OnInit {

  @Input()
  reportId: number;

  constructor(private service: ServiceHistoryService) { }

  ngOnInit() {
  }

  openPdf() {
    this.service.getPdfFromDB(this.reportId).subscribe(response => {
      let file = new Blob([response], {type: 'application/pdf'});
      var fileURL = URL.createObjectURL(file);
      console.log(response);
      console.log(file);
      console.log(fileURL);

      window.location.href = fileURL;
    });

  }

}
