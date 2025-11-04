import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface FxRate {
  currency: string;
  rate: number;
  date: string;
}

@Injectable({
  providedIn: 'root'
})
export class FxRatesService {
  private apiUrl = 'http://localhost:8080/api/rates';

  constructor(private http: HttpClient) {}

  getTodayRates(): Observable<FxRate[]> {
    return this.http.get<FxRate[]>(this.apiUrl);
  }

  getCurrencyHistory(currency: string): Observable<FxRate[]> {
    return this.http.get<FxRate[]>(`${this.apiUrl}/${currency}/history`);
  }
}