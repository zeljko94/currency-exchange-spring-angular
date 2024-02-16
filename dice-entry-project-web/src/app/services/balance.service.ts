import { Injectable, numberAttribute } from '@angular/core';
import { BaseRestApiService } from './base-rest-api.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BalanceService extends BaseRestApiService {

  constructor(http: HttpClient) {
    super(http, "/api/balance");
   }

   addMoney(balanceId: number, amount: number): Observable<any> {
    return this.http.put(this.apiRoute + '/add/' + balanceId + '/' + amount, {});
  }

  subtractMoney(balanceId: number, amount: number): Observable<any> {
    return this.http.put(this.apiRoute + '/subtract/' + balanceId + '/' + amount, {});
  }


  transferMoney(balanceFromId: number, balanceToId: number, amount: number): Observable<any> {
    return this.http.put(this.apiRoute + '/transfer/' + balanceFromId + '/' + balanceToId + '/' + amount, {});
  }


   getBalancesForUser(userId: number): Observable<any> {
    return this.http.get(this.apiRoute + '/getBalancesForUser/' + userId);
  }

  insertBalance(data: any, userId: number): Observable<any> {
    return this.http.post(this.apiRoute + '/insert/' + userId, data);
  }
}
