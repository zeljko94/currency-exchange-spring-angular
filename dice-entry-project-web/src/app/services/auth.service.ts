import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../models/user';
import { UsersService } from './users.service';
import { Observable } from 'rxjs';
import { BaseRestApiService } from './base-rest-api.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService extends BaseRestApiService {
  userKey = "USER";

  constructor(http: HttpClient, private usersService: UsersService, private router: Router) { 
    super(http, "/api/auth");
  }

  register(username: string, password: string, email: string): Observable<any> {
    return this.http.post(this.apiRoute + '/register', { username: username, password: password, email: email });
  }

  checkLogin(username: string, password: string): Observable<any> {
    return this.http.post(this.apiRoute + '/login', { username: username, password: password });
  }

  getLoggedUserId() {
    return this.getLoggedInUser().id;
  }

  getLoggedUsername() {
    return this.getLoggedInUser().username;
  }


  getLoggedInUser() {
    return JSON.parse(localStorage.getItem(this.userKey));
  }

  isAuthenticated() {
    return this.getLoggedInUser() != null;
  }

  setLoginSession(user: User) {
    localStorage.setItem(this.userKey, JSON.stringify(user));
    this.router.navigate(['/home']);
  }

  destroyLoginSession() {
    localStorage.removeItem(this.userKey);
    this.router.navigate(['/login']);
  }
}
