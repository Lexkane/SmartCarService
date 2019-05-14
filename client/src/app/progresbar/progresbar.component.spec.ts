import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProgresbarComponent } from './progresbar.component';

describe('ProgresbarComponent', () => {
  let component: ProgresbarComponent;
  let fixture: ComponentFixture<ProgresbarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProgresbarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProgresbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
