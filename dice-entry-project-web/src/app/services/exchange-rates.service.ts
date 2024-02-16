import { Injectable } from '@angular/core';
import { BaseRestApiService } from './base-rest-api.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExchangeRatesService extends BaseRestApiService {

  constructor(http: HttpClient) {
    super(http, "/api/exchange-rates");
   }

   getExchangeRates(base: string): Observable<any> {
      return this.http.get(this.apiRoute + '/' + base);
   }

   convertAmount(base: string, to: string, amount: number): Observable<any> {
    return this.http.get(this.apiRoute + '/convertAmount/' + base + '/' + to + '/' + amount);
  }

  fetchRatesAndInsertCurrencies() {
    return this.http.get(this.apiRoute + '/testFetchRates');
  }

}
