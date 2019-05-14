import {Component, OnInit} from '@angular/core';
import {UserService} from '../services/user.service';


@Component({
  selector: 'app-tm',
  templateUrl: './tm.component.html',
  styleUrls: ['./tm.component.scss']
})
export class TmComponent implements OnInit {

  board: string;
  errorMessage: string;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getPMBoard().subscribe(
      data => {
        this.board = data;
      },
      error => {
        this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
      }
    );
  }

}
