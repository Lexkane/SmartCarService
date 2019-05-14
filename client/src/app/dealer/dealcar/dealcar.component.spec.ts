import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DealcarComponent } from './dealcar.component';

describe('DealcarComponent', () => {
  let component: DealcarComponent;
  let fixture: ComponentFixture<DealcarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DealcarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DealcarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
