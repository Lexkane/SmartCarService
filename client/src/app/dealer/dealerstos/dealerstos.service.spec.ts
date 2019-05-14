import { TestBed } from '@angular/core/testing';

import { DealerstosService } from './dealerstos.service';

describe('DealerstosService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DealerstosService = TestBed.get(DealerstosService);
    expect(service).toBeTruthy();
  });
});
