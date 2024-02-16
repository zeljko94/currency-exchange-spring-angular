import { Component, OnInit } from '@angular/core';
import { CurrencyService } from '../../services/currency.service';
import { ExchangeRatesService } from '../../services/exchange-rates.service';
import { MessageService } from 'primeng/api';
import { debounceTime } from 'rxjs/operators';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {
  isLoading: boolean = false;
  amount: string = '1';
  currencies: any[] = [];
  dateNow: Date = new Date();
  exchangeRates: any;
  convertData: any = {};

  selectedFrom: any = {id: 1, code: 'EUR', name: 'EUR', label: 'EUR', value: 'EUR'};
  selectedTo: any = {id: 2, code: 'USD', name: 'USD', label: 'USD', value: 'USD'};


  constructor(private currencyService: CurrencyService, private exchangeRatesService: ExchangeRatesService, private messageService: MessageService) {
    
  }

  ngOnInit(): void {
    this.isLoading = true;
    this.currencyService.getAll().subscribe((data:any) => {
      this.currencies = data.map(({ id, code, name }) => ({
        id,
        code,
        name,
        label: code,
        value: code
      }));        
      
      this.selectedFrom = this.currencies.find(x => x.code == "EUR");
      this.selectedTo = this.currencies.find(x => x.code == "USD");

      
      this.exchangeRatesService.convertAmount("EUR", "USD", 1)
      .subscribe(data => {
        this.convertData = data;
      },
      err => {
        console.error(err);
      },
      () => { this.isLoading = false; });
    });

    this.exchangeRatesService.getExchangeRates("EUR").subscribe(exchangeRatesData => {
      this.exchangeRates = exchangeRatesData;
    },
    err => {
      console.error(err);
    },
    () => { 
      this.isLoading = false;
     }
    );

  }

  onFieldValueChange(event, name) {
    if(name == "from") {
      this.exchangeRatesService.getExchangeRates(this.selectedFrom.code).subscribe(exchangeRatesData => {
        this.exchangeRates = exchangeRatesData;
      },
      err => {
        console.error(err);
      },
      () => { 
        this.isLoading = false;
      });
    }

    this.onSubmit();
  }
  
  onSubmit() {
    const enteredAmount = parseFloat(this.amount);
    if (isNaN(enteredAmount) || enteredAmount <= 0) {
      this.messageService.add({ key: 'form-input-error', severity: 'error', summary: 'Error', detail: 'Invalid amount', life: 1000 });
      return;
    }

    if (this.selectedFrom && this.selectedTo) {
      this.exchangeRatesService.convertAmount(this.selectedFrom.code, this.selectedTo.code, parseFloat(this.amount))
      .subscribe(data => {
        this.convertData = data;
      });
    } else {
      this.messageService.add({ key: 'form-input-error', severity: 'error', summary: 'Error', detail: "Please select both 'From' and 'To' currencies", life: 1000 });
    }
  }
}
