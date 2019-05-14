import { TestBed } from '@angular/core/testing';

import { TechmanagerProfileService } from './techmanager-profile.service';

describe('TechmanagerProfileService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TechmanagerProfileService = TestBed.get(TechmanagerProfileService);
    expect(service).toBeTruthy();
  });
});
