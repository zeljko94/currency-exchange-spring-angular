import { Component, OnInit } from '@angular/core';
import { BalanceService } from '../../services/balance.service';
import { CurrencyService } from '../../services/currency.service';
import { AuthService } from '../../services/auth.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-balances',
  templateUrl: './balances.component.html',
  styleUrls: ['./balances.component.scss']
})
export class BalancesComponent implements OnInit {
  isLoading: boolean = false;
  amountToAdd: number = 0;
  amountToSubtract: number = 0;
  amountToTransfer: number = 0;
  currencies: any[] = [];
  newBalance: any = {};
  balances: any[] = [];
  selectedBalanceToTransfer: any;
  display: boolean = false;
  displayAddMoneyDialog: boolean = false;
  displaySubtractMoneyDialog: boolean = false;
  displayTransferMoneyDialog: boolean = false;

  currentBalance: any;


  constructor(private balanceService: BalanceService, private currencyService: CurrencyService, private authService: AuthService, private messageService: MessageService) {
  }

  ngOnInit() {
    this.isLoading = true;
    this.currencyService.getAll().subscribe((data:any) => { 
      this.currencies = data.map(({ id, code, name }) => ({
        id,
        code,
        name,
        label: code,
        value: code
      }));
     },
     err => {
       this.messageService.add({ key: 'form-input-error', severity: 'error', summary: 'Error', detail: 'Error while fetching currencies from API.', life: 1000 });
     },
     () => {
       this.isLoading = false;
     });
     this.fetchBalancesForUser();
  }
  
  fetchBalancesForUser() {
    this.balanceService.getBalancesForUser(this.authService.getLoggedUserId()).subscribe(data => {
      this.balances = data.map(({ id, name, userId, currencyCode, amount }) => ({
        id,
        userId,
        name,
        currencyCode,
        amount,
        label: name,
        value: 1
      }));
    },
    err => {
      this.messageService.add({ key: 'form-input-error', severity: 'error', summary: 'Error', detail: 'Error while fetching balances from database.', life: 1000 });
    },
    () => {
      this.isLoading = false;
    });
  }

  openNew() {
    this.newBalance = {};
    this.display = true;
  }

  saveBalance() {
    let obj = {
      name: this.newBalance.name,
      currencyCode: this.newBalance.currency.code,
      userId: this.authService.getLoggedUserId(),
      amount: 0
    };

      this.balanceService.insertBalance(obj, this.authService.getLoggedUserId()).subscribe((data) => {
        this.balances.push(data)
        this.onHide();
        
        this.messageService.add({ key: 'form-input-error', severity: 'success', summary: 'Success', detail: 'Balance saved successfully.', life: 1000 });
      },
      err => {
        this.messageService.add({ key: 'form-input-error', severity: 'error', summary: 'Error', detail: 'Error while inserting a balance into the databse.', life: 1000 });
      },
      () => {
        this.isLoading = false;
      });
  }

  show() {
    this.display = true;
  }

  onHide() {
    this.display = false;
  }

  openAddMoneyDialog(balance: any) {
    this.currentBalance = balance;
    this.displayAddMoneyDialog = true;
  }

  hideAddMoneyDialog() {
    this.displayAddMoneyDialog = false;
    this.currentBalance = null;
    this.amountToAdd = 0;
  }

  openSubtractMoneyDialog(balance: any) {
    this.currentBalance = balance;
    this.displaySubtractMoneyDialog = true;
  }

  hideSubtractMoneyDialog() {
    this.displaySubtractMoneyDialog = false;
    this.currentBalance = null;
    this.amountToSubtract = 0;
  }


  openTransferMoneyDialog(balance: any) {
    this.currentBalance = balance;
    this.displayTransferMoneyDialog = true;
  }

  hideTransferMoneyDialog() {
    this.displayTransferMoneyDialog = false;
    this.currentBalance = null;
    this.amountToTransfer = 0;
    this.selectedBalanceToTransfer = null;
  }


  saveAddBalance() {
    this.balanceService.addMoney(this.currentBalance.id, this.amountToAdd).subscribe(data => {
      this.fetchBalancesForUser();
      this.hideAddMoneyDialog();
      this.messageService.add({ key: 'form-input-error', severity: 'success', summary: 'Success', detail: 'Balance added successfully.', life: 1000 });
    },
    err => {
      this.messageService.add({ key: 'form-input-error', severity: 'error', summary: 'Error', detail: 'Error while adding balance.', life: 1000 });
    },
    () => {
      this.isLoading = false;
    });
  }

  saveSubtractBalance() {
    this.balanceService.subtractMoney(this.currentBalance.id, this.amountToSubtract).subscribe(data => {
      this.fetchBalancesForUser();
      this.hideSubtractMoneyDialog();
      this.messageService.add({ key: 'form-input-error', severity: 'success', summary: 'Success', detail: 'Balance subtracted successfully.', life: 1000 });
    },
    err => {
      this.messageService.add({ key: 'form-input-error', severity: 'error', summary: 'Error', detail: 'Error while while subtracting balance.', life: 1000 });
    },
    () => {
      this.isLoading = false;
    });
  }

  submitTransfer() {
    if(this.currentBalance.id == this.selectedBalanceToTransfer.id) {
      this.messageService.add({ key: 'form-input-error', severity: 'error', summary: 'Error', detail: 'Cannot transfer to the same balance.', life: 1000 });
      return;
    }

    this.balanceService.transferMoney(this.currentBalance.id, this.selectedBalanceToTransfer.id, this.amountToTransfer).subscribe(
      data => {
        this.fetchBalancesForUser();
        this.hideTransferMoneyDialog();
        this.messageService.add({ key: 'form-input-error', severity: 'success', summary: 'Success', detail: 'Balance transferred successfully.', life: 1000 });
      },
      err => {
        this.messageService.add({ key: 'form-input-error', severity: 'error', summary: 'Error', detail: 'Error while transfering balance.', life: 1000 });
      },
      () => {
        this.isLoading = false;
      }
    );
  }
}
