import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DealerstosComponent } from './dealerstos.component';

describe('DealerstosComponent', () => {
  let component: DealerstosComponent;
  let fixture: ComponentFixture<DealerstosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DealerstosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DealerstosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
