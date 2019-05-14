import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServicesFeedbackFormComponent } from './services-feedback-form.component';

describe('ServicesFeedbackFormComponent', () => {
  let component: ServicesFeedbackFormComponent;
  let fixture: ComponentFixture<ServicesFeedbackFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServicesFeedbackFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServicesFeedbackFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
