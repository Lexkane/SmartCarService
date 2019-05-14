import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TechservicePageComponent } from './techservice-page.component';

describe('TechservicePageComponent', () => {
  let component: TechservicePageComponent;
  let fixture: ComponentFixture<TechservicePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TechservicePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TechservicePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
