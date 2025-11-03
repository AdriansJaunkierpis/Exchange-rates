import { Component, OnInit } from '@angular/core';
import { FxRatesService, FxRate } from '../../services/fx-rates.service';

@Component({
  selector: 'app-fx-rate-list',
  templateUrl: './fx-rate-list.component.html',
  styleUrls: ['./fx-rate-list.component.css']
})
export class FxRateListComponent implements OnInit {
  rates: FxRate[] = [];

  constructor(private fxRatesService: FxRatesService) {}

  ngOnInit(): void {
    this.fxRatesService.getTodayRates().subscribe({
      next: (data) => {
        this.rates = data;
      }
    });
  }
}