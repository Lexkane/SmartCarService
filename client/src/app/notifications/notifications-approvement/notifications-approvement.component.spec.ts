import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NotificationsApprovementComponent } from './notifications-approvement.component';

describe('NotificationsApprovementComponent', () => {
  let component: NotificationsApprovementComponent;
  let fixture: ComponentFixture<NotificationsApprovementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NotificationsApprovementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NotificationsApprovementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
