import { Component, OnInit } from '@angular/core';
import { FxRatesService, FxRate } from '../../services/fx-rates.service';

@Component({
  selector: 'app-currency-calculator',
  templateUrl: './currency-calculator.component.html',
  styleUrls: ['./currency-calculator.component.css']
})
export class CurrencyCalculatorComponent implements OnInit {
  rates: FxRate[] = [];
  
  amountEur: number = 100;
  amountConverted: number | null = null;
  currency: string = '';

  result: any = null;
  rateUsed: number | null = null;
  error: string | null = null;

  constructor(private fxRatesService: FxRatesService) {}

  ngOnInit(): void {
    this.fxRatesService.getTodayRates().subscribe({
      next: (data) => {
        this.rates = data;
      },
      error: (err) => {
        console.error(err);
        this.error = 'Failed to load rates';
      }
    });
  }

    calculate(): void {
    if (!this.amountEur || !this.currency) return;

    const selectedRate = this.rates.find(r => r.currency === this.currency);
    if (!selectedRate) {
      this.error = 'Rate not found for selected currency';
      return;
    }

    this.rateUsed = selectedRate.rate;
    this.amountConverted = this.amountEur * selectedRate.rate;
  }
}