import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FxRateListComponent } from './fx-rate-list.component';

describe('FxRateListComponent', () => {
  let component: FxRateListComponent;
  let fixture: ComponentFixture<FxRateListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FxRateListComponent]
    });
    fixture = TestBed.createComponent(FxRateListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
