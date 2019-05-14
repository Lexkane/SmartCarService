import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TradesinComponent } from './tradesin.component';

describe('TradesinComponent', () => {
  let component: TradesinComponent;
  let fixture: ComponentFixture<TradesinComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TradesinComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TradesinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
