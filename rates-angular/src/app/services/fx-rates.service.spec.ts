import { TestBed } from '@angular/core/testing';

import { FxRatesService } from './fx-rates.service';

describe('FxRatesService', () => {
  let service: FxRatesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FxRatesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
