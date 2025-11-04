import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FxRatesService, FxRate } from '../../services/fx-rates.service';

@Component({
  selector: 'app-fx-rate-history',
  templateUrl: './fx-rate-history.component.html',
  styleUrls: ['./fx-rate-history.component.css']
})
export class FxRateHistoryComponent {
  currency!: string;
  rates: FxRate[] = [];

  constructor(
    private route: ActivatedRoute,
    private fxRatesService: FxRatesService
  ) {}

  ngOnInit(): void {
    this.currency = this.route.snapshot.paramMap.get('currency')!;
    this.fxRatesService.getCurrencyHistory(this.currency).subscribe({
      next: (data) => {
        this.rates = data;
      },
    });
  }
}
