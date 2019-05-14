import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CarNormsComponent } from './car-norms.component';

describe('CarNormsComponent', () => {
  let component: CarNormsComponent;
  let fixture: ComponentFixture<CarNormsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CarNormsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CarNormsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
