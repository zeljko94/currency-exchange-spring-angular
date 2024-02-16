import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseRestApiService } from './base-rest-api.service';

@Injectable({
  providedIn: 'root'
})
export class UsersService extends BaseRestApiService {

  constructor(http: HttpClient) {
    super(http, "/api/users")
   }

  // Optional<UserEntity> findByUsername(String username);
  // Boolean existsByUsername(String username);
  // Optional<UserEntity> findByEmail(String email);
  // Boolean existsByEmail(String email);

  findByUsername(username: string): Observable<any> {
    return this.http.get(this.apiRoute + '/findByUsername/' + username);
  }

  existsByUsername(username: string): Observable<any> {
    return this.http.get(this.apiRoute + '/existsByUsername/' + username);
  }

  findByEmail(email: string): Observable<any> {
    return this.http.get(this.apiRoute + '/findByEmail/' + email);
  }

  existsByEmail(email: string): Observable<any> {
    return this.http.get(this.apiRoute + '/existsByEmail/' + email);
  }

}
