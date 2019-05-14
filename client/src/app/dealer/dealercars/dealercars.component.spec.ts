import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DealercarsComponent } from './dealercars.component';

describe('DealercarsComponent', () => {
  let component: DealercarsComponent;
  let fixture: ComponentFixture<DealercarsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DealercarsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DealercarsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
