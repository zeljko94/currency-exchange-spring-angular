import { Component } from '@angular/core';
import { Message, MessageService } from 'primeng/api';
import { AuthService } from '../../services/auth.service';
import { LayoutService } from '../../layout/service/app.layout.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styles: [`
      :host ::ng-deep .pi-eye,
      :host ::ng-deep .pi-eye-slash {
          transform:scale(1.6);
          margin-right: 1rem;
          color: var(--primary-color) !important;
      }
  `]
})
export class LoginPageComponent {
  valCheck: string[] = ['remember'];

  email!: string;
  password!: string;
  msgs: Message[] = [];

  constructor(private authService: AuthService, public layoutService: LayoutService, private messageService: MessageService) { }

  ngOnInit() {

  }


  login() {
    this.authService.checkLogin(this.email, this.password)
      .subscribe(user => {
        if(user) {
          this.authService.setLoginSession(user);
        }
        else {
          this.messageService.add({ key: 'login', severity: 'error', summary: 'Error', detail: 'Login error', life: 1000 });
        }
      },
      err => {
        this.messageService.add({ key: 'login', severity: 'error', summary: 'Error', detail: 'Login error', life: 1000 });
      });
  }
}
