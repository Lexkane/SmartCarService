import { TestBed } from '@angular/core/testing';

import { TechserviceService } from './techservice.service';

describe('TechserviceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TechserviceService = TestBed.get(TechserviceService);
    expect(service).toBeTruthy();
  });
});
