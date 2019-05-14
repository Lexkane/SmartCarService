import { TestBed } from '@angular/core/testing';

import { DealercarsService } from './dealercars.service';

describe('DealercarsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DealercarsService = TestBed.get(DealercarsService);
    expect(service).toBeTruthy();
  });
});
