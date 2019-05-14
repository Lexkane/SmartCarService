import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkersFeedbackComponent } from './workers-feedback.component';

describe('WorkersFeedbackComponent', () => {
  let component: WorkersFeedbackComponent;
  let fixture: ComponentFixture<WorkersFeedbackComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkersFeedbackComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkersFeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
