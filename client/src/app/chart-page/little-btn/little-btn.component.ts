import {Component, OnInit, Input} from '@angular/core';

import COLORS from '../colors';

@Component({
  selector: 'little-btn',
  templateUrl: './little-btn.component.html',
})
export class LittleBtnComponent implements OnInit {

  @Input()
  text: string;

  @Input()
  sensor: string;

  constructor() {
  }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    if (this.text === 'Avg') {
      document.getElementById(`Avg_${this.sensor}`).style.backgroundColor = COLORS.get('LIGHT_BTN');
    }
  }

}
