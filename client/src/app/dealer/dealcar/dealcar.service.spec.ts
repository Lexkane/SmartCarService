import { TestBed } from '@angular/core/testing';

import { DealcarService } from './dealcar.service';

describe('DealcarService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DealcarService = TestBed.get(DealcarService);
    expect(service).toBeTruthy();
  });
});
