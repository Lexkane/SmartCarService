import { TestBed } from '@angular/core/testing';

import { ServicesFeedbackService } from './services-feedback.service';

describe('ServicesFeedbackService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ServicesFeedbackService = TestBed.get(ServicesFeedbackService);
    expect(service).toBeTruthy();
  });
});
