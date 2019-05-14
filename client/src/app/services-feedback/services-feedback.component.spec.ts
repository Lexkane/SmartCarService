import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServicesFeedbackComponent } from './services-feedback.component';

describe('ServicesFeedbackComponent', () => {
  let component: ServicesFeedbackComponent;
  let fixture: ComponentFixture<ServicesFeedbackComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServicesFeedbackComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServicesFeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
