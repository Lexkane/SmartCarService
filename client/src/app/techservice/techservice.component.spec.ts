import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TechserviceComponent } from './techservice.component';

describe('TechserviceComponent', () => {
  let component: TechserviceComponent;
  let fixture: ComponentFixture<TechserviceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TechserviceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TechserviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
