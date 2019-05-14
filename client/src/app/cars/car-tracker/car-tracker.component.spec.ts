import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CarTrackerComponent } from './car-tracker.component';

describe('CarTrackerComponent', () => {
  let component: CarTrackerComponent;
  let fixture: ComponentFixture<CarTrackerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CarTrackerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CarTrackerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
