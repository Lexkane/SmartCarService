import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DealerstoaddComponent } from './dealerstoadd.component';

describe('DealerstoaddComponent', () => {
  let component: DealerstoaddComponent;
  let fixture: ComponentFixture<DealerstoaddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DealerstoaddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DealerstoaddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
