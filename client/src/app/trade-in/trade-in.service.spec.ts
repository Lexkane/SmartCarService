import { TestBed } from '@angular/core/testing';

import { TradeInService } from './trade-in.service';

describe('TradeInService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TradeInService = TestBed.get(TradeInService);
    expect(service).toBeTruthy();
  });
});
