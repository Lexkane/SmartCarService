import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-car-tracker',
  templateUrl: './car-tracker.component.html',
  styleUrls: ['./car-tracker.component.scss']
})
export class CarTrackerComponent implements OnInit {

public map: any = { lat: 49.843251, lng: 24.026131 };
public car1: any = { lat: 49.80, lng: 24.0 };
public car2: any = { lat: 49.82, lng: 24.02 };
public car3: any = { lat: 49.84, lng: 24.025 };

longitude = 24.026131;
latitude = 49.843251;

markers = [{ latitude: 49.843251, longitude: 24.026131 }];

placeMarker(position: any) {
const lat = position.coords.lat;
const lng = position.coords.lng;

this.markers.push({ latitude: lat, longitude: lng });
}

//create new markers depending on numbers of cars

  constructor() { }

  ngOnInit() {
  }

}
