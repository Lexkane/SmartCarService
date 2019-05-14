import { TestBed } from '@angular/core/testing';

import { TradesinService } from './tradesin.service';

describe('TradesinService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TradesinService = TestBed.get(TradesinService);
    expect(service).toBeTruthy();
  });
});
