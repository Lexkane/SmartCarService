import { TestBed } from '@angular/core/testing';

import { DealerstoaddService } from './dealerstoadd.service';

describe('DealerstoaddService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DealerstoaddService = TestBed.get(DealerstoaddService);
    expect(service).toBeTruthy();
  });
});
