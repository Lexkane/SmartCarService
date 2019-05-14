import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoMassageComponent } from './info-massage.component';

describe('InfoMassageComponent', () => {
  let component: InfoMassageComponent;
  let fixture: ComponentFixture<InfoMassageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InfoMassageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InfoMassageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
