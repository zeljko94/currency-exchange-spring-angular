import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from './service/app.layout.service';
import { AuthService } from '../services/auth.service';


@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {

    model: any[] = [];

    constructor(private authService: AuthService, public layoutService: LayoutService) { }

    ngOnInit() {
        this.model = [
            {
                label: 'Home',
                items: [
                    { label: 'Exchange rates', icon: 'pi pi-fw pi-dollar', routerLink: ['/home'] },
                    { label: 'Balances', icon: 'pi pi-fw pi-wallet', routerLink: ['/balances'] },
                    { label: 'Scheduler', icon: 'pi pi-fw pi-stopwatch', routerLink: ['/scheduler'] },
                ]
            },
        ];

    }
}
