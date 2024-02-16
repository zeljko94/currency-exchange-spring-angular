import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-my-profile-page',
  templateUrl: './my-profile-page.component.html',
  styleUrls: ['./my-profile-page.component.scss']
})
export class MyProfilePageComponent implements OnInit {
  isLoading: boolean = false;
  loggedInUsername: string = '';

  constructor(private authService: AuthService) {

  }
  ngOnInit(): void {
    this.loggedInUsername = this.authService.getLoggedUsername();
  }
}
