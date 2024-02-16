import { Component, OnInit } from '@angular/core';
import { ExchangeRatesService } from '../../services/exchange-rates.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-scheduler-page',
  templateUrl: './scheduler-page.component.html',
  styleUrls: ['./scheduler-page.component.scss']
})
export class SchedulerPageComponent implements OnInit {
  isLoading: boolean = false;


  constructor(private exchangeRateService: ExchangeRatesService, private messageService: MessageService) {
    
  }

  ngOnInit(): void {
      
  }

  fetchData() {
    this.isLoading = true;
    this.exchangeRateService.fetchRatesAndInsertCurrencies().subscribe(data => {
      this.messageService.add({ key: 'fetchData', severity: 'success', summary: 'Success', detail: 'Data fetched successfully.', life: 1000 });
    },
    err => {
      this.messageService.add({ key: 'fetchData', severity: 'error', summary: 'Error', detail: 'Error while fetching currencies and exchange rates from API.', life: 1000 });
    },
    () => {
      this.isLoading = false;
    });
  }
}
