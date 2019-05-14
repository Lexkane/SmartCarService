import { TestBed } from '@angular/core/testing';

import { CarProfileService } from './car-profile.service';

describe('CarProfileService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CarProfileService = TestBed.get(CarProfileService);
    expect(service).toBeTruthy();
  });
});
