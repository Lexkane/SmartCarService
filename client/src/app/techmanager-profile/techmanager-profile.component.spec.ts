import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TechmanagerProfileComponent } from './techmanager-profile.component';

describe('TechmanagerProfileComponent', () => {
  let component: TechmanagerProfileComponent;
  let fixture: ComponentFixture<TechmanagerProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TechmanagerProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TechmanagerProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
