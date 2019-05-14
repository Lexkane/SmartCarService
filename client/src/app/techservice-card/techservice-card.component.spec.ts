import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TechserviceCardComponent } from './techservice-card.component';

describe('TechserviceCardComponent', () => {
  let component: TechserviceCardComponent;
  let fixture: ComponentFixture<TechserviceCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TechserviceCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TechserviceCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
