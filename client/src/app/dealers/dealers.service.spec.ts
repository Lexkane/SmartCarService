import { TestBed } from '@angular/core/testing';

import { DealersService } from './dealers.service';

describe('DealersService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DealersService = TestBed.get(DealersService);
    expect(service).toBeTruthy();
  });
});
